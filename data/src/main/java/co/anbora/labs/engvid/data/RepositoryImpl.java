package co.anbora.labs.engvid.data;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
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
    public List<Lesson> getLessons() {

        List<Lesson> lessons = localRepository.getLessons();
        if (lessons.isEmpty()) {
            return syncLessons();
        }
        return localRepository.getLessons();
    }

    @Override
    public List<Lesson> syncLessons() {
        List<LessonTitle> titles = remoteRepository.getTitles();
        localRepository.saveTitles(titles);

        List<LessonTitle> unSync = localRepository.getUnSyncTitles();
        List<Lesson> lessons = remoteRepository.getUnSyncLessons(unSync);
        localRepository.save(lessons);

        return localRepository.getLessons();
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {

        return localRepository.getLessonById(lessonId);
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
