package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.CommonValuesForTestsHelper;
import co.anbora.labs.engvid.domain.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SyncLessonsAtStartupUseCaseTest {

    private IRepository repository = Mockito.mock(IRepository.class);
    private SyncLessonsAtStartupUseCase useCase;

    @Before
    public void setUp() throws Exception {

        SyncRemoteLessonsUseCase syncRemote = new SyncRemoteLessonsUseCase(repository);
        this.useCase = new SyncLessonsAtStartupUseCase(repository, syncRemote);
    }

    @Test
    public void returnAllLessonFromRepository() {
        Mockito.when(repository.getLessons())
                .thenReturn(CommonValuesForTestsHelper.advancedLessons());
        SyncLessonsAtStartupUseCase.Response response = this.useCase.execute(new SyncLessonsAtStartupUseCase.Request());
        assertEquals(response.getLessons(), CommonValuesForTestsHelper.advancedLessons());
    }
}
