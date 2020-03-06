package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.CommonValuesForTests;
import co.anbora.labs.engvid.domain.repository.IRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SyncLessonsAtStartupUseCaseTest {

    private IRepository repository = Mockito.mock(IRepository.class);
    private SyncLessonsAtStartupUseCase useCase;

    @Before
    public void setUp() throws Exception {

        this.useCase = new SyncLessonsAtStartupUseCase(repository);
    }

    @Test
    public void returnAllLessonFromRepository() {
        Mockito.when(repository.getLessons())
                .thenReturn(CommonValuesForTests.advancedLessons());
        SyncLessonsAtStartupUseCase.Response response = this.useCase.execute(new SyncLessonsAtStartupUseCase.Request());
        Assert.assertEquals(response.getLessons(), CommonValuesForTests.advancedLessons());
    }
}