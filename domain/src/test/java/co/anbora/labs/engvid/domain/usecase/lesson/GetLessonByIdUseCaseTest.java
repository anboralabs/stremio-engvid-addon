package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.CommonValuesForTests;
import co.anbora.labs.engvid.domain.constants.StremioConstants;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.VIDEO_PREFIX_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class GetLessonByIdUseCaseTest {

    private IRepository repository = Mockito.mock(IRepository.class);
    private final int LESSON_ID = 6716;
    private GetLessonByIdUseCase getLessonByIdUseCase;

    @Before
    public void setUp() throws Exception {
        getLessonByIdUseCase = new GetLessonByIdUseCase(repository);
        Mockito.when(repository.getLessonById(LESSON_ID)).thenReturn(CommonValuesForTests.provideBeginnerLesson());
    }

    @Test(expected = LessonNotFoundException.class)
    public void givenANoMovieCategoryReturnEmptyResponse() {

        GetLessonByIdUseCase.Request request = new GetLessonByIdUseCase.Request("serie", "1");
        GetLessonByIdUseCase.Response response = getLessonByIdUseCase.execute(request);
        assertNull(response.getLesson());
    }

    @Test(expected = LessonNotFoundException.class)
    public void givenAnInvalidIdThrowsLessonNotFoundException() {

        GetLessonByIdUseCase.Request request =
                new GetLessonByIdUseCase.Request(StremioConstants.StremioCatalog.MOVIE, VIDEO_PREFIX_ID + "invalid");
        GetLessonByIdUseCase.Response response = getLessonByIdUseCase.execute(request);
    }

    @Test
    public void givenAValidIdReturnLesson() {

        Lesson lesson = CommonValuesForTests.provideBeginnerLesson();

        GetLessonByIdUseCase.Request request =
                new GetLessonByIdUseCase.Request(StremioConstants.StremioCatalog.MOVIE, VIDEO_PREFIX_ID + "6716");
        GetLessonByIdUseCase.Response response = getLessonByIdUseCase.execute(request);
        assertEquals(response.getLesson(), lesson);
    }

}