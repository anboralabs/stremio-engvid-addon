package co.anbora.labs.engvid.service;

import co.anbora.labs.engvid.api.CatalogContainer;
import co.anbora.labs.engvid.api.MetaVideo;
import co.anbora.labs.engvid.api.Stream;
import co.anbora.labs.engvid.api.dto.LessonVideo;
import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.domain.usecase.lesson.GetAllLessonsUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonByIdUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonsByCategoryUseCase;
import io.quarkus.cache.CacheResult;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class AddonService {

    @Inject
    protected UseCaseExecutor useCaseExecutor;
    @Inject
    protected GetLessonsByCategoryUseCase getLessonsByCategoryUseCase;
    @Inject
    protected GetLessonByIdUseCase getLessonByIdUseCase;
    @Inject
    protected GetAllLessonsUseCase getAllLessonsUseCase;

    @CacheResult(cacheName = "cache-lessons")
    @ConsumeEvent(value = "allVideos", blocking = true)
    public CompletionStage<CatalogContainer> allVideos(GetLessonsByCategoryUseCase.Request request) {
        return useCaseExecutor.execute(getLessonsByCategoryUseCase,
                request,
                response -> CatalogContainer.from(response.getLessons())
        );
    }

    @ConsumeEvent(value = "searchVideos", blocking = true)
    public CompletionStage<CatalogContainer> searchVideos(GetAllLessonsUseCase.Request request) {
        return useCaseExecutor.execute(getAllLessonsUseCase,
                request,
                response -> CatalogContainer.from(response.getLessons())
        );
    }

    @CacheResult(cacheName = "cache-meta")
    @ConsumeEvent(value = "infoVideo", blocking = true)
    public CompletionStage<MetaVideo> infoVideo(GetLessonByIdUseCase.Request request) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                request,
                response -> MetaVideo.from(
                        LessonVideo.from(response.getLesson())
                )
        );
    }

    @CacheResult(cacheName = "cache-stream")
    @ConsumeEvent(value = "stream", blocking = true)
    public CompletionStage<Stream> stream(GetLessonByIdUseCase.Request request) {
        return useCaseExecutor.execute(getLessonByIdUseCase,
                request,
                response -> Stream.from(response.getLesson())
        );
    }

}
