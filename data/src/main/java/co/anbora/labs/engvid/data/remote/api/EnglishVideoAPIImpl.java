package co.anbora.labs.engvid.data.remote.api;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.domain.exceptions.MappingJsonException;
import co.anbora.labs.engvid.domain.exceptions.MediaNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jasongoodwin.monads.Try;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EnglishVideoAPIImpl implements EnglishVideoAPI {

  private String baseUrl;
  private OkHttpClient httpClient;
  private ObjectMapper mapper = new ObjectMapper();

  public EnglishVideoAPIImpl(String baseUrl, OkHttpClient httpClient) {
    this.baseUrl = baseUrl;
    this.httpClient = httpClient;
  }

  @Override
  public List<LessonInfoDTO> getLessonsByPage(Integer page, Integer maxItems) {
    HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
    httpBuilder.addPathSegment("wp-json")
        .addPathSegment("wp")
        .addPathSegment("v2")
        .addPathSegment("posts")
        .addQueryParameter("page", page.toString())
        .addQueryParameter("per_page", maxItems.toString());

    Request request = new Request.Builder().url(httpBuilder.build()).build();
    return Try.ofFailable(() -> httpClient.newCall(request).execute())
        .map(this::transformLessonsResponse)
        .orElseThrow(MappingJsonException::new);
  }

  private List<LessonInfoDTO> transformLessonsResponse(Response response) {
    return Try.ofFailable(() -> response.body().bytes())
        .map(bytes -> mapper.readValue(bytes, LessonInfoDTO[].class))
        .map(Arrays::asList)
        .map(ArrayList::new)
        .orElseThrow(MappingJsonException::new);
  }

  @Override
  public Response getMediaInfoBySlug(String slug) {
    HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
    httpBuilder.addPathSegment(slug);

    Request request = new Request.Builder().url(httpBuilder.build()).build();
    return Try.ofFailable(() -> httpClient.newCall(request).execute())
        .orElseThrow(MediaNotFoundException::new);
  }
}
