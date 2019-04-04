package example.com.myapplication.places;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.myapplication.R;

public class MainActivity extends AppCompatActivity implements IndexFragment.InteractionListener,
    SearchFragment.InteractionListener {
  private FragmentManager fragmentManager = getSupportFragmentManager();

  @BindView(R.id.toolbar) Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    pushFragment(IndexFragment.newInstance());
  }

  void pushFragment(Fragment fragment) {
    if (fragmentManager == null) fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getSimpleName());
    transaction.commit();
  }

  @Override
  public void onTapToSearchClicked() {
    pushFragment(SearchFragment.newInstance());
  }

  @Override
  public void onSetUpToolbar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      toolbar.setNavigationIcon(R.drawable.ic_navigate_before_black_24dp);
    }
  }

  @Override
  public void onHideToolBar() {
    if (toolbar != null) toolbar.setVisibility(View.GONE);
  }

  @Override
  public void onShowToolbar() {
    if (toolbar != null) toolbar.setVisibility(View.VISIBLE);
  }
}
