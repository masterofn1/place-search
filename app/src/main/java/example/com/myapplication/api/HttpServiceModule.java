package example.com.myapplication.api;

import dagger.Module;
import dagger.Provides;
import example.com.myapplication.di.module.HttpModule;
import example.com.myapplication.di.scope.PerApplication;
import retrofit2.Retrofit;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@Module(includes = {HttpModule.class})
public class HttpServiceModule {

  @PerApplication
  @Provides
  LemiService searchPlaces(Retrofit retrofit) {
    return retrofit.create(LemiService.class);
  }
}
