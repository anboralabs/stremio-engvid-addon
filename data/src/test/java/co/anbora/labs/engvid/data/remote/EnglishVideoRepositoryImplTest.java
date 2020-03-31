package co.anbora.labs.engvid.data.remote;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.LessonInfoDTOMapper;
import co.anbora.labs.engvid.data.remote.mapper.ListLessonInfoDTOMapper;
import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.data.remote.model.RenderDTO;
import co.anbora.labs.engvid.domain.exceptions.LessonNotFoundException;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EnglishVideoRepositoryImplTest {

    private EnglishVideoAPI api = Mockito.mock(EnglishVideoAPI.class);
    private EnglishVideoRepositoryImpl repository;
    private ListLessonInfoDTOMapper listLessonInfoDTOMapper;
    private HtmlToLessonMediaMapper htmlToLessonMediaMapper;

    @Before
    public void setUp() throws Exception {

        htmlToLessonMediaMapper = new HtmlToLessonMediaMapper();
        listLessonInfoDTOMapper = new ListLessonInfoDTOMapper(new LessonInfoDTOMapper());
        repository = new EnglishVideoRepositoryImpl(api, listLessonInfoDTOMapper, htmlToLessonMediaMapper);
    }

    @Test
    public void givenARequestAllLessonReturnEmptyLessons() {

        Mockito.when(api.getLessonsByPage(Mockito.anyInt(), Mockito.anyInt()))
                .thenThrow(new RuntimeException());

        assertTrue(repository.getLessons().isEmpty());
    }

    @Test
    public void givenARequestAllLessonReturnLessonsAvailable() throws IOException {

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

        int lessonInfoId2 = 2;
        RenderDTO renderTitle2 = new RenderDTO();
        renderTitle2.setRendered("<a>Title2</a>");
        RenderDTO renderDescription2 = new RenderDTO();
        renderDescription2.setRendered("<a>Description2</a>");
        LessonInfoDTO lessonInfoDTO2 = new LessonInfoDTO();
        lessonInfoDTO2.setId(lessonInfoId2);
        lessonInfoDTO2.setCategories(Arrays.asList(EnglishLevel.BEGINNER.getId()));
        lessonInfoDTO2.setTitle(renderTitle2);
        lessonInfoDTO2.setContent(renderDescription2);

        List<LessonInfoDTO> list1Page = new ArrayList<>();
        list1Page.add(lessonInfoDTO);

        List<LessonInfoDTO> list2Page = new ArrayList<>();
        list2Page.add(lessonInfoDTO2);

        List<LessonInfoDTO> list3Page = new ArrayList<>();

        Mockito.when(api.getLessonsByPage(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(list1Page, list2Page, list3Page);

        LessonInfo lessonInfo = LessonInfo.builder()
                .id(lessonInfoId)
                .category(EnglishLevel.BEGINNER.getId())
                .title("Title")
                .description("Description")
                .build();

        LessonInfo lessonInfo2 = LessonInfo.builder()
                .id(lessonInfoId2)
                .category(EnglishLevel.BEGINNER.getId())
                .title("Title2")
                .description("Description2")
                .build();

        assertEquals(Arrays.asList(lessonInfo, lessonInfo2), repository.getLessons());
    }

    @Test(expected = LessonNotFoundException.class)
    public void givenAnInvalidSlugThrowsLessonNotFoundException() throws IOException {

        long invalidId = -1;

        Request request = new Request.Builder().url("http://localhost").build();

        Response errorCode = new Response.Builder().request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .code(404).build();

        Mockito.when(api.getMediaInfoBySlug("mock-lesson"))
                .thenReturn(errorCode);

        repository.getLessonMediaById("mock-lesson", invalidId);
    }

    @Test
    public void givenAValidSlugReturnLessonMedia() throws IOException, URISyntaxException {

        long lessonId = 6716L;
        String mediaSlug = "future-simple";

        ClassLoader classLoader = this.getClass().getClassLoader();
        String html = Files.lines(Paths.get(classLoader.getResource("lesson_media.html").toURI()))
                .collect(Collectors.joining());

        Request request = new Request.Builder().url("http://localhost").build();

        Response okResponse = new Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .code(200)
                .body(ResponseBody.create(MediaType.get("application/text"), html))
                .build();

        Mockito.when(api.getMediaInfoBySlug(mediaSlug))
                .thenReturn(okResponse);

        LessonMedia media = LessonMedia.builder()
                .id(lessonId)
                .imageUrl("https://img.youtube.com/vi/5hkutpZKkwA/0.jpg")
                .youtubeId("5hkutpZKkwA")
                .sync(true)
                .build();

        assertEquals(media, repository.getLessonMediaById(mediaSlug, lessonId));
    }

}