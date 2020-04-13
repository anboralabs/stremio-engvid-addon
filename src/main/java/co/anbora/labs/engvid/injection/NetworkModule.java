package co.anbora.labs.engvid.injection;

import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPI;
import co.anbora.labs.engvid.data.remote.api.EnglishVideoAPIImpl;
import okhttp3.OkHttpClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import static co.anbora.labs.engvid.injection.UntrustedSSLClientHelper.getUnsafeOkHttpClient;

@Dependent
public class NetworkModule {

    @Produces
    public OkHttpClient provideRetrofit(@ConfigProperty(name = "engvid.url") String url) {
        return getUnsafeOkHttpClient();
    }

    @Produces
    public EnglishVideoAPI provideEnglishVideoAPI(@ConfigProperty(name = "engvid.url") String url, OkHttpClient retrofit) {
        return new EnglishVideoAPIImpl(url, retrofit);
    }

}
