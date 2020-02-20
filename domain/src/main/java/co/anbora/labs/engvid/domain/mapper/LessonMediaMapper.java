package co.anbora.labs.engvid.domain.mapper;

import co.anbora.labs.engvid.domain.model.Lesson;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;

import java.util.Objects;
import java.util.function.BiFunction;

public class LessonMediaMapper implements BiFunction<Lesson, LessonMedia, Lesson> {
    @Override
    public Lesson apply(Lesson lesson, LessonMedia lessonMedia) {
        if (Objects.nonNull(lesson) && Objects.nonNull(lessonMedia)) {
            return Lesson.builder()
                    .id(lesson.getId())
                    .title(lesson.getTitle())
                    .description(lesson.getDescription())
                    .category(lesson.getCategory())
                    .date(lesson.getDate())
                    .renderLink(lesson.getRenderLink())
                    .slug(lesson.getSlug())
                    .imageUrl(lessonMedia.getImageUrl())
                    .youtubeId(lessonMedia.getYoutubeId())
                    .sync(Objects.nonNull(lessonMedia.getYoutubeId()))
                    .build();
        }
        return null;
    }
}
