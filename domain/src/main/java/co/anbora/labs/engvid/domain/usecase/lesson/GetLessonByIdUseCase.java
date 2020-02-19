package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import com.jasongoodwin.monads.Try;
import lombok.Value;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.MOVIE;


public class GetLessonByIdUseCase extends UseCase<GetLessonByIdUseCase.Request, GetLessonByIdUseCase.Response> {

    private IAddOnRepository repository;

    public GetLessonByIdUseCase(IAddOnRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {
        if (MOVIE.equals(input.type)) {
            return Try.ofFailable(() -> Integer.parseInt(input.type))
                        .map(this.repository::getLessonMediaById)
                        .map(Response::new)
                        .orElseThrow(LessonNotFoundException::new);
        }
        return new Response(null);
    }

    @Value
    public static class Request implements UseCase.InputValues {
        private String type;
        private String id;
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private Lesson lesson;
    }
}
