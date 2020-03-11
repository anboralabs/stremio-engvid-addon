package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.data.remote.model.RenderDTO;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LessonInfoDTOMapperTest {

    private LessonInfoDTOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new LessonInfoDTOMapper();
    }

    @Test
    public void givenANullLessonInfoDTOReturnNull() {

        Assert.assertNull(mapper.apply(null));
    }

    @Test
    public void givenALessonInfoDTOReturnLessonInfo() {

        int lessonInfoId = 1;

        RenderDTO renderTitle = new RenderDTO();
        renderTitle.setRendered("<a>Title</a>");

        RenderDTO renderDescription = new RenderDTO();
        renderDescription.setRendered("<a>Description</a>");

        LessonInfoDTO lessonInfoDTO = new LessonInfoDTO();
        lessonInfoDTO.setId(lessonInfoId);
        lessonInfoDTO.setCategories(Arrays.asList(EnglishLevel.BEGINNER.getId()));
        lessonInfoDTO.setTitle(renderTitle);
        lessonInfoDTO.setContent(renderDescription);

        LessonInfo lessonInfo = LessonInfo.builder()
                .id(lessonInfoId)
                .category(EnglishLevel.BEGINNER.getId())
                .title("Title")
                .description("Description")
                .build();

        Assert.assertEquals(lessonInfo, mapper.apply(lessonInfoDTO));
    }
}