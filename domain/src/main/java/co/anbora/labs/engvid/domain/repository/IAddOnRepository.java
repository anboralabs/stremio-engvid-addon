package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import java.util.List;

public interface IAddOnRepository {

  void save(LessonMedia lessonMedia);

  void save(List<LessonInfo> lessons);

  List<Lesson> getLessons();

  List<Lesson> getLessonsByCategory(Integer categoryId);

  Lesson getLessonById(Integer lessonId);

  List<Lesson> getLessonsByDescription(Integer categoryId, String searchValue);
}
