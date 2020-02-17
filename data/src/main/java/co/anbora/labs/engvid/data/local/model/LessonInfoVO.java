package co.anbora.labs.engvid.data.local.model;

import lombok.Getter;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.DEFAULT_IMAGE;

@Getter
public class LessonInfoVO {

    private Integer id;
    private String title;
    private String description;
    private Integer category;
    private String date;
    private String renderLink;
    private String slug;
    private String imageUrl;

    public LessonInfoVO(Integer id,
                        String title,
                        String description, Integer category,
                        String date, String renderLink, String slug) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.renderLink = renderLink;
        this.slug = slug;
        this.imageUrl = DEFAULT_IMAGE;
    }
}
