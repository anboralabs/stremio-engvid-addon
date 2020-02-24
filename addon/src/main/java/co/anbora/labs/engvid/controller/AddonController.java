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
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@Controller()
public class AddonController {

    @Inject
    Manifest manifest;
    @Inject
    UseCaseExecutor useCaseExecutor;
    @Inject
    GetLessonsByCategoryUseCase getLessonsByCategoryUseCase;
    @Inject
    GetLessonByIdUseCase getLessonByIdUseCase;
    @Inject
    GetAllLessonsUseCase getAllLessonsUseCase;

    @Get("/")
    public CompletableFuture<Manifest> index() {
        return CompletableFuture.supplyAsync(() -> manifest);
    }

    @Get("/manifest.json")
    public CompletableFuture<Manifest> manifest() {
        return CompletableFuture.supplyAsync(() -> manifest);
    }

    @Get("/catalog/{type}/{id}.json")
    public CompletableFuture<CatalogContainer> allVideos(String type, String id) {
        return useCaseExecutor.execute(getLessonsByCategoryUseCase,
                new GetLessonsByCategoryUseCase.Request(type, id),
                response -> CatalogContainer.from(response.getLessons())
        );
    }

    @Get("/catalog/{type}/{id}/{extra}.json")
    public CompletableFuture<CatalogContainer> searchVideos(String type, String id, String extra) {
        return useCaseExecutor.execute(getAllLessonsUseCase,
                new GetAllLessonsUseCase.Request(type, id, extra),
                response -> CatalogContainer.from(response.getLessons()));
    }

    @Cacheable(value = "addon-cache", parameters = "id")
    @Get("/meta/{type}/{id}.json")
    public CompletableFuture<MetaVideo> infoVideo(String type, String id) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                new GetLessonByIdUseCase.Request(type, id),
                response -> MetaVideo.from(
                        LessonVideo.from(response.getLesson())
                )
        );
    }

    @Cacheable
    @Get("/stream/{type}/{id}.json")
    public CompletableFuture<Stream> stream(String type, String id) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                new GetLessonByIdUseCase.Request(type, id),
                response -> Stream.from(response.getLesson()));
    }
}