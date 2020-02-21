package co.anbora.labs.engvid.domain.model.stremio;

import co.anbora.labs.engvid.domain.model.LessonVideo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MetaVideo {

    private LessonVideo meta;

    public static MetaVideo from(LessonVideo lesson) {
        return new MetaVideo(lesson);
    }
}
