package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.Objects;
import java.util.function.Function;

public class LessonInfoVOMapper implements Function<LessonInfoVO, LessonInfo> {
    @Override
    public LessonInfo apply(LessonInfoVO lessonInfoVO) {
        if (Objects.nonNull(lessonInfoVO)) {
            return LessonInfo.builder()
                    .id(lessonInfoVO.getId())
                    .title(lessonInfoVO.getTitle())
                    .description(lessonInfoVO.getDescription())
                    .date(lessonInfoVO.getDate())
                    .category(lessonInfoVO.getCategory())
                    .renderLink(lessonInfoVO.getRenderLink())
                    .build();
        }
        return null;
    }
}
