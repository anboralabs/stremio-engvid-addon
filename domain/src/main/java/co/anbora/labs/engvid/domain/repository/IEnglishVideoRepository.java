package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;

import java.util.List;

public interface IEnglishVideoRepository {

    List<LessonTitle> getTitles();

    List<LessonInfo> getLessons();

    LessonMedia getLessonMediaById(String slug, Long lessonId);

}
