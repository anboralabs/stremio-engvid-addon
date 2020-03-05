package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.CommonValuesForTests;
import co.anbora.labs.engvid.domain.constants.StremioConstants;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.repository.IRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

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
        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstants.StremioCatalog.MOVIE, "invalid");
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        Assert.assertEquals(response.getLessons().size(), zero);
    }

    @Test
    public void givenBeginnerIdCatalogIdReturnAllBeginnerLessons() {

        Mockito.when(repository.getLessonsByCategory(EnglishLevel.BEGINNER.getId()))
                .thenReturn(CommonValuesForTests.beginnerLessons());

        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstants.StremioCatalog.MOVIE, StremioConstants.StremioCatalog.BEGINNER_ID_CATALOG);
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        Assert.assertEquals(response.getLessons(), CommonValuesForTests.beginnerLessons());
    }

    @Test
    public void givenIntermediateIdCatalogIdReturnAllIntermediateLessons() {

        Mockito.when(repository.getLessonsByCategory(EnglishLevel.INTERMEDIATE.getId()))
                .thenReturn(CommonValuesForTests.intermediateLessons());

        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstants.StremioCatalog.MOVIE, StremioConstants.StremioCatalog.INTERMEDIATE_ID_CATALOG);
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        Assert.assertEquals(response.getLessons(), CommonValuesForTests.intermediateLessons());
    }

    @Test
    public void givenAdvancedIdCatalogIdReturnAllAdvancedLessons() {

        Mockito.when(repository.getLessonsByCategory(EnglishLevel.ADVANCE.getId()))
                .thenReturn(CommonValuesForTests.advancedLessons());

        GetLessonsByCategoryUseCase.Request request = new GetLessonsByCategoryUseCase.Request(StremioConstants.StremioCatalog.MOVIE, StremioConstants.StremioCatalog.ADVANCED_ID_CATALOG);
        GetLessonsByCategoryUseCase.Response response = getLessonsByCategoryUseCase.execute(request);
        Assert.assertEquals(response.getLessons(), CommonValuesForTests.advancedLessons());
    }

}