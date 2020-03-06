package co.anbora.labs.engvid.data.local.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstants.DEFAULT_IMAGE;

@Getter
@AllArgsConstructor
public class LessonInfoVO {

    private Integer id;
    private String title;
    private String description;
    private Integer category;
    private String date;
    private String renderLink;
    private String slug;

}
