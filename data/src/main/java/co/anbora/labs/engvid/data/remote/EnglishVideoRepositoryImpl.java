package co.anbora.labs.engvid.data.remote;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import com.jasongoodwin.monads.Try;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.INITIAL_PAGE;

public class EnglishVideoRepositoryImpl implements IEnglishVideoRepository {

    private BiFunction<String, LessonTitle, Lesson> htmlLessonMapper;
    private Function<String, List<LessonTitle>> htmlLessonTitleDTOMapper;

    private EnglishVideoAPI englishVideoAPI;

    public EnglishVideoRepositoryImpl(EnglishVideoAPI englishVideoAPI,
                                      BiFunction<String, LessonTitle, Lesson> htmlMediaDTOMapper,
                                      Function<String, List<LessonTitle>> htmlLessonTitleDTOMapper) {
        this.englishVideoAPI = englishVideoAPI;
        this.htmlLessonMapper = htmlMediaDTOMapper;
        this.htmlLessonTitleDTOMapper = htmlLessonTitleDTOMapper;
    }

    @Override
    public List<LessonTitle> getTitles() {
        return Try.ofFailable(() -> englishVideoAPI.getTitles())
                .filter(Response::isSuccessful)
                .map(Response::body)
                .map(response -> htmlLessonTitleDTOMapper.apply(response.string()))
                .orElseThrow(LessonNotFoundException::new);
    }

    /*private List<LessonInfoDTO> getLessons(final int page, int maxItems) {
        List<LessonInfoDTO> lessonInfoDTOS = Try.ofFailable(() -> englishVideoAPI.getLessonsByPage(page, maxItems))
                .orElse(new ArrayList<>());

        if (!lessonInfoDTOS.isEmpty()) {
            lessonInfoDTOS.addAll(getLessons(page + 1, maxItems));
        }
        return lessonInfoDTOS;
    }*/

    @Override
    public List<Lesson> getUnSyncLessons(List<LessonTitle> unSyncedTitles) {
        List<Try<Lesson>> lessonsLoaded = getLessonsWithHtmlInformation(unSyncedTitles, INITIAL_PAGE);
        return lessonsLoaded.stream()
                .filter(Try::isSuccess)
                .map(Try::getUnchecked)
                .collect(Collectors.toList());
    }

    private List<Try<Lesson>> getLessonsWithHtmlInformation(List<LessonTitle> unSyncedTitles, int position) {
        List<Try<Lesson>> loadedLessons = new ArrayList<>();
        if (position < unSyncedTitles.size()) {
            Try<Lesson> loaded = getMediaFromApi(unSyncedTitles.get(position));
            loadedLessons.add(loaded);
            loadedLessons.addAll(getLessonsWithHtmlInformation(unSyncedTitles, position + 1));
        }
        return loadedLessons;
    }

    private Try<Lesson> getMediaFromApi(LessonTitle title) {
        return Try.ofFailable(() -> englishVideoAPI.getMediaInfoBySlug(title.getSlug()))
                .filter(Response::isSuccessful)
                .map(Response::body)
                .map(response -> htmlLessonMapper.apply(response.string(), title));
    }
}
