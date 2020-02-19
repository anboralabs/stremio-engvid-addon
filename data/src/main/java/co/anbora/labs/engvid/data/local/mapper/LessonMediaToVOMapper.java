package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonMediaVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonMedia;

import java.util.Objects;
import java.util.function.Function;

public class LessonMediaToVOMapper implements Function<LessonMedia, LessonMediaVO> {
    @Override
    public LessonMediaVO apply(LessonMedia lessonMedia) {
        if (Objects.nonNull(lessonMedia)) {
            return new LessonMediaVO(
                lessonMedia.getId(),
                lessonMedia.getImageUrl(),
                lessonMedia.getYoutubeId(),
                Objects.nonNull(lessonMedia.getImageUrl())
            );
        }
        return null;
    }
}
