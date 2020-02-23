package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static co.anbora.labs.engvid.domain.constants.Constants.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.Constants.EQUAL_CHARACTER;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.MIN_EXTRAS;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.SEARCH;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.*;

public class GetAllLessonsUseCase extends UseCase<GetAllLessonsUseCase.Request, GetAllLessonsUseCase.Response> {

    private IRepository repository;

    public GetAllLessonsUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {
        String searchValue = getSearchValue(input.extra);
        if (MOVIE.equals(input.type) && BEGINNER_ID_CATALOG.equals(input.id)) {
            return getResponse(searchValue, EnglishLevel.BEGINNER);
        }
        if (MOVIE.equals(input.type) && INTERMEDIATE_ID_CATALOG.equals(input.id)) {
            return getResponse(searchValue, EnglishLevel.INTERMEDIATE);
        }
        if (MOVIE.equals(input.type) && ADVANCED_ID_CATALOG.equals(input.id)) {
            return getResponse(searchValue, EnglishLevel.ADVANCE);
        }
        return new Response(null);
    }

    private Response getResponse(String searchValue, EnglishLevel beginner) {
        return new Response(getFilteredLessons(
                repository.getLessonsByCategory(beginner.getId()), searchValue)
        );
    }

    private List<Lesson> getFilteredLessons(List<Lesson> lessons, String extra) {
        return lessons
                .stream()
                .filter(lesson -> lesson.getDescription().contains(extra))
                .collect(Collectors.toList());
    }

    private String getSearchValue(String extra) {
        Map<String, String> mapExtras = Stream.of(extra)
                .map(str -> str.split(EQUAL_CHARACTER))
                .filter(extras -> extras.length > MIN_EXTRAS)
                .collect(Collectors.toMap(t -> t[0], t -> t[1]));
        return mapExtras.getOrDefault(SEARCH, EMPTY_VALUE);
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
