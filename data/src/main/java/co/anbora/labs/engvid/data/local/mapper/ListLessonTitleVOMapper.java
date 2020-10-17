package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonTitleVO;
import co.anbora.labs.engvid.domain.model.EnglishLevel;
import co.anbora.labs.engvid.domain.model.lesson.LessonTitle;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonTitleVOMapper implements Function<List<LessonTitleVO>, List<LessonTitle>> {
    @Override
    public List<LessonTitle> apply(List<LessonTitleVO> lessonTitleVOS) {
        if (Objects.nonNull(lessonTitleVOS)) {
            return lessonTitleVOS.stream().map(this::mapTo)
                .collect(Collectors.toList());
        }
        return null;
    }

    private LessonTitle mapTo(LessonTitleVO titleVO) {
        return LessonTitle
                .builder()
                .renderLink(titleVO.getRenderLink())
                .category(EnglishLevel.getLevel(titleVO.getCategory().intValue()))
                .build();
    }
}
