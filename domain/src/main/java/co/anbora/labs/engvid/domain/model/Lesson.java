package co.anbora.labs.engvid.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Lesson {

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

    public Boolean isSync() {
        return sync && Objects.nonNull(youtubeId);
    }

}
