package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.mapper.*;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.LessonInfoDTOMapper;
import co.anbora.labs.engvid.data.remote.mapper.ListLessonInfoDTOMapper;
import co.anbora.labs.engvid.domain.mapper.LessonMediaMapper;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Dependent
public class MapperModule {

    @Produces
    LessonMediaToVOMapper provideLessonMediaToVOMapper() {
        return new LessonMediaToVOMapper();
    }

    @Produces
    LessonInfoToVOMapper provideLessonInfoToVOMapper() {
        return new LessonInfoToVOMapper();
    }

    @Produces
    ListLessonInfoToVOMapper provideListLessonInfoToVOMapper(LessonInfoToVOMapper mapper) {
        return new ListLessonInfoToVOMapper(mapper);
    }

    @Produces
    LessonVOMapper provideLessonVOMapper() {
        return new LessonVOMapper();
    }

    @Produces
    LessonInfoDTOMapper provideLessonInfoDTOMapper() {
        return new LessonInfoDTOMapper();
    }

    @Produces
    ListLessonInfoDTOMapper provideListLessonInfoDTOMapper(LessonInfoDTOMapper mapper) {
        return new ListLessonInfoDTOMapper(mapper);
    }

    @Produces
    HtmlToLessonMediaMapper provideHtmlToLessonMediaMapper() {
        return new HtmlToLessonMediaMapper();
    }

    @Produces
    ListLessonVOMapper provideListLessonVOMapper(LessonVOMapper lessonVOMapper) {
        return new ListLessonVOMapper(lessonVOMapper);
    }

    @Produces
    LessonMediaMapper provideLessonMediaMapper() {
        return new LessonMediaMapper();
    }

}
