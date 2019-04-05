package example.com.myapplication.di.module;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@Module
public class GsonModule {

  @Provides
  Gson getGson() {
    return new Gson();
  }
}
