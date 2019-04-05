package example.com.myapplication.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@Module
public class ApplicationContextModule {

  private Application application;

  public ApplicationContextModule(@NonNull final Application application) {
    this.application = application;
  }

  @Provides
  public Context getApplicationContext() {
    return application;
  }

  @Provides
  public Application getApplication() {
    return application;
  }
}
