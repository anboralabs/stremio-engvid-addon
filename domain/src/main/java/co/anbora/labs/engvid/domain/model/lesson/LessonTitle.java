package co.anbora.labs.engvid.domain.model.lesson;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class LessonTitle {

    private String slug;
    private EnglishLevel category;

}
