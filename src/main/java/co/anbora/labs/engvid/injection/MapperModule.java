package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.mapper.LessonVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonVOMapper;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class MapperModule {


    @Produces
    public LessonVOMapper provideLessonVOMapper() {
        return new LessonVOMapper();
    }

    @Produces
    public HtmlToLessonMediaMapper provideHtmlToLessonMediaMapper() {
        return new HtmlToLessonMediaMapper();
    }

    @Produces
    public ListLessonVOMapper provideListLessonVOMapper(LessonVOMapper lessonVOMapper) {
        return new ListLessonVOMapper(lessonVOMapper);
    }
}
