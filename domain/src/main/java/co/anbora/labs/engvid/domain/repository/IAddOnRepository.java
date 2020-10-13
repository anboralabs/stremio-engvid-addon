package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;

import java.util.List;

public interface IAddOnRepository {

    void save(List<Lesson> lessons);

    void saveTitles(List<LessonTitle> titles);

    List<Lesson> getLessons();

    List<Lesson> getLessonsByCategory(Integer categoryId);

    Lesson getLessonById(Integer lessonId);

    List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue);

    List<LessonTitle> getUnSyncTitles();
}
