package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.mapper.LessonVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonTitleMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonTitleVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonVOMapper;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonTitleMapper;

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
    public HtmlToLessonTitleMapper provideHtmlToLessonTitleMapper() {
        return new HtmlToLessonTitleMapper();
    }

    @Produces
    public ListLessonVOMapper provideListLessonVOMapper(LessonVOMapper lessonVOMapper) {
        return new ListLessonVOMapper(lessonVOMapper);
    }

    @Produces
    public ListLessonMapper provideListLessonMapper() {
        return new ListLessonMapper();
    }

    @Produces
    public ListLessonTitleMapper provideListLessonTitleMapper() {
        return new ListLessonTitleMapper();
    }

    @Produces
    public ListLessonTitleVOMapper provideListLessonTitleVOMapper() {
        return new ListLessonTitleVOMapper();
    }
}
