package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.mapper.LessonInfoToVOMapper;
import co.anbora.labs.engvid.data.local.mapper.LessonMediaToVOMapper;
import co.anbora.labs.engvid.data.local.mapper.LessonVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonInfoToVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonVOMapper;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.LessonInfoDTOMapper;
import co.anbora.labs.engvid.data.remote.mapper.ListLessonInfoDTOMapper;
import co.anbora.labs.engvid.domain.mapper.LessonMediaMapper;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class MapperModule {

    @Produces
    public LessonMediaToVOMapper provideLessonMediaToVOMapper() {
        return new LessonMediaToVOMapper();
    }

    @Produces
    public LessonInfoToVOMapper provideLessonInfoToVOMapper() {
        return new LessonInfoToVOMapper();
    }

    @Produces
    public ListLessonInfoToVOMapper provideListLessonInfoToVOMapper(LessonInfoToVOMapper mapper) {
        return new ListLessonInfoToVOMapper(mapper);
    }

    @Produces
    public LessonVOMapper provideLessonVOMapper() {
        return new LessonVOMapper();
    }

    @Produces
    public LessonInfoDTOMapper provideLessonInfoDTOMapper() {
        return new LessonInfoDTOMapper();
    }

    @Produces
    public ListLessonInfoDTOMapper provideListLessonInfoDTOMapper(LessonInfoDTOMapper mapper) {
        return new ListLessonInfoDTOMapper(mapper);
    }

    @Produces
    public HtmlToLessonMediaMapper provideHtmlToLessonMediaMapper() {
        return new HtmlToLessonMediaMapper();
    }

    @Produces
    public ListLessonVOMapper provideListLessonVOMapper(LessonVOMapper lessonVOMapper) {
        return new ListLessonVOMapper(lessonVOMapper);
    }

    @Produces
    public LessonMediaMapper provideLessonMediaMapper() {
        return new LessonMediaMapper();
    }

}
