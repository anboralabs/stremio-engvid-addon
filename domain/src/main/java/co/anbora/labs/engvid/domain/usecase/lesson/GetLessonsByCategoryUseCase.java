package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.ADVANCED_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.BEGINNER_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.INTERMEDIATE_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.MOVIE;


public class GetLessonsByCategoryUseCase extends UseCase<GetLessonsByCategoryUseCase.Request, GetLessonsByCategoryUseCase.Response> {

    private IRepository repository;

    public GetLessonsByCategoryUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {

        if (MOVIE.equals(input.type) && BEGINNER_ID_CATALOG.equals(input.id)) {
            return new Response(repository.getLessonsByCategory(EnglishLevel.BEGINNER.getId()));
        }
        if (MOVIE.equals(input.type) && INTERMEDIATE_ID_CATALOG.equals(input.id)) {
            return new Response(repository.getLessonsByCategory(EnglishLevel.INTERMEDIATE.getId()));
        }
        if (MOVIE.equals(input.type) && ADVANCED_ID_CATALOG.equals(input.id)) {
            return new Response(repository.getLessonsByCategory(EnglishLevel.ADVANCE.getId()));
        }
        return new Response(new ArrayList<>());
    }

    @Value
    public static class Request implements UseCase.InputValues {
        private String type;
        private String id;
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<Lesson> lessons;
    }
}
