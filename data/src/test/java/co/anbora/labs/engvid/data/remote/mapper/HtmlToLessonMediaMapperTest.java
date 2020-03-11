package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class HtmlToLessonMediaMapperTest {

    public static final long LESSON_ID = 1;
    private HtmlToLessonMediaMapper mapper;

    @Before
    public void setUp() throws Exception {

        mapper = new HtmlToLessonMediaMapper();
    }

    @Test
    public void givenAHtmlNullReturnNull() {

        Assert.assertNull(mapper.apply(null, LESSON_ID));
    }

    @Test
    public void givenAValidHtmlMediaInfoReturnLessonMedia() throws URISyntaxException, IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String html = Files.lines(Paths.get(classLoader.getResource("lesson_media.html").toURI()))
                .collect(Collectors.joining());

        LessonMedia media = LessonMedia.builder()
                .id(LESSON_ID)
                .imageUrl("https://img.youtube.com/vi/5hkutpZKkwA/0.jpg")
                .youtubeId("5hkutpZKkwA")
                .sync(true)
                .build();

        Assert.assertEquals(media , mapper.apply(html, LESSON_ID));
    }
}