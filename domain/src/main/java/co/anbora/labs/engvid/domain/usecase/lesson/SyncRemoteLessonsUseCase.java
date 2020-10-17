package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SyncRemoteLessonsUseCase extends UseCase<SyncRemoteLessonsUseCase.Request, SyncRemoteLessonsUseCase.Response> {

    private IRepository repository;

    public SyncRemoteLessonsUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {

        List<LessonTitle> unSyncTitles = this.repository.getTitles();
        if (!unSyncTitles.isEmpty()) {
            List<Lesson> lessons = this.repository.getLessonsByTitles(unSyncTitles);

            Collection<LessonTitle> titlesError404 = filterAvailableLessons(unSyncTitles, lessons);
            this.repository.markTitlesUnReachable(titlesError404);
        }
        return new Response();
    }

    private Collection<LessonTitle> filterAvailableLessons(List<LessonTitle> titles, List<Lesson> lessons) {

        Map<String, LessonTitle> updateTitlesWithError = titles.stream()
                .collect(Collectors.toMap(LessonTitle::getSlug, Function.identity()));

        for (Lesson lesson: lessons) {
            updateTitlesWithError.entrySet()
                    .removeIf(entries -> entries.getKey().equals(lesson.getSlug()));
        }
        return updateTitlesWithError.values();
    }

    @Value
    public static class Request implements UseCase.InputValues {
    }

    @Value
    public static class Response implements UseCase.OutputValues {

    }

}
