package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.CommonValuesForTestsHelper;
import co.anbora.labs.engvid.domain.constants.StremioConstantsHelper;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetLessonsByCategoryUseCaseTest {

    private IRepository repository = Mockito.mock(IRepository.class);
    private GetLessonsByCategoryUseCase getLessonsByCategoryUseCase;

    @Before
    public void setUp() throws Exception {

        getLessonsByCategoryUseCase = new GetLessonsByCategoryUseCase(repository);
    }

    @Test
    public void givenAnInvalidCatalogIdReturnEmptyResponse() {

        int zero = 0;
        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE, "invalid");
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        assertEquals(response.getLessons().size(), zero);
    }

    @Test
    public void givenBeginnerIdCatalogIdReturnAllBeginnerLessons() {

        Mockito.when(repository.getLessonsByCategory(EnglishLevel.BEGINNER.getId()))
                .thenReturn(CommonValuesForTestsHelper.beginnerLessons());

        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE, StremioConstantsHelper.StremioCatalog.BEGINNER_ID_CATALOG);
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.beginnerLessons());
    }

    @Test
    public void givenIntermediateIdCatalogIdReturnAllIntermediateLessons() {

        Mockito.when(repository.getLessonsByCategory(EnglishLevel.INTERMEDIATE.getId()))
                .thenReturn(CommonValuesForTestsHelper.intermediateLessons());

        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE, StremioConstantsHelper.StremioCatalog.INTERMEDIATE_ID_CATALOG);
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.intermediateLessons());
    }

    @Test
    public void givenAdvancedIdCatalogIdReturnAllAdvancedLessons() {

        Mockito.when(repository.getLessonsByCategory(EnglishLevel.ADVANCE.getId()))
                .thenReturn(CommonValuesForTestsHelper.advancedLessons());

        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE, StremioConstantsHelper.StremioCatalog.ADVANCED_ID_CATALOG);
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.advancedLessons());
    }

}
