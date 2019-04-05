package example.com.myapplication.places;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.myapplication.R;

/**
 * Created by kestrella on 4/4/19.
 * ken.aque@gmail.com
 */
public class IndexFragment extends Fragment {
  @Nullable private InteractionListener listener;
  private static final String CITY = "city";

  @BindView(R.id.tv_selected_city) TextView tvSelectedCity;

  static IndexFragment newInstance(@Nullable String city) {
    Bundle args = new Bundle();
    if (city != null) args.putString(CITY, city);
    IndexFragment fragment = new IndexFragment();
    fragment.setArguments(args);
    return fragment;
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

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_index, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    if (listener != null) listener.onHideToolBar();
    Bundle bundle = getArguments();
    if (bundle == null) return;
    if (bundle.getString(CITY) != null) {
      String city = bundle.getString(CITY);
      tvSelectedCity.setText(String.format(getString(R.string.selected_city), city));
      tvSelectedCity.setVisibility(View.VISIBLE);
    } else {
      tvSelectedCity.setVisibility(View.GONE);
    }
  }

  @OnClick(R.id.tv_tap_to_select)
  void openSearchPage() {
    if (listener != null) listener.onTapToSearchClicked();
  }

  public interface InteractionListener {
    void onTapToSearchClicked();

    void onHideToolBar();
  }
}
