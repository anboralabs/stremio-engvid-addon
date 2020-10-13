package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonMapper implements Function<List<Lesson>, List<LessonVO>> {
    @Override
    public List<LessonVO> apply(List<Lesson> lessons) {
        if (Objects.nonNull(lessons)) {
            return lessons.stream().map(this::mapToVO)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private LessonVO mapToVO(Lesson lesson) {
        return new LessonVO(
            lesson.getId(),
            lesson.getTitle(),
            lesson.getDescription(),
            lesson.getDate(),
            lesson.getRenderLink(),
            lesson.getCategory(),
            lesson.getSlug(),
            lesson.getImageUrl(),
            lesson.getYoutubeId(),
            true
        );
    }
}
