package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;

import java.util.Objects;
import java.util.function.Function;

public class LessonInfoDTOtoVO implements Function<LessonInfoDTO, LessonInfoVO> {
    @Override
    public LessonInfoVO apply(LessonInfoDTO lessonInfoDTO) {
        if (Objects.nonNull(lessonInfoDTO)) {
            return new LessonInfoVO(
                lessonInfoDTO.getId(),
                lessonInfoDTO.getTitle().getRendered(),
                lessonInfoDTO.getContent().getRendered(),
                lessonInfoDTO.getEnglishLevel(),
                lessonInfoDTO.getDate(),
                lessonInfoDTO.getLink()
            );
        }
        return null;
    }
}
