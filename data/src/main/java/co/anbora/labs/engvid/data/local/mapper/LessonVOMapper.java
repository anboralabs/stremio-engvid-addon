package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.Objects;
import java.util.function.Function;

public class LessonVOMapper implements Function<LessonVO, Lesson> {
    @Override
    public Lesson apply(LessonVO lessonVO) {
        if (Objects.nonNull(lessonVO)) {
            return Lesson.builder()
                    .id(lessonVO.getId())
                    .title(lessonVO.getTitle())
                    .description(lessonVO.getDescription())
                    .date(lessonVO.getDate())
                    .category(lessonVO.getCategory())
                    .imageUrl(lessonVO.getImageUrl())
                    .renderLink(lessonVO.getRenderLink())
                    .slug(lessonVO.getSlug())
                    .youtubeId(lessonVO.getYoutubeId())
                    //.sync(Objects.nonNull(lessonVO.getYoutubeId()))
                    .build();
        }
        return null;
    }
}
