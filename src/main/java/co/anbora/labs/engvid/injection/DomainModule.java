package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.lesson.GetAllLessonsUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonByIdUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.GetLessonsByCategoryUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.SyncLessonsAtStartupUseCase;
import co.anbora.labs.engvid.domain.usecase.lesson.SyncRemoteLessonsUseCase;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class DomainModule {

    @Produces
    public SyncRemoteLessonsUseCase provideSyncLessonUseCase(IAddOnRepository localRepository,
                                                             IEnglishVideoRepository remoteRepository) {
        return new SyncRemoteLessonsUseCase(localRepository, remoteRepository);
    }

    @Produces
    public SyncLessonsAtStartupUseCase provideSyncLessonsAtStartupUseCase(IRepository repository) {
        return new SyncLessonsAtStartupUseCase(repository);
    }

    @Produces
    public GetAllLessonsUseCase provideGetAllLessonsUseCase(IRepository repository) {
        return new GetAllLessonsUseCase(repository);
    }

    @Produces
    public GetLessonByIdUseCase provideGetLessonByIdUseCase(IRepository repository) {
        return new GetLessonByIdUseCase(repository);
    }

    @Produces
    public GetLessonsByCategoryUseCase provideGetLessonsByCategoryUseCase(IRepository repository) {
        return new GetLessonsByCategoryUseCase(repository);
    }

}
