package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

import static co.anbora.labs.engvid.injection.UntrustedSSLClient.getUnsafeOkHttpClient;

@Factory
public class NetworkModule {

    @Bean
    @Singleton
    Retrofit provideRetrofit(@Property(name = "engvid.url") String url) {
        OkHttpClient okHttpClient = getUnsafeOkHttpClient()
                .newBuilder()
                .build();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Bean
    @Singleton
    EnglishVideoAPI provideEnglishVideoAPI(Retrofit retrofit) {
        return retrofit.create(EnglishVideoAPI.class);
    }

}
