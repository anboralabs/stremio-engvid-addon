package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;

import java.util.List;

public interface IEnglishVideoRepository {

    List<LessonInfo> getLessons();

    LessonMedia getLessonMediaById(String slug, Long lessonId);

}
