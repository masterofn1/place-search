package example.com.myapplication;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import example.com.myapplication.di.components.ApplicationComponent;
import example.com.myapplication.di.components.DaggerApplicationComponent;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class App extends Application {
  public ApplicationComponent applicationComponent;
  private static App instance;

  public static App getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
  }


  @NonNull
  public ApplicationComponent getApplicationComponent() {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent
          .builder()
          .build();
    }

    return applicationComponent;
  }

  public static App get(@NonNull Activity activity) {
    return (App) activity.getApplication();
  }
}
