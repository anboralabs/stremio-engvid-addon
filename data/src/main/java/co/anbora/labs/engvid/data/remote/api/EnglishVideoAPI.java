package co.anbora.labs.engvid.data.remote.api;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import java.util.List;
import okhttp3.Response;

public interface EnglishVideoAPI {

  List<LessonInfoDTO> getLessonsByPage(Integer page, Integer maxItems);

  Response getMediaInfoBySlug(String slug);
}
