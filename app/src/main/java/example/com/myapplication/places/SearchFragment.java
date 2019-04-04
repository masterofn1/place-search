package example.com.myapplication.places;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.myapplication.R;

/**
 * Created by kestrella on 4/4/19.
 * ken.aque@gmail.com
 */
public class SearchFragment extends Fragment {
  @BindView(R.id.lv_cities) ListView lvCities;

  @Nullable private InteractionListener listener;

  static SearchFragment newInstance() {
    Bundle args = new Bundle();
    SearchFragment fragment = new SearchFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search, container, false);
    ButterKnife.bind(this, view);
    setUpToolBar();
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    if (listener != null) listener.onShowToolbar();
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    try {
      listener = (InteractionListener) context;
    } catch (ClassCastException ignored) {
      throw new ClassCastException(context.toString()
          + " must implement InteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    listener = null;
  }

  private void setUpToolBar() {
    if (listener != null) listener.onSetUpToolbar();
  }

  public interface InteractionListener {
    void onSetUpToolbar();

    void onShowToolbar();
  }
}
