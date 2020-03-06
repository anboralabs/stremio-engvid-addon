package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LessonMediaToVOMapperTest {

    private LessonMediaToVOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new LessonMediaToVOMapper();
    }

    @Test
    public void givenALessonMediaNullReturnNull() {

        Assert.assertNull(mapper.apply(null));
    }

    @Test
    public void givenALessonMediaReturnLessonMediaVO() {

        LessonMedia lessonMedia = LessonMedia.builder()
                .id(1L)
                .youtubeId("youtubeId")
                .build();

        LessonMediaVO lessonMediaVO = new LessonMediaVO(1L, null, "youtubeId", false);

        Assert.assertEquals(lessonMediaVO, mapper.apply(lessonMedia));
    }

    @Test
    public void givenALessonMediaReturnLessonMediaVOSync() {

        LessonMedia lessonMedia = LessonMedia.builder()
                .id(1L)
                .imageUrl("image.jpg")
                .youtubeId("youtubeId")
                .build();

        LessonMediaVO lessonMediaVO = new LessonMediaVO(1L, "image.jpg", "youtubeId", true);

        Assert.assertEquals(lessonMediaVO, mapper.apply(lessonMedia));
    }
}