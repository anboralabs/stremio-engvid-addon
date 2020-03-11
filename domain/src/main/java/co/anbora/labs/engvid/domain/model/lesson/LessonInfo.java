package co.anbora.labs.engvid.domain.model.lesson;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class LessonInfo {

    private Integer id;
    private String title;
    private String description;
    private Integer category;
    private String date;
    private String renderLink;
    private String slug;

}
