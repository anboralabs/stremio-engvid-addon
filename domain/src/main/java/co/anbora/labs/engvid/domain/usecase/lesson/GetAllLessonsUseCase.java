package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.constants.ConstantsHelper;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.UseCase;
import com.jasongoodwin.monads.Try;
import lombok.Value;

import java.util.List;
import java.util.Objects;

import static co.anbora.labs.engvid.domain.constants.StremioConstantsHelper.StremioCatalog.ADVANCED_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstantsHelper.StremioCatalog.BEGINNER_ID_CATALOG;
import static co.anbora.labs.engvid.domain.constants.StremioConstantsHelper.StremioCatalog.INTERMEDIATE_ID_CATALOG;

public class GetAllLessonsUseCase extends UseCase<GetAllLessonsUseCase.Request, GetAllLessonsUseCase.Response> {

    private IRepository repository;

    public GetAllLessonsUseCase(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response execute(Request input) {
        String searchValue = input.getSearchValue();
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


    @Value
    public static class Request implements UseCase.InputValues {
        private String type;
        private String id;
        private String extra;

        public String getSearchValue() {
            if (Objects.nonNull(extra) && extra.isEmpty()) {
                return ConstantsHelper.EMPTY_VALUE;
            }
            String[] extras = extra.split(ConstantsHelper.EQUAL_CHARACTER);
            return Try.ofFailable(() -> extras[1])
                    .orElse(ConstantsHelper.EMPTY_VALUE);
        }
    }

    @Value
    public static class Response implements UseCase.OutputValues {
        private List<Lesson> lessons;
    }
}
