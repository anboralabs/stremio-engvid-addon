package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;

import java.util.Objects;
import java.util.function.Function;

public class LessonVOtoMedia implements Function<LessonVO, LessonMedia> {
    @Override
    public LessonMedia apply(LessonVO lessonVO) {
        if (Objects.nonNull(lessonVO)) {
            return LessonMedia.builder()
                    .id(lessonVO.getId())
                    .imageUrl(lessonVO.getImageUrl())
                    .youtubeId(lessonVO.getYoutubeId())
                    .sync(lessonVO.getSync())
                    .build();
        }
        return null;
    }
}
