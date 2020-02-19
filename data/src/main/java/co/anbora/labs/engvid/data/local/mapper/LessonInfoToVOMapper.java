package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.Objects;
import java.util.function.Function;

public class LessonInfoToVOMapper implements Function<LessonInfo, LessonInfoVO> {
    @Override
    public LessonInfoVO apply(LessonInfo lessonInfo) {
        if (Objects.nonNull(lessonInfo)) {
            return new LessonInfoVO(
                lessonInfo.getId(),
                lessonInfo.getTitle(),
                lessonInfo.getDescription(),
                lessonInfo.getCategory(),
                lessonInfo.getDate(),
                lessonInfo.getRenderLink(),
                lessonInfo.getSlug()
            );
        }
        return null;
    }
}
