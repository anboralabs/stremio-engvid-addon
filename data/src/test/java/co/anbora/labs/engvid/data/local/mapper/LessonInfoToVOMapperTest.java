package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LessonInfoToVOMapperTest {

    private LessonInfoToVOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new LessonInfoToVOMapper();
    }

    @Test
    public void givenALessonInfoNullReturnNull() {

        assertNull(mapper.apply(null));
    }

    @Test
    public void givenALessonInfoReturnLessonInfoVO() {

        LessonInfo lessonInfo = LessonInfo.builder()
                .id(1)
                .title("test")
                .description("test")
                .build();

        LessonInfoVO lessonInfoVO = new LessonInfoVO(
            1, "test", "test", null, null, null, null
        );

        assertEquals(lessonInfoVO, mapper.apply(lessonInfo));
    }
}