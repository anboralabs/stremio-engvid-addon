package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.mapper.*;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddonRepositoryImplTest {

    private LessonDao lessonDao = Mockito.mock(LessonDao.class);

    private AddonRepositoryImpl addonRepository;

    @Before
    public void setUp() throws Exception {

        LessonMediaToVOMapper mediaToVOMapper = new LessonMediaToVOMapper();
        LessonInfoToVOMapper lessonInfoToVOMapper = new LessonInfoToVOMapper();
        ListLessonInfoToVOMapper listLessonInfoToVOMapper = new ListLessonInfoToVOMapper(lessonInfoToVOMapper);
        LessonVOMapper lessonVOMapper = new LessonVOMapper();
        ListLessonVOMapper listLessonVOMapper = new ListLessonVOMapper(lessonVOMapper);

        this.addonRepository = new AddonRepositoryImpl(mediaToVOMapper, listLessonInfoToVOMapper,
                lessonVOMapper, listLessonVOMapper, lessonDao);
    }

    @Test
    public void givenLessonMediaSaveInLocalDB() {

        LessonMedia lessonMedia = LessonMedia.builder()
                .id(1L)
                .imageUrl("image.jpg")
                .youtubeId("youtubeId")
                .build();

        LessonMediaVO lessonMediaVO = new LessonMediaVO(1L,
                "image.jpg",
                "youtubeId",
                true);

        this.addonRepository.save(lessonMedia);
        Mockito.verify(lessonDao).insertMedia(lessonMediaVO);
    }

}