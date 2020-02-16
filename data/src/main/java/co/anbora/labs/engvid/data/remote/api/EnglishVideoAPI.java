package co.anbora.labs.engvid.data.remote.api;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;

import java.util.List;

public interface EnglishVideoAPI {

    List<LessonInfoDTO> getLessonByPage(Integer page);

}
