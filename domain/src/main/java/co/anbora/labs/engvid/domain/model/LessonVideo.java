package co.anbora.labs.engvid.domain.model;

import co.anbora.labs.engvid.domain.model.stremio.MetaItem;

import static co.anbora.labs.engvid.domain.constants.StremioConstants.StremioCatalog.MOVIE;

public class LessonVideo extends MetaItem {
    public LessonVideo(String id, String name, String poster, String description, String background) {
        super(id, MOVIE, name, poster, description, background);
    }

    public static LessonVideo from(Lesson lesson) {
        return new LessonVideo(String.valueOf(lesson.getId()),
                lesson.getTitle(), lesson.getImageUrl(),
                lesson.getDescription(), lesson.getImageUrl());
    }
}
