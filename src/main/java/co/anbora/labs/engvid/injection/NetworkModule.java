package co.anbora.labs.engvid.injection;

import static co.anbora.labs.engvid.injection.UntrustedSSLClient.getUnsafeOkHttpClient;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPIImpl;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Dependent
public class NetworkModule {

  @Produces
  OkHttpClient
  provideRetrofit(@ConfigProperty(name = "engvid.url") String url) {
    return getUnsafeOkHttpClient();
  }

  @Produces
  EnglishVideoAPI provideEnglishVideoAPI(@ConfigProperty(name = "engvid.url")
                                         String url, OkHttpClient retrofit) {
    return new EnglishVideoAPIImpl(url, retrofit);
  }
}
