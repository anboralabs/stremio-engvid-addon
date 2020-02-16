package co.anbora.labs.engvid.data.remote.manager;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.List;

public interface IVideoManager {

    List<LessonInfo> lessons();

}
