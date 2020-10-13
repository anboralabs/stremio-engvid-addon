package co.anbora.labs.engvid.data.remote.api;

import okhttp3.Response;

import java.util.List;

public interface EnglishVideoAPI {

    Response getTitles();

    Response getMediaInfoBySlug(String slug);

}
