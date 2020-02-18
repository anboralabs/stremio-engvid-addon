package co.anbora.labs.engvid.domain.model.lesson;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LessonMedia {

    private Integer id;
    private String imageUrl;
    private String youtubeId;
    private Boolean sync;

}
