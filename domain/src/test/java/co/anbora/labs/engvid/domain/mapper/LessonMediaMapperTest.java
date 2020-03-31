package co.anbora.labs.engvid.domain.mapper;

import co.anbora.labs.engvid.domain.CommonValuesForTests;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LessonMediaMapperTest {

    private Lesson incompleteLesson;
    private LessonMedia mediaInfo;
    private LessonMediaMapper mapper = new LessonMediaMapper();

    @Before
    public void setUp() throws Exception {
        incompleteLesson = Lesson.builder()
                .id(6716L)
                .title("Learn English Tenses: FUTURE SIMPLE with “will”")
                .description("In this easy English class, you will learn to use will and won’t to talk about the future. This is called the FUTURE SIMPLE TENSE in English. I’ll show you exactly how to use it, when to use it, and what mistakes to avoid. This one English grammar class covers everything you need: structure, usage, [&hellip;]")
                .category(EnglishLevel.BEGINNER.getId().longValue())
                .date("2020-03-01T17:52:50")
                .renderLink("https://www.engvid.com/?p=6716")
                .slug("future-simple")
                .build();
        mediaInfo = LessonMedia.builder()
                .imageUrl("https://img.youtube.com/vi/tri7u632AaA/0.jpg")
                .youtubeId("tri7u632AaA")
                .sync(true)
                .build();
    }

    @Test
    public void givenLessonAndMediaReturnLessonUpdated() {
        Lesson lesson = CommonValuesForTests.provideBeginnerLesson();
        assertEquals(lesson, mapper.apply(incompleteLesson, mediaInfo));
    }

    @Test
    public void givenLessonNullAndMediaReturnNull() {
        assertNull(mapper.apply(null, mediaInfo));
    }

    @Test
    public void givenLessonAndMediaNullReturnNull() {
        assertNull(mapper.apply(incompleteLesson, null));
    }

}