package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPIImpl;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;
import okhttp3.OkHttpClient;

import javax.inject.Singleton;

import static co.anbora.labs.engvid.injection.UntrustedSSLClient.getUnsafeOkHttpClient;

@Factory
public class NetworkModule {

    @Bean
    @Singleton
    OkHttpClient provideHttpClient() {
        return getUnsafeOkHttpClient();
    }

    @Bean
    @Singleton
    EnglishVideoAPI provideEnglishVideoAPI(@Property(name = "engvid.url") String url, OkHttpClient httpClient) {
        return new EnglishVideoAPIImpl(url, httpClient);
    }

}
