package co.anbora.labs.engvid.controller;

import co.anbora.labs.engvid.api.CatalogContainer;
import co.anbora.labs.engvid.api.Manifest;
import co.anbora.labs.engvid.api.MetaVideo;
import co.anbora.labs.engvid.api.Stream;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.usecase.lesson.GetAllLessonsUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonByIdUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonsByCategoryUseCase;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;

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
    protected EventBus bus;

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

        GetLessonsByCategoryUseCase.Request request =
                new GetLessonsByCategoryUseCase.Request(type, id);

        return bus.<CatalogContainer>request("allVideos", request)
                .onItem().apply(Message::body).subscribeAsCompletionStage();
    }

    @GET
    @Path("/catalog/{type}/{id}/{extra}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<CatalogContainer> searchVideos(@PathParam("type") String type, @PathParam("id") String id, @PathParam("extra") String extra) {

        GetAllLessonsUseCase.Request request =
                new GetAllLessonsUseCase.Request(type, id, extra);

        return bus.<CatalogContainer>request("searchVideos", request)
                .onItem().apply(Message::body).subscribeAsCompletionStage();
    }

    @GET
    @Path("/meta/{type}/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<MetaVideo> infoVideo(@PathParam("type") String type, @PathParam("id") String id) {

        GetLessonByIdUseCase.Request request = getRequest(type, id);

        return bus.<MetaVideo>request("infoVideo", request)
                .onItem().apply(Message::body).subscribeAsCompletionStage();
    }

    @GET
    @Path("/stream/{type}/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Stream> stream(@PathParam("type") String type, @PathParam("id") String id) {

        GetLessonByIdUseCase.Request request = getRequest(type, id);

        return bus.<Stream>request("stream", request)
                .onItem().apply(Message::body).subscribeAsCompletionStage();
    }

    private GetLessonByIdUseCase.Request getRequest(String type, String id) {
        GetLessonByIdUseCase.Request request = new GetLessonByIdUseCase.Request(type, id);
        if (!request.isValidVideoId()) {
            throw new LessonNotFoundException(request.getType(), request.getId());
        }
        return request;
    }
}
