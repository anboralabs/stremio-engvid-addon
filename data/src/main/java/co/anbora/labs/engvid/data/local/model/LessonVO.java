package co.anbora.labs.engvid.data.local.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LessonVO {

    private Long id;
    private String title;
    private String description;
    private String date;
    private String renderLink;
    private Long category;
    private String slug;
    private String defaultImage;
    private String imageUrl;
    private String youtubeId;
    private Boolean sync;

}
