package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.lesson.*;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Dependent
public class DomainModule {

  @Produces
  SyncRemoteLessonsUseCase
  provideSyncLessonUseCase(IAddOnRepository localRepository,
                           IEnglishVideoRepository remoteRepository) {
    return new SyncRemoteLessonsUseCase(localRepository, remoteRepository);
  }

  @Produces
  SyncLessonsAtStartupUseCase
  provideSyncLessonsAtStartupUseCase(IRepository repository) {
    return new SyncLessonsAtStartupUseCase(repository);
  }

  @Produces
  GetAllLessonsUseCase provideGetAllLessonsUseCase(IRepository repository) {
    return new GetAllLessonsUseCase(repository);
  }

  @Produces
  GetLessonByIdUseCase provideGetLessonByIdUseCase(IRepository repository) {
    return new GetLessonByIdUseCase(repository);
  }

  @Produces
  GetLessonsByCategoryUseCase
  provideGetLessonsByCategoryUseCase(IRepository repository) {
    return new GetLessonsByCategoryUseCase(repository);
  }
}
