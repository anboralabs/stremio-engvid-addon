package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class LessonMediaToVOMapperTest {

    private LessonMediaToVOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new LessonMediaToVOMapper();
    }

    @Test
    public void givenALessonMediaNullReturnNull() {

        assertNull(mapper.apply(null));
    }

    @Test
    public void givenALessonMediaWithoutImageReturnLessonMediaVONotSync() {

        LessonMedia lessonMedia = LessonMedia.builder()
                .id(1L)
                .youtubeId("youtubeId")
                .build();

        LessonMediaVO lessonMediaVO = new LessonMediaVO(1L, null, "youtubeId", false);

        assertEquals(lessonMediaVO, mapper.apply(lessonMedia));
    }

    @Test
    public void givenALessonMediaWithImageReturnLessonMediaVOSync() {

        LessonMedia lessonMedia = LessonMedia.builder()
                .id(1L)
                .imageUrl("image.jpg")
                .youtubeId("youtubeId")
                .build();

        LessonMediaVO lessonMediaVO = new LessonMediaVO(1L, "image.jpg", "youtubeId", true);

        assertEquals(lessonMediaVO, mapper.apply(lessonMedia));
    }
}