package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.mapper.*;
import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

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

    @Test
    public void givenListLessonInfoSaveInLocalDB() {

        LessonInfo lessonInfo = LessonInfo.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();

        LessonInfoVO lessonInfoVO = new LessonInfoVO(
                1, "test", "test", null, null, null, null
        );

        this.addonRepository.save(Arrays.asList(lessonInfo));
        Mockito.verify(lessonDao).insert(Arrays.asList(lessonInfoVO));
    }

    @Test
    public void getAllLessonFromLocalDB() {

        LessonVO lessonVO = new LessonVO(
                1L,  "test", "test",
                null, null, null, null,
                null, "youtubeId", null
        );

        Lesson lesson = Lesson.builder()
                .id(1L)
                .title("test")
                .description("test")
                .youtubeId("youtubeId")
                .sync(true)
                .build();

        Mockito.when(lessonDao.findAll())
                .thenReturn(Arrays.asList(lessonVO));

        Assert.assertEquals(Arrays.asList(lesson), this.addonRepository.getLessons());
    }

}