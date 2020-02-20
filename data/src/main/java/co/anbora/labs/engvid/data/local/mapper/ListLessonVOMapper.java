package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.Lesson;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonVOMapper implements Function<List<LessonVO>, List<Lesson>> {

    private Function<LessonVO, Lesson> mapper;

    public ListLessonVOMapper(Function<LessonVO, Lesson> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Lesson> apply(List<LessonVO> lessonVOS) {
        if (Objects.nonNull(lessonVOS)) {
            return lessonVOS.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
