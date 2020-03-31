package co.anbora.labs.engvid.api;

import co.anbora.labs.engvid.api.dto.LessonVideo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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
