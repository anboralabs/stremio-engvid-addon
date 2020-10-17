package co.anbora.labs.engvid.data;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
        return localRepository.getLessons();
    }

    @Override
    public List<LessonTitle> getTitles() {
        List<LessonTitle> titles = remoteRepository.getTitles();
        localRepository.saveTitles(titles);
        return localRepository.getUnSyncTitles();
    }

    @Override
    public List<Lesson> getLessonsByTitles(List<LessonTitle> titles) {
        List<Lesson> lessons = remoteRepository.getUnSyncLessons(titles);
        localRepository.save(lessons);
        return localRepository.getLessons();
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {

        Lesson lesson = localRepository.getLessonById(lessonId);
        if (!lesson.isSync()) {
            LessonTitle title = LessonTitle
                    .builder()
                    .renderLink(lesson.getRenderLink())
                    .category(EnglishLevel.BEGINNER)
                    .build();
            syncLesson(title);
            lesson = localRepository.getLessonById(lessonId);
        }
        return lesson;
    }

    private void syncLesson(LessonTitle title) {
        List<Lesson> lessons = remoteRepository.getUnSyncLessons(Collections.singletonList(title));
        localRepository.update(lessons);
    }

    @Override
    public List<Lesson> getLessonsByCategory(Integer categoryId) {

        return localRepository.getLessonsByCategory(categoryId);
    }

    @Override
    public List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue) {

        return localRepository.getLessonsByDescription(categoryId, searchValue);
    }

    @Override
    public void markTitlesUnReachable(Collection<LessonTitle> titlesError404) {
        localRepository.markTitlesUnReachable(titlesError404);
    }
}
