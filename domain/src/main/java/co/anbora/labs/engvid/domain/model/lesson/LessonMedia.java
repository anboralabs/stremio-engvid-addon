package co.anbora.labs.engvid.domain.model.lesson;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
@EqualsAndHashCode
public class LessonMedia {

    private Long id;
    private String imageUrl;
    private String youtubeId;
    private Boolean sync;

    public boolean isSync() {
        return Objects.nonNull(youtubeId);
    }

}
