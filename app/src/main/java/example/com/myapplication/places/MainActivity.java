package example.com.myapplication.places;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

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
