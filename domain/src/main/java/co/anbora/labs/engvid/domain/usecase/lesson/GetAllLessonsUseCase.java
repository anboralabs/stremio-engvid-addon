package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.*;

public class GetAllLessonsUseCase extends UseCase<GetAllLessonsUseCase.Request, GetAllLessonsUseCase.Response> {

    private IRepository repository;

    public GetAllLessonsUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {
        if (MOVIE.equals(input.type) && isValidCategory(input)) {
            return new Response(this.repository.getLessons());
        }
        return new Response(null);
    }

    private boolean isValidCategory(Request input) {
        return BEGINNER_ID_CATALOG.equals(input.id)
                || INTERMEDIATE_ID_CATALOG.equals(input.id)
                || ADVANCED_ID_CATALOG.equals(input.id);
    }

    @Value
    public static class Request implements UseCase.InputValues {
        private String type;
        private String id;
        private String extra;
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<Lesson> lessons;
    }
}
