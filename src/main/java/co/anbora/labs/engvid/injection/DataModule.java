package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.RepositoryImpl;
import co.anbora.labs.engvid.data.local.AddonRepositoryImpl;
import co.anbora.labs.engvid.data.local.dao.LessonDao;
import co.anbora.labs.engvid.data.local.mapper.LessonVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonTitleMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonTitleVOMapper;
import co.anbora.labs.engvid.data.local.mapper.ListLessonVOMapper;
import co.anbora.labs.engvid.data.remote.EnglishVideoRepositoryImpl;
import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonMediaMapper;
import co.anbora.labs.engvid.data.remote.mapper.HtmlToLessonTitleMapper;
import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class DataModule {

    @Produces
    public IAddOnRepository provideLocalRepository(LessonVOMapper lessonVOMapper,
                                                   ListLessonVOMapper listLessonVOMapper,
                                                   ListLessonTitleMapper listLessonTitleMapper,
                                                   ListLessonTitleVOMapper listLessonTitleVOMapper,
                                                   ListLessonMapper listLessonMapper,
                                                   LessonDao lessonDao) {
        return new AddonRepositoryImpl(
                lessonVOMapper,
                listLessonVOMapper,
                listLessonTitleMapper,
                listLessonTitleVOMapper,
                listLessonMapper, lessonDao
        );
    }

    @Produces
    public IEnglishVideoRepository provideRemoteRepository(
            EnglishVideoAPI englishVideoAPI,
            HtmlToLessonMediaMapper htmlMediaMapper,
            HtmlToLessonTitleMapper htmlToLessonTitleMapper
    ) {
        return new EnglishVideoRepositoryImpl(englishVideoAPI, htmlMediaMapper, htmlToLessonTitleMapper);
    }

    @Produces
    public IRepository provideRepository(IAddOnRepository local,
                                  IEnglishVideoRepository remote) {
        return new RepositoryImpl(local, remote);
    }

}
