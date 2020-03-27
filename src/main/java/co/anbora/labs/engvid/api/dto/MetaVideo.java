package co.anbora.labs.engvid.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaVideo {

    @JsonProperty("meta")
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
