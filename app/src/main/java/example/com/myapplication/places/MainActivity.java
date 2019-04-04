package example.com.myapplication.places;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import example.com.myapplication.R;

public class MainActivity extends AppCompatActivity {
  private FragmentManager fragmentManager = getSupportFragmentManager();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pushFragment(IndexFragment.newInstance());
  }

  void pushFragment(Fragment fragment) {
    if (fragmentManager == null) fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getSimpleName());
    transaction.commit();
  }
}
