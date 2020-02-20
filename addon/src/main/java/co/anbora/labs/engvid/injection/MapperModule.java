package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.local.mapper.*;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.LessonInfoDTOMapper;
import co.anbora.labs.engvid.data.remote.mapper.ListLessonInfoDTOMapper;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class MapperModule {

    @Bean
    @Singleton
    LessonMediaToVOMapper provideLessonMediaToVOMapper() {
        return new LessonMediaToVOMapper();
    }

    @Bean
    @Singleton
    LessonInfoToVOMapper provideLessonInfoToVOMapper() {
        return new LessonInfoToVOMapper();
    }

    @Bean
    @Singleton
    ListLessonInfoToVOMapper provideListLessonInfoToVOMapper(LessonInfoToVOMapper mapper) {
        return new ListLessonInfoToVOMapper(mapper);
    }

    @Bean
    @Singleton
    LessonVOMapper provideLessonVOMapper() {
        return new LessonVOMapper();
    }

    @Bean
    @Singleton
    LessonInfoDTOMapper provideLessonInfoDTOMapper() {
        return new LessonInfoDTOMapper();
    }

    @Bean
    @Singleton
    ListLessonInfoDTOMapper provideListLessonInfoDTOMapper(LessonInfoDTOMapper mapper) {
        return new ListLessonInfoDTOMapper(mapper);
    }

    @Bean
    @Singleton
    HtmlToLessonMediaMapper provideHtmlToLessonMediaMapper() {
        return new HtmlToLessonMediaMapper();
    }

    @Bean
    @Singleton
    ListLessonVOMapper provideListLessonVOMapper(LessonVOMapper lessonVOMapper) {
        return new ListLessonVOMapper(lessonVOMapper);
    }

}
