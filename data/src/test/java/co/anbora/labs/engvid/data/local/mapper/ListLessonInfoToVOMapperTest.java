package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ListLessonInfoToVOMapperTest {

    private ListLessonInfoToVOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new ListLessonInfoToVOMapper(new LessonInfoToVOMapper());
    }

    @Test
    public void givenAListLessonInfoNullReturnNull() {

        assertNull(mapper.apply(null));
    }

    @Test
    public void givenAListLessonReturnListLessonVO() {

        LessonInfo lessonInfo = LessonInfo.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();

        LessonInfoVO lessonInfoVO = new LessonInfoVO(
                1, "test", "test", null, null, null, null
        );

        assertEquals(Arrays.asList(lessonInfoVO), mapper.apply(Arrays.asList(lessonInfo)));
    }
}