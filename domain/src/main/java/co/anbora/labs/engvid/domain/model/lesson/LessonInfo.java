package co.anbora.labs.engvid.domain.model.lesson;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LessonInfo {

    private Integer id;
    private String title;
    private String description;
    private Integer category;
    private String date;
    private String renderLink;

}
