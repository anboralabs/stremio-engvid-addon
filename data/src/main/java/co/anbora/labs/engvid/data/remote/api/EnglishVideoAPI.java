package co.anbora.labs.engvid.data.remote.api;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import okhttp3.Response;

import java.util.List;

public interface EnglishVideoAPI {

    Response getTitles();

    List<LessonInfoDTO> getLessonsByPage(Integer page,
                                         Integer maxItems);

    Response getMediaInfoBySlug(String slug);

}
