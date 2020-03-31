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
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class GetAllLessonsUseCaseTest {

    private IRepository repository = Mockito.mock(IRepository.class);
    private GetAllLessonsUseCase getAllLessonsUseCase;

    @Before
    public void setUp() throws Exception {

        this.getAllLessonsUseCase = new GetAllLessonsUseCase(repository);
    }

    @Test
    public void givenAnInvalidCatalogIdReturnEmptyResponse() {

        String search = "";
        String invalid = "invalid";

        GetAllLessonsUseCase.Request request = new GetAllLessonsUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE, invalid, search);
        GetAllLessonsUseCase.Response response = getAllLessonsUseCase.execute(request);
        assertNull(response.getLessons());
    }

    @Test
    public void givenBeginnerIdCatalogIdReturnAllBeginnerLessons() {

        String search = "search=In this easy English class";
        String searchValue = "In this easy English class";

        Mockito.when(repository.getLessonsByDescription(EnglishLevel.BEGINNER.getId(), searchValue))
                .thenReturn(CommonValuesForTestsHelper.beginnerLessons());

        GetAllLessonsUseCase.Request request = new GetAllLessonsUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE, StremioConstantsHelper.StremioCatalog.BEGINNER_ID_CATALOG, search);
        GetAllLessonsUseCase.Response response = getAllLessonsUseCase.execute(request);
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.beginnerLessons());
    }

    @Test
    public void givenIntermediateIdCatalogIdReturnAllIntermediateLessons() {

        String search = "search=set phrases is one";
        String searchValue = "set phrases is one";

        Mockito.when(repository.getLessonsByDescription(EnglishLevel.INTERMEDIATE.getId(), searchValue))
                .thenReturn(CommonValuesForTestsHelper.intermediateLessons());

        GetAllLessonsUseCase.Request request = new GetAllLessonsUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE,
                StremioConstantsHelper.StremioCatalog.INTERMEDIATE_ID_CATALOG, search);
        GetAllLessonsUseCase.Response response = getAllLessonsUseCase.execute(request);
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.intermediateLessons());
    }

    @Test
    public void givenAdvancedIdCatalogIdReturnAllAdvancedLessons() {

        String search = "search=lesson is all about English";
        String searchValue = "lesson is all about English";

        Mockito.when(repository.getLessonsByDescription(EnglishLevel.ADVANCE.getId(), searchValue))
                .thenReturn(CommonValuesForTestsHelper.advancedLessons());

        GetAllLessonsUseCase.Request request = new GetAllLessonsUseCase.Request(StremioConstantsHelper.StremioCatalog.MOVIE,
                StremioConstantsHelper.StremioCatalog.ADVANCED_ID_CATALOG, search);
        GetAllLessonsUseCase.Response response = getAllLessonsUseCase.execute(request);
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.advancedLessons());
    }

}
