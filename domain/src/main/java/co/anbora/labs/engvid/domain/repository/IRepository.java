package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.List;

public interface IRepository {

    List<LessonInfo> getLessons();

    Lesson getLessonById(Integer lessonId);

    List<LessonInfo> getLessonsByCategory(Integer categoryId);
}
