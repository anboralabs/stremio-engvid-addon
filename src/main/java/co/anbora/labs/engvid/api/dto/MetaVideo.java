package co.anbora.labs.engvid.api.dto;

import co.anbora.labs.engvid.domain.model.LessonVideo;

public class MetaVideo {

    private LessonVideo meta;

    public MetaVideo(LessonVideo meta) {
        this.meta = meta;
    }

    public LessonVideo getMeta() {
        return meta;
    }

    public static MetaVideo from(LessonVideo lesson) {
        return new MetaVideo(lesson);
    }
}
