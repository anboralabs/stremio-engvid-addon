package co.anbora.labs.engvid.data.remote;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.INITIAL_PAGE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.MAX_POST_BY_PAGE;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import com.jasongoodwin.monads.Try;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import okhttp3.Response;

public class EnglishVideoRepositoryImpl implements IEnglishVideoRepository {

  private Function<List<LessonInfoDTO>, List<LessonInfo>>
      listLessonInfoDTOMapper;
  private BiFunction<String, Long, LessonMedia> htmlMediaDTOMapper;

  private EnglishVideoAPI englishVideoAPI;

  public EnglishVideoRepositoryImpl(
      EnglishVideoAPI englishVideoAPI,
      Function<List<LessonInfoDTO>, List<LessonInfo>> listLessonInfoDTOMapper,
      BiFunction<String, Long, LessonMedia> htmlMediaDTOMapper) {
    this.englishVideoAPI = englishVideoAPI;
    this.listLessonInfoDTOMapper = listLessonInfoDTOMapper;
    this.htmlMediaDTOMapper = htmlMediaDTOMapper;
  }

  @Override
  public List<LessonInfo> getLessons() {
    return listLessonInfoDTOMapper.apply(
        getLessons(INITIAL_PAGE, MAX_POST_BY_PAGE));
  }

  private List<LessonInfoDTO> getLessons(final int page, int maxItems) {
    List<LessonInfoDTO> lessonInfoDTOS =
        Try.ofFailable(() -> englishVideoAPI.getLessonsByPage(page, maxItems))
            .orElse(new ArrayList<>());

    if (!lessonInfoDTOS.isEmpty()) {
      lessonInfoDTOS.addAll(getLessons(page + 1, maxItems));
    }
    return lessonInfoDTOS;
  }

  @Override
  public LessonMedia getLessonMediaById(String slug, Long lessonId) {
    return getMediaFromApi(slug, lessonId);
  }

  private LessonMedia getMediaFromApi(String slug, Long lessonId) {
    return Try.ofFailable(() -> englishVideoAPI.getMediaInfoBySlug(slug))
        .filter(Response::isSuccessful)
        .map(Response::body)
        .map(response -> htmlMediaDTOMapper.apply(response.string(), lessonId))
        .orElseThrow(LessonNotFoundException::new);
  }
}
