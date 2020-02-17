package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.data.local.model.LessonVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonVOMapper implements Function<List<LessonVO>, List<LessonInfo>> {

    private Function<LessonVO, LessonInfo> mapper;

    public ListLessonVOMapper(Function<LessonVO, LessonInfo> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LessonInfo> apply(List<LessonVO> lessonVOS) {
        List<LessonInfo> lessons = new ArrayList<>();
        if (Objects.nonNull(lessonVOS)) {
            lessons = lessonVOS.stream().map(mapper).collect(Collectors.toList());
        }
        return lessons;
    }
}
