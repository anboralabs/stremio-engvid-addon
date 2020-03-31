package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class LessonVOMapperTest {

    private LessonVOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new LessonVOMapper();
    }

    @Test
    public void givenALessonVONullReturnNull() {

        assertNull(mapper.apply(null));
    }

    @Test
    public void givenALessonVOWithOutYoutubeIDReturnLessonNotSync() {

        LessonVO lessonVO = new LessonVO(
            1L,  "test", "test",
                null, null, null, null,
                null, null, null
        );

        Lesson lesson = Lesson.builder()
                .id(1L)
                .title("test")
                .description("test")
                .sync(false)
                .build();

        assertEquals(lesson, mapper.apply(lessonVO));
    }

    @Test
    public void givenALessonVOWithYoutubeIDReturnLessonSync() {

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

        assertEquals(lesson, mapper.apply(lessonVO));
    }


}