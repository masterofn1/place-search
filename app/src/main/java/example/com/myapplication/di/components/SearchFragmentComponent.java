package example.com.myapplication.di.components;

import dagger.Component;
import example.com.myapplication.di.module.FragmentModule;
import example.com.myapplication.di.scope.PerFragment;
import example.com.myapplication.places.SearchFragment;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@PerFragment
@Component(modules = {FragmentModule.class}, dependencies = {ApplicationComponent.class})
public interface SearchFragmentComponent {
  void inject(SearchFragment fragment);
}
