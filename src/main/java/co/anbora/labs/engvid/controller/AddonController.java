package co.anbora.labs.engvid.controller;

import co.anbora.labs.engvid.domain.model.LessonVideo;
import co.anbora.labs.engvid.domain.model.stremio.CatalogContainer;
import co.anbora.labs.engvid.domain.model.stremio.Manifest;
import co.anbora.labs.engvid.domain.model.stremio.MetaVideo;
import co.anbora.labs.engvid.domain.model.stremio.Stream;
import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.domain.usecase.lesson.GetAllLessonsUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonByIdUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonsByCategoryUseCase;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.CompletableFuture;

@Path("/")
public class AddonController {

    /*@Inject
    Manifest manifest;
    @Inject
    UseCaseExecutor useCaseExecutor;
    @Inject
    GetLessonsByCategoryUseCase getLessonsByCategoryUseCase;
    @Inject
    GetLessonByIdUseCase getLessonByIdUseCase;
    @Inject
    GetAllLessonsUseCase getAllLessonsUseCase;

    @GET
    public CompletableFuture<Manifest> index() {
        return CompletableFuture.supplyAsync(() -> manifest);
    }

    @GET
    @Path("/manifest.json")
    public CompletableFuture<Manifest> manifest() {
        return CompletableFuture.supplyAsync(() -> manifest);
    }

    @GET
    @Path("/catalog/{type}/{id}.json")
    public CompletableFuture<CatalogContainer> allVideos(String type, String id) {
        return useCaseExecutor.execute(getLessonsByCategoryUseCase,
                new GetLessonsByCategoryUseCase.Request(type, id),
                response -> CatalogContainer.from(response.getLessons())
        );
    }

    @GET
    @Path("/catalog/{type}/{id}/{extra}.json")
    public CompletableFuture<CatalogContainer> searchVideos(String type, String id, String extra) {
        return useCaseExecutor.execute(getAllLessonsUseCase,
                new GetAllLessonsUseCase.Request(type, id, extra),
                response -> CatalogContainer.from(response.getLessons()));
    }

    @CacheResult(cacheName = "cache-meta")
    @GET
    @Path("/meta/{type}/{id}.json")
    public CompletableFuture<MetaVideo> infoVideo(String type, @CacheKey String id) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                new GetLessonByIdUseCase.Request(type, id),
                response -> MetaVideo.from(
                        LessonVideo.from(response.getLesson())
                )
        );
    }

    @CacheResult(cacheName = "cache-stream")
    @GET
    @Path("/stream/{type}/{id}.json")
    public CompletableFuture<Stream> stream(String type, @CacheKey String id) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                new GetLessonByIdUseCase.Request(type, id),
                response -> Stream.from(response.getLesson()));
    }*/
    @GET
    @Path("/manifest.json")
    public CompletableFuture<String> manifest() {
        return CompletableFuture.supplyAsync(() -> "manifest");
    }
}