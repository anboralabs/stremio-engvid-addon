package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.domain.repository.IAddOnRepository;
import co.anbora.labs.engvid.domain.repository.IEnglishVideoRepository;
import co.anbora.labs.engvid.domain.repository.IRepository;
import co.anbora.labs.engvid.domain.usecase.lesson.*;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class DomainModule {

    @Bean
    @Singleton
    SyncRemoteLessonsUseCase provideSyncLessonUseCase(IAddOnRepository localRepository,
                                                      IEnglishVideoRepository remoteRepository) {
        return new SyncRemoteLessonsUseCase(localRepository, remoteRepository);
    }

    @Bean
    @Singleton
    SyncLessonsAtStartupUseCase provideSyncLessonsAtStartupUseCase(IRepository repository) {
        return new SyncLessonsAtStartupUseCase(repository);
    }

    @Bean
    @Singleton
    GetAllLessonsUseCase provideGetAllLessonsUseCase(IRepository repository) {
        return new GetAllLessonsUseCase(repository);
    }

    @Bean
    @Singleton
    GetLessonByIdUseCase provideGetLessonByIdUseCase(IRepository repository) {
        return new GetLessonByIdUseCase(repository);
    }

    @Bean
    @Singleton
    GetLessonsByCategoryUseCase provideGetLessonsByCategoryUseCase(IRepository repository) {
        return new GetLessonsByCategoryUseCase(repository);
    }

}
