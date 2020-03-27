package co.anbora.labs.engvid.api.dto;

import co.anbora.labs.engvid.domain.model.Lesson;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.MOVIE;

public class LessonVideo extends MetaItem {
    public LessonVideo(String id, String name, String poster, String description, String background) {
        super(id, MOVIE, name, poster, description, background);
    }

    public static LessonVideo from(Lesson lesson) {
        return new LessonVideo(lesson.getInternalId(),
                lesson.getTitle(), lesson.getImageUrl(),
                lesson.getDescription(), lesson.getImageUrl());
    }
}
