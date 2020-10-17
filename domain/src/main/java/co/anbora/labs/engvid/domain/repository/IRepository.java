package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;

import java.util.Collection;
import java.util.List;

public interface IRepository {

    List<Lesson> getLessons();

    List<LessonTitle> getTitles();

    List<Lesson> getLessonsByTitles(List<LessonTitle> titles);

    Lesson getLessonById(Integer lessonId);

    List<Lesson> getLessonsByCategory(Integer categoryId);

    List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue);

    void markTitlesUnReachable(Collection<LessonTitle> titlesError404);
}
