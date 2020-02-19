package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.*;

public class GetLessonsByCategoryUseCase extends UseCase<GetLessonsByCategoryUseCase.Request, GetLessonsByCategoryUseCase.Response> {

    private IAddOnRepository repository;

    public GetLessonsByCategoryUseCase(IAddOnRepository repository) {
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
        private List<LessonInfo> lessons;
    }
}
