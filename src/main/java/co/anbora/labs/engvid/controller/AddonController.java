package co.anbora.labs.engvid.controller;

import co.anbora.labs.engvid.api.dto.LessonVideo;
import co.anbora.labs.engvid.api.CatalogContainer;
import co.anbora.labs.engvid.api.Manifest;
import co.anbora.labs.engvid.api.MetaVideo;
import co.anbora.labs.engvid.api.Stream;
import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.domain.usecase.lesson.GetAllLessonsUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonByIdUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonsByCategoryUseCase;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/")
public class AddonController {

    @Inject
    protected Manifest manifest;
    @Inject
    protected UseCaseExecutor useCaseExecutor;
    @Inject
    protected GetLessonsByCategoryUseCase getLessonsByCategoryUseCase;
    @Inject
    protected GetLessonByIdUseCase getLessonByIdUseCase;
    @Inject
    protected GetAllLessonsUseCase getAllLessonsUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Manifest> index() {
        return CompletableFuture.supplyAsync(() -> manifest);
    }

    @GET
    @Path("/manifest.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Manifest> manifest() {
        return CompletableFuture.supplyAsync(() -> manifest);
    }

    @GET
    @Path("/catalog/{type}/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<CatalogContainer> allVideos(@PathParam("type") String type, @PathParam("id") String id) {
        return useCaseExecutor.execute(getLessonsByCategoryUseCase,
                new GetLessonsByCategoryUseCase.Request(type, id),
                response -> CatalogContainer.from(response.getLessons())
        );
    }

    @GET
    @Path("/catalog/{type}/{id}/{extra}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<CatalogContainer> searchVideos(@PathParam("type") String type, @PathParam("id") String id, @PathParam("extra") String extra) {
        return useCaseExecutor.execute(getAllLessonsUseCase,
                new GetAllLessonsUseCase.Request(type, id, extra),
                response -> CatalogContainer.from(response.getLessons()));
    }

    @GET
    @Path("/meta/{type}/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<MetaVideo> infoVideo(@PathParam("type") String type, @PathParam("id") String id) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                new GetLessonByIdUseCase.Request(type, id),
                response -> MetaVideo.from(
                        LessonVideo.from(response.getLesson())
                )
        );
    }

    @GET
    @Path("/stream/{type}/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Stream> stream(@PathParam("type") String type, @PathParam("id") String id) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                new GetLessonByIdUseCase.Request(type, id),
                response -> Stream.from(response.getLesson()));
    }
}
