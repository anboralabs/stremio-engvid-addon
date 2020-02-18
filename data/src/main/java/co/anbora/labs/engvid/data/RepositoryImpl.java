package co.anbora.labs.engvid.data;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;

import java.util.List;

public class RepositoryImpl implements IRepository {

    private IAddOnRepository localRepository;
    private IEnglishVideoRepository remoteRepository;

    public RepositoryImpl(IAddOnRepository localRepository,
                          IEnglishVideoRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public List<LessonInfo> getLessons() {

        List<LessonInfo> lessons = localRepository.getLessons();
        if (lessons.isEmpty()) {
            lessons = remoteRepository.getLessons();
            localRepository.save(lessons);
        }
        return lessons;
    }

    @Override
    public LessonMedia getLessonMediaById(Integer lessonId) {

        LessonMedia media = localRepository.getLessonMediaById(lessonId);
        if (!media.isSync()) {
            media = remoteRepository.getLessonMediaById(media.getSlug(), media.getId());
            localRepository.save(media);
        }
        return media;
    }

    @Override
    public List<LessonInfo> getLessonsByCategory(Integer categoryId) {

        return localRepository.getLessonsByCategory(categoryId);
    }
}
