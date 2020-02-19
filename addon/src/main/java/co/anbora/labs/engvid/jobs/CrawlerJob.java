package co.anbora.labs.engvid.jobs;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.domain.usecase.lesson.SyncRemoteLessonsUseCase;
import io.micronaut.scheduling.annotation.Scheduled;

import java.util.function.Function;

@Singleton
public class CrawlerJob {

    @Inject
    UseCaseExecutor useCaseExecutor;

    @Inject
    SyncRemoteLessonsUseCase syncRemoteLessonsUseCase;

    @Scheduled(fixedRate = "5m")
    public void process() {
        useCaseExecutor.execute(
                syncRemoteLessonsUseCase,
                new SyncRemoteLessonsUseCase.Request(),
                Function.identity()
        );
    }
}