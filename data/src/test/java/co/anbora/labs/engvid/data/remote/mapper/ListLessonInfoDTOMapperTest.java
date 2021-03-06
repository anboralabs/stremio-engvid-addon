package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.data.remote.model.RenderDTO;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ListLessonInfoDTOMapperTest {

    private ListLessonInfoDTOMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new ListLessonInfoDTOMapper(new LessonInfoDTOMapper());
    }

    @Test
    public void givenANullListReturnNull() {

        assertNull(mapper.apply(null));
    }

    @Test
    public void givenAListLessonInfoDTOReturnListLessonInfo() {

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

        assertEquals(Arrays.asList(lessonInfo), mapper.apply(Arrays.asList(lessonInfoDTO)));
    }
}
