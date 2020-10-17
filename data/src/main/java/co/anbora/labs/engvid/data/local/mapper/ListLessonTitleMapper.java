package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonTitleVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonTitleMapper implements Function<List<LessonTitle>, List<LessonTitleVO>> {
    @Override
    public List<LessonTitleVO> apply(List<LessonTitle> lessonTitles) {
        if (Objects.nonNull(lessonTitles)) {
            return lessonTitles.stream().map(this::mapToVO)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private LessonTitleVO mapToVO(LessonTitle title) {
        return new LessonTitleVO(
                title.getSlug(),
                title.getRenderLink(),
                title.getCategory().getId().longValue()
        );
    }
}
