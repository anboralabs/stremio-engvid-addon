package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import co.anbora.labs.engvid.usecases.UseCaseExecutorImpl;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Dependent
public class AddonModule {

  @Produces
  UseCaseExecutor provideUseCaseExecutor() {
    return new UseCaseExecutorImpl();
  }
}
