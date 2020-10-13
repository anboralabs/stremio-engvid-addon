package co.anbora.labs.engvid.domain.repository;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;

import java.util.List;

public interface IEnglishVideoRepository {

    List<LessonTitle> getTitles();

    List<Lesson> getUnSyncLessons(List<LessonTitle> unSyncedTitles);
}
