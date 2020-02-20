package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.RepositoryImpl;
import co.anbora.labs.engvid.data.local.AddonRepositoryImpl;
import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.mapper.*;
import co.anbora.labs.engvid.data.remote.EnglishVideoRepositoryImpl;
import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.ListLessonInfoDTOMapper;
import co.anbora.labs.engvid.domain.mapper.LessonMediaMapper;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class DataModule {

    @Bean
    @Singleton
    IAddOnRepository provideLocalRepository(LessonMediaToVOMapper lessonMediaToVOMapper,
                                            ListLessonInfoToVOMapper listLessonInfoToVOMapper,
                                            LessonVOMapper lessonVOMapper,
                                            ListLessonVOMapper listLessonVOMapper,
                                            LessonDao lessonDao) {
        return new AddonRepositoryImpl(lessonMediaToVOMapper, listLessonInfoToVOMapper,
                lessonVOMapper, listLessonVOMapper, lessonDao);
    }

    @Bean
    @Singleton
    IEnglishVideoRepository provideRemoteRepository(EnglishVideoAPI englishVideoAPI,
                                                    ListLessonInfoDTOMapper listLessonInfoDTOMapper,
                                                    HtmlToLessonMediaMapper htmlMediaMapper) {
        return new EnglishVideoRepositoryImpl(englishVideoAPI,
                listLessonInfoDTOMapper,
                htmlMediaMapper);
    }

    @Bean
    @Singleton
    IRepository provideRepository(IAddOnRepository local,
                                  IEnglishVideoRepository remote,
                                  LessonMediaMapper mapper) {
        return new RepositoryImpl(local, remote, mapper);
    }

}
