package co.anbora.labs.engvid.data.local;

import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.mapper.LessonInfoToVOMapper;
import co.anbora.labs.engvid.data.local.mapper.LessonMediaToVOMapper;
import co.anbora.labs.engvid.data.local.mapper.LessonVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonInfoToVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonVOMapper;
import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
        Mockito.verify(lessonDao).updateMedia(lessonMediaVO);
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

        assertEquals(Arrays.asList(lesson), this.addonRepository.getLessons());
    }

    @Test
    public void getLessonsByCategoryFromLocalDB() {
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

        Mockito.when(lessonDao.findAllByCategory(EnglishLevel.BEGINNER.getId()))
                .thenReturn(Arrays.asList(lessonVO));

        assertEquals(Arrays.asList(lesson), this.addonRepository.getLessonsByCategory(EnglishLevel.BEGINNER.getId()));
    }

    @Test
    public void givenAValidIdLessonReturnLesson() {

        int idLesson = 1;

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

        Mockito.when(lessonDao.findById(idLesson))
                .thenReturn(lessonVO);
        assertEquals(lesson, this.addonRepository.getLessonById(idLesson));
    }

    @Test
    public void givenAnInvalidIdLessonReturnNull() {

        int invalidId = 10;

        Mockito.when(lessonDao.findById(Mockito.anyInt()))
                .thenReturn(null);
        assertNull(this.addonRepository.getLessonById(invalidId));
    }

}
