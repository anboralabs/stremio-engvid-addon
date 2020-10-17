package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;

public class SyncLessonsAtStartupUseCase extends UseCase<SyncLessonsAtStartupUseCase.Request, SyncLessonsAtStartupUseCase.Response> {

    private IRepository repository;
    private SyncRemoteLessonsUseCase syncRemoteLessonsUseCase;

    public SyncLessonsAtStartupUseCase(IRepository repository,
                                       SyncRemoteLessonsUseCase syncRemoteLessonsUseCase) {
        this.repository = repository;
        this.syncRemoteLessonsUseCase = syncRemoteLessonsUseCase;
    }

    @Override
    public Response execute(Request input) {

        List<Lesson> lessons = this.repository.getLessons();
        if (lessons.isEmpty()) {
            this.syncRemoteLessonsUseCase.execute(
                    new SyncRemoteLessonsUseCase.Request()
            );
        }

        return new Response(lessons);
    }

    @Value
    public static class Request implements UseCase.InputValues {
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<Lesson> lessons;
    }
}
