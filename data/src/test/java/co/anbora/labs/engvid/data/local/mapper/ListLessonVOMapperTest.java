package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ListLessonVOMapperTest {

    private ListLessonVOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new ListLessonVOMapper(new LessonVOMapper());
    }

    @Test
    public void givenAListLessonNullReturnNull() {

        Assert.assertNull(mapper.apply(null));
    }

    @Test
    public void givenAListLessonVOReturnListLesson() {
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

        Assert.assertEquals(Arrays.asList(lesson), mapper.apply(Arrays.asList(lessonVO)));
    }
}