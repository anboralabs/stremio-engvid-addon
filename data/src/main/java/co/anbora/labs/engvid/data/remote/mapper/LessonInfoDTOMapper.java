package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Objects;
import java.util.function.Function;

public class LessonInfoDTOMapper implements Function<LessonInfoDTO, LessonInfo> {
    @Override
    public LessonInfo apply(LessonInfoDTO lessonInfoDTO) {
        if (Objects.nonNull(lessonInfoDTO)) {
            return LessonInfo.builder()
                    .id(lessonInfoDTO.getId())
                    .title(StringEscapeUtils.unescapeHtml4(lessonInfoDTO.getTitle().getRendered()))
                    .description(StringEscapeUtils.unescapeHtml4(lessonInfoDTO.getContent().getRendered()))
                    .category(lessonInfoDTO.getEnglishLevel())
                    .date(lessonInfoDTO.getDate())
                    .slug(lessonInfoDTO.getSlug())
                    .renderLink(lessonInfoDTO.getLink())
                    .build();
        }
        return null;
    }
}
