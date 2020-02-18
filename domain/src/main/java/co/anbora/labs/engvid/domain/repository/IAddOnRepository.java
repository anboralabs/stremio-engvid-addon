package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;

import java.util.List;

public interface IAddOnRepository {

    void save(LessonMedia lessonMedia);

    void save(List<LessonInfo> lessons);

    List<LessonInfo> getLessons();

    List<LessonInfo> getLessonsByCategory(Integer categoryId);

    LessonMedia getLessonMediaById(Integer lessonId);

}
