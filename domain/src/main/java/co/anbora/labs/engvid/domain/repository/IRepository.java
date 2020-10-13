package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.List;

public interface IRepository {

    List<Lesson> getLessons();

    List<Lesson> syncLessons();

    Lesson getLessonById(Integer lessonId);

    List<Lesson> getLessonsByCategory(Integer categoryId);

    List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue);
}
