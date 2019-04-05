package example.com.myapplication.di.components;

import com.google.gson.Gson;

import dagger.Component;
import example.com.myapplication.api.HttpServiceModule;
import example.com.myapplication.api.LemiService;
import example.com.myapplication.di.module.ApplicationContextModule;
import example.com.myapplication.di.module.GsonModule;
import example.com.myapplication.di.scope.PerApplication;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@PerApplication
@Component(modules = {
    ApplicationContextModule.class,
    HttpServiceModule.class,
    GsonModule.class
})
public interface ApplicationComponent {
  LemiService search();

  Gson getGson();
}
