package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;

import java.util.List;

public interface IAddOnRepository {

    List<LessonInfo> getLessons();

    LessonMedia getLessonMediaById(Integer lessonId);

}
