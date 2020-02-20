package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.Objects;
import java.util.function.Function;

public class LessonVOMapper implements Function<LessonVO, Lesson> {
    @Override
    public Lesson apply(LessonVO lessonVO) {
        if (Objects.nonNull(lessonVO)) {
            String imageUrl = lessonVO.getImageUrl() != null ?
                    lessonVO.getImageUrl() : lessonVO.getDefaultImage();
            return Lesson.builder()
                    .id(lessonVO.getId())
                    .title(lessonVO.getTitle())
                    .description(lessonVO.getDescription())
                    .date(lessonVO.getDate())
                    .category(lessonVO.getCategory())
                    .imageUrl(imageUrl)
                    .renderLink(lessonVO.getRenderLink())
                    .slug(lessonVO.getSlug())
                    .youtubeId(lessonVO.getYoutubeId())
                    .sync(Objects.nonNull(lessonVO.getYoutubeId()))
                    .build();
        }
        return null;
    }
}
