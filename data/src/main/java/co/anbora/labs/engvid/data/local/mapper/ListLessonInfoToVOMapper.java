package co.anbora.labs.engvid.data.local.mapper;

import co.anbora.labs.engvid.data.local.model.LessonInfoVO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonInfoToVOMapper implements Function<List<LessonInfo>, List<LessonInfoVO>> {

    private Function<LessonInfo, LessonInfoVO> mapper;

    public ListLessonInfoToVOMapper(Function<LessonInfo, LessonInfoVO> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LessonInfoVO> apply(List<LessonInfo> lessonInfos) {
        if (Objects.nonNull(lessonInfos)) {
            return lessonInfos.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
