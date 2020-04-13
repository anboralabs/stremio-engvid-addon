package co.anbora.labs.engvid.data;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;

import java.util.List;
import java.util.function.BiFunction;

public class RepositoryImpl implements IRepository {

    private BiFunction<Lesson, LessonMedia, Lesson> lessonMapper;

    private IAddOnRepository localRepository;
    private IEnglishVideoRepository remoteRepository;

    public RepositoryImpl(IAddOnRepository localRepository,
                          IEnglishVideoRepository remoteRepository,
                          BiFunction<Lesson, LessonMedia, Lesson> lessonMapper) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<Lesson> getLessons() {

        List<Lesson> lessons = localRepository.getLessons();
        if (lessons.isEmpty()) {
            List<LessonInfo> lessonInfos = remoteRepository.getLessons();
            localRepository.save(lessonInfos);
        }
        return localRepository.getLessons();
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {

        Lesson media = localRepository.getLessonById(lessonId);
        if (!media.isSync()) {
            LessonMedia lessonMedia = remoteRepository.getLessonMediaById(media.getSlug(), media.getId());
            localRepository.save(lessonMedia);
            media = lessonMapper.apply(media, lessonMedia);
        }
        return media;
    }

    @Override
    public List<Lesson> getLessonsByCategory(Integer categoryId) {

        return localRepository.getLessonsByCategory(categoryId);
    }

    @Override
    public List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue) {

        return localRepository.getLessonsByDescription(categoryId, searchValue);
    }
}
