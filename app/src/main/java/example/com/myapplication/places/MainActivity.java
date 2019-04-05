package example.com.myapplication.places;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.myapplication.R;
import example.com.myapplication.model.PlaceDTO;

public class MainActivity extends AppCompatActivity implements IndexFragment.InteractionListener,
    SearchFragment.InteractionListener {
  private FragmentManager fragmentManager = getSupportFragmentManager();

  private SearchFragment searchFragment;

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.svCity) SearchView svCity;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    pushFragment(IndexFragment.newInstance(null));
  }

  void pushFragment(Fragment fragment) {
    if (fragmentManager == null) fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.content_frame, fragment).addToBackStack(fragment.getClass().getSimpleName());
    transaction.commit();
  }

  @Override
  public void onTapToSearchClicked() {
    searchFragment = SearchFragment.newInstance();
    pushFragment(searchFragment);
  }

  @Override
  public void onSetUpToolbar() {
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      toolbar.setNavigationIcon(R.drawable.ic_navigate_before_black_24dp);

      svCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
          return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
          onSearchTextChange(newText);
          return true;
        }
      });
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

  @Override
  public void onSearchTextChange(String query) {
    searchFragment.onSearchTextChange(query);
  }

  @Override
  public void onSelectItem(PlaceDTO placeDTO) {
    pushFragment(IndexFragment.newInstance(placeDTO.getName()));

  }
}
