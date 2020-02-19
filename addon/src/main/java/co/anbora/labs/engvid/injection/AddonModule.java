package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.usecases.UseCaseExecutorImpl;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
public class AddonModule {

    @Bean
    @Singleton
    UseCaseExecutor provideUseCaseExecutor() {
        return new UseCaseExecutorImpl();
    }

}
