package co.anbora.labs.engvid.usecases;

import co.anbora.labs.engvid.domain.usecase.UseCase;
import co.anbora.labs.engvid.domain.usecase.UseCaseExecutor;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;

public class UseCaseExecutorImpl implements UseCaseExecutor {
  @Override
  public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues>
      CompletableFuture<RX> execute(UseCase<I, O> useCase, I input,
                                    Function<O, RX> outputMapper) {
    return CompletableFuture.supplyAsync(() -> input)
        .thenApplyAsync(useCase::execute)
        .thenApplyAsync(outputMapper);
  }
}
