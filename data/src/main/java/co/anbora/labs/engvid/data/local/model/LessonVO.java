package co.anbora.labs.engvid.data.local.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LessonVO {

    private Integer id;
    private String title;
    private String description;
    private Integer category;
    private String date;
    private String renderLink;
    private String slug;
    private String imageUrl;
    private String youtubeId;
    private Boolean sync;

    public boolean isSync() {
        return sync;
    }

}
