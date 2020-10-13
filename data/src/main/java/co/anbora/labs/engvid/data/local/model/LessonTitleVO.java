package co.anbora.labs.engvid.data.local.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class LessonTitleVO {

    private String slug;
    private Long category;

}
