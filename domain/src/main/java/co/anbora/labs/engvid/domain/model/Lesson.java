package co.anbora.labs.engvid.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

import static co.anbora.labs.engvid.domain.constants.StremioConstantsHelper.StremioCatalog.VIDEO_PREFIX_ID;

@Getter
@Builder
@EqualsAndHashCode
public class Lesson {

    private Long id;
    private String title;
    private String description;
    private Long category;
    private String date;
    private String renderLink;
    private String slug;
    private String imageUrl;
    private String youtubeId;

    public String getInternalId() {
        return VIDEO_PREFIX_ID + getId();
    }

    public boolean isSync() {
        return Objects.nonNull(youtubeId);
    }

}
