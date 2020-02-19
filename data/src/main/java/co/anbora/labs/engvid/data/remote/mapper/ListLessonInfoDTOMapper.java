package co.anbora.labs.engvid.data.remote.mapper;

import co.anbora.labs.engvid.data.remote.model.LessonInfoDTO;
import co.anbora.labs.engvid.domain.model.lesson.LessonInfo;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListLessonInfoDTOMapper implements Function<List<LessonInfoDTO>, List<LessonInfo>> {

    private Function<LessonInfoDTO, LessonInfo> mapper;

    public ListLessonInfoDTOMapper(Function<LessonInfoDTO, LessonInfo> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LessonInfo> apply(List<LessonInfoDTO> lessonInfoDTOS) {
        if (Objects.nonNull(lessonInfoDTOS)) {
            return lessonInfoDTOS.stream()
                    .map(mapper)
                    .collect(Collectors.toList());
        }
        return null;
    }
}