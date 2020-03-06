package co.anbora.labs.engvid.data.local.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class LessonMediaVO {

    private Long id;
    private String imageUrl;
    private String youtubeId;
    private Boolean sync;

}
