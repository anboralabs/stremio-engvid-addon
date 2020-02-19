package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonInfoVOMapper implements Function<List<LessonInfoVO>, List<LessonInfo>> {

    private Function<LessonInfoVO, LessonInfo> lessonInfoVOMapper;

    public ListLessonInfoVOMapper(Function<LessonInfoVO, LessonInfo> lessonInfoVOMapper) {
        this.lessonInfoVOMapper = lessonInfoVOMapper;
    }

    @Override
    public List<LessonInfo> apply(List<LessonInfoVO> lessonInfoVOS) {
        if (Objects.nonNull(lessonInfoVOS)) {
            return lessonInfoVOS.stream()
                    .map(lessonInfoVOMapper)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
