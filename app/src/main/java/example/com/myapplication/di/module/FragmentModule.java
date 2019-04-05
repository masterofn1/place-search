package example.com.myapplication.di.module;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
@Module
public class FragmentModule {

  private Fragment fragment;

  public FragmentModule(@NonNull final Fragment fragment) {
    this.fragment = fragment;
  }

  @Provides
  public Fragment getFragment() {
    return fragment;
  }
}
