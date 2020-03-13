package co.anbora.labs.engvid.data;

import co.anbora.labs.engvid.domain.mapper.LessonMediaMapper;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryImplTest {

    private IAddOnRepository addOnRepository = Mockito.mock(IAddOnRepository.class);
    private IEnglishVideoRepository englishVideoRepository = Mockito.mock(IEnglishVideoRepository.class);
    private RepositoryImpl repository;

    @Before
    public void setUp() throws Exception {

        repository = new RepositoryImpl(addOnRepository, englishVideoRepository, new LessonMediaMapper());
    }

    @Test
    public void whenLocalRepositoryHaveLessonsReturnLessons() {

        Lesson lesson = Lesson.builder()
                .id(1L)
                .title("test")
                .description("test")
                .youtubeId("youtubeId")
                .sync(true)
                .build();

        Mockito.when(addOnRepository.getLessons())
                .thenReturn(Arrays.asList(lesson));

        Assert.assertEquals(Arrays.asList(lesson), this.repository.getLessons());
    }

    @Test
    public void whenLocalRepositoryHaveNotLessonsSyncWithRemoteAndReturnLessons() {

        int lessonInfoId = 1;

        Lesson lesson = Lesson.builder()
                .id(1L)
                .title("Title")
                .description("Description")
                .youtubeId("youtubeId")
                .sync(true)
                .build();

        Mockito.when(addOnRepository.getLessons())
                .thenReturn(new ArrayList<>(), Arrays.asList(lesson));

        LessonInfo lessonInfo = LessonInfo.builder()
                .id(lessonInfoId)
                .category(EnglishLevel.BEGINNER.getId())
                .title("Title")
                .description("Description")
                .build();

        List<LessonInfo> lessonInfoList = Arrays.asList(lessonInfo);

        Mockito.when(englishVideoRepository.getLessons())
                .thenReturn(lessonInfoList);

        List<Lesson> lessons = repository.getLessons();

        Mockito.verify(addOnRepository).save(lessonInfoList);
        Assert.assertEquals(lessons, Arrays.asList(lesson));
    }

    @Test
    public void givenACategoryReturnLessonList() {

        long lessonId = 1L;

        Lesson lesson = Lesson.builder()
                .id(lessonId)
                .title("Title")
                .description("Description")
                .youtubeId("youtubeId")
                .sync(true)
                .build();

        List<Lesson> beginnerLessons = Arrays.asList(lesson);

        Mockito.when(addOnRepository.getLessonsByCategory(EnglishLevel.BEGINNER.getId()))
                .thenReturn(beginnerLessons);

        Assert.assertEquals(beginnerLessons, repository.getLessonsByCategory(EnglishLevel.BEGINNER.getId()));
    }

    @Test
    public void givenALessonIdSyncedInLocalDBReturnLesson() {

        Long lessonId = 1L;

        Lesson lesson = Lesson.builder()
                .id(lessonId)
                .title("Title")
                .description("Description")
                .youtubeId("youtubeId")
                .sync(true)
                .build();

        Mockito.when(addOnRepository.getLessonById(lessonId.intValue()))
                .thenReturn(lesson);

        Assert.assertEquals(lesson, repository.getLessonById(lessonId.intValue()));
    }

    @Test
    public void givenALessonWithoutSyncedInLocalDBSyncLessonAndReturn() {

        Long lessonId = 1L;
        String mediaSlug = "future-simple";

        Lesson lessonNotSync = Lesson.builder()
                .id(lessonId)
                .title("Title")
                .description("Description")
                .slug(mediaSlug)
                .sync(false)
                .build();

        Mockito.when(addOnRepository.getLessonById(lessonId.intValue()))
                .thenReturn(lessonNotSync);

        Lesson lessonSync = Lesson.builder()
                .id(lessonId)
                .title("Title")
                .description("Description")
                .imageUrl("https://img.youtube.com/vi/5hkutpZKkwA/0.jpg")
                .youtubeId("5hkutpZKkwA")
                .slug(mediaSlug)
                .sync(true)
                .build();


        LessonMedia media = LessonMedia.builder()
                .id(lessonId)
                .imageUrl("https://img.youtube.com/vi/5hkutpZKkwA/0.jpg")
                .youtubeId("5hkutpZKkwA")
                .sync(true)
                .build();

        Mockito.when(englishVideoRepository.getLessonMediaById(mediaSlug, lessonId))
                .thenReturn(media);

        Assert.assertEquals(lessonSync, repository.getLessonById(lessonId.intValue()));
    }
}