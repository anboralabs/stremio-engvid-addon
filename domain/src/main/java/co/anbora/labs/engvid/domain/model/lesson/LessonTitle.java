package co.anbora.labs.engvid.domain.model.lesson;

import co.anbora.labs.engvid.domain.model.EnglishLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static co.anbora.labs.engvid.domain.constants.ConstantsHelper.EMPTY_VALUE;
import static co.anbora.labs.engvid.domain.constants.ConstantsHelper.SLASH;
import static co.anbora.labs.engvid.domain.constants.EnglishVideoConstantsHelper.ENG_VID_URL;

@Getter
@Builder
@EqualsAndHashCode
public class LessonTitle {

    private String renderLink;
    private EnglishLevel category;

    public String getSlug() {
        return renderLink.replace(ENG_VID_URL, EMPTY_VALUE).replace(SLASH, EMPTY_VALUE);
    }

}
