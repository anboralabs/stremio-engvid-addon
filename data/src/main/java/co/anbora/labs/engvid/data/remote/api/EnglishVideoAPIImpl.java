package co.anbora.labs.engvid.data.remote.api;

import co.anbora.labs.engvid.domain.exceptions.MappingJsonException;
import co.anbora.labs.engvid.domain.exceptions.MediaNotFoundException;
import co.anbora.labs.engvid.domain.exceptions.TitlesNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jasongoodwin.monads.Try;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static co.anbora.labs.engvid.domain.constants.ConstantsHelper.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.ENGLISH_LESSONS;

public class EnglishVideoAPIImpl implements EnglishVideoAPI {

    private String baseUrl;
    private OkHttpClient httpClient;
    private ObjectMapper mapper = new ObjectMapper();

    public EnglishVideoAPIImpl(String baseUrl, OkHttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    @Override
    public Response getTitles() {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        httpBuilder.addPathSegment(ENGLISH_LESSONS);

        Request request = new Request.Builder().url(httpBuilder.build()).build();
        return Try.ofFailable(() -> httpClient.newCall(request).execute())
                .orElseThrow(TitlesNotFoundException::new);
    }

    /*@Override
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
    }*/

    @Override
    public Response getMediaInfoBySlug(String slug) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(baseUrl).newBuilder();
        httpBuilder.addPathSegment(slug);
        httpBuilder.addPathSegment(EMPTY_VALUE);

        Request request = new Request.Builder().url(httpBuilder.build()).build();
        return Try.ofFailable(() -> httpClient.newCall(request).execute())
                .orElseThrow(MediaNotFoundException::new);
    }
}
