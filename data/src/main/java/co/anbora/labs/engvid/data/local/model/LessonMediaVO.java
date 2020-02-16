package co.anbora.labs.engvid.data.local.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LessonMediaVO {

    private Integer id;
    private String imageUrl;
    private String youtubeId;
    private Boolean sync;

}
