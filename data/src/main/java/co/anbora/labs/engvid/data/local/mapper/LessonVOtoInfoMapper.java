package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.Objects;
import java.util.function.Function;

public class LessonVOtoInfoMapper implements Function<LessonVO, LessonInfo> {
    @Override
    public LessonInfo apply(LessonVO lessonVO) {
        if (Objects.nonNull(lessonVO)) {
            return LessonInfo.builder()
                    .id(lessonVO.getId())
                    .title(lessonVO.getTitle())
                    .description(lessonVO.getDescription())
                    .slug(lessonVO.getSlug())
                    .renderLink(lessonVO.getRenderLink())
                    .category(lessonVO.getCategory())
                    .date(lessonVO.getDate())
                    .build();
        }
        return null;
    }
}
