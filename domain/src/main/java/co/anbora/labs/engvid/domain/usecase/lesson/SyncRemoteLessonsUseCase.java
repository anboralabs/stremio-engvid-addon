package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;

public class SyncRemoteLessonsUseCase extends UseCase<SyncRemoteLessonsUseCase.Request, SyncRemoteLessonsUseCase.Response> {

    private IRepository repository;

    public SyncRemoteLessonsUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {

        return new Response(this.repository.syncLessons());
    }

    @Value
    public static class Request implements UseCase.InputValues {
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<Lesson> lessons;
    }

}
