package example.com.myapplication.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import example.com.myapplication.di.scope.PerApplication;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@Module
public class HttpModule {

  @Provides
  String getBaseUrl() {
    return "https://lemi.travel/";
  }

  @Provides
  @PerApplication
  Retrofit getRetrofit(@NonNull final String baseUrl, @NonNull OkHttpClient httpClient, @NonNull final GsonConverterFactory gsonConverterFactory, @NonNull final RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
    return new Retrofit.Builder()
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .client(httpClient)
        .baseUrl(baseUrl)
        .build();
  }

  @Provides
  @PerApplication
  GsonConverterFactory getGsonConverterFactory() {
    Gson gson = new GsonBuilder()
        .setLenient()
        .create();
    return GsonConverterFactory.create(gson);
  }

  @Provides
  @PerApplication
  RxJava2CallAdapterFactory getRxJava2CallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Provides
  public HttpLoggingInterceptor getLoggingInterceptor() {
    return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
  }

  @Provides
  public OkHttpClient getHttpClient(@NonNull HttpLoggingInterceptor interceptor) {
    final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.connectTimeout(50000, TimeUnit.MILLISECONDS);
    builder.addInterceptor(interceptor);
    return builder.build();
  }

}
