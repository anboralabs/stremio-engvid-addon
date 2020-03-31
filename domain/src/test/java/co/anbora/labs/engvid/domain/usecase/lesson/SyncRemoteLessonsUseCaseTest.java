package co.anbora.labs.engvid.domain.usecase.lesson;

import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class SyncRemoteLessonsUseCaseTest {

    private IAddOnRepository localRepository = Mockito.mock(IAddOnRepository.class);
    private IEnglishVideoRepository remoteRepository = Mockito.mock(IEnglishVideoRepository.class);
    private SyncRemoteLessonsUseCase useCase;

    @Before
    public void setUp() throws Exception {

        useCase = new SyncRemoteLessonsUseCase(localRepository, remoteRepository);
    }

    @Test
    public void syncRemoteVideosInLocalDatabase() {

        LessonInfo lessonInfo = LessonInfo.builder()
                .title("test")
                .build();

        List<LessonInfo> infos = Arrays.asList(lessonInfo);

        Mockito.when(remoteRepository.getLessons())
                .thenReturn(infos);

        SyncRemoteLessonsUseCase.Response response = useCase.execute(new SyncRemoteLessonsUseCase.Request());
        assertNotNull(response);
        Mockito.verify(localRepository).save(infos);
    }
}
