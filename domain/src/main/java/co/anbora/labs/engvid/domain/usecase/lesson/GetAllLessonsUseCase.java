package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.constants.Constants;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.MIN_EXTRAS;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.SEARCH;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.ADVANCED_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.BEGINNER_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.INTERMEDIATE_ID_CATALOG;

public class GetAllLessonsUseCase extends UseCase<GetAllLessonsUseCase.Request, GetAllLessonsUseCase.Response> {

    private IRepository repository;

    public GetAllLessonsUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {
        String searchValue = getSearchValue(input.extra);
        switch (input.id) {
            case BEGINNER_ID_CATALOG:
                return getResponse(searchValue, EnglishLevel.BEGINNER);
            case INTERMEDIATE_ID_CATALOG:
                return getResponse(searchValue, EnglishLevel.INTERMEDIATE);
            case ADVANCED_ID_CATALOG:
                return getResponse(searchValue, EnglishLevel.ADVANCE);
            default:
                return new Response(null);
        }
    }

    private Response getResponse(String searchValue, EnglishLevel englishLevel) {
        if (searchValue.isEmpty()) {
            return new Response(repository.getLessonsByCategory(englishLevel.getId()));
        }
        return new Response(repository.getLessonsByDescription(englishLevel.getId(), searchValue));
    }

    private String getSearchValue(String extra) {
        if (Objects.nonNull(extra) && extra.isEmpty()) {
            return Constants.EMPTY_VALUE;
        }
        Map<String, String> mapExtras = Stream.of(extra)
                .map(str -> str.split(Constants.EQUAL_CHARACTER))
                .filter(extras -> extras.length > MIN_EXTRAS)
                .collect(Collectors.toMap(t -> t[0], t -> t[1]));
        return mapExtras.getOrDefault(SEARCH, Constants.EMPTY_VALUE);
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
