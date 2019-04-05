package example.com.myapplication.places;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.misc.Utils;
import example.com.myapplication.App;
import example.com.myapplication.R;
import example.com.myapplication.api.LemiService;
import example.com.myapplication.di.components.DaggerSearchFragmentComponent;
import example.com.myapplication.di.components.SearchFragmentComponent;
import example.com.myapplication.model.PlaceDTO;
import example.com.myapplication.net.places.PlacesAdapter;
import example.com.myapplication.net.places.SearchLoader;
import example.com.myapplication.net.places.SearchOperation;

/**
 * Created by kestrella on 4/4/19.
 * ken.aque@gmail.com
 */
public class SearchFragment extends Fragment implements SearchContract.View {
  @BindView(R.id.lv_cities) ListView lvCities;

  @Nullable private InteractionListener listener;
  @Inject LemiService lemiService;
  @Inject Gson gson;

  private PlacesAdapter placesAdapter;

  @Nullable private SearchContract.Presenter presenter;


  static SearchFragment newInstance() {
    Bundle args = new Bundle();
    SearchFragment fragment = new SearchFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initComponent().inject(this);
    SearchLoader searchLoader = new SearchOperation(lemiService);
    presenter = new SearchPresenter(this, searchLoader);
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
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (presenter != null) presenter.search("");

    lvCities.setOnScrollListener(new AbsListView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(AbsListView absListView, int i) {
        Utils.hideSoftKeyboard(absListView);
      }

      @Override
      public void onScroll(AbsListView absListView, int i, int i1, int i2) {
      }
    });
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

  void onSearchTextChange(String query) {
    if (presenter != null) presenter.search(query);
  }

  private void setUpToolBar() {
    if (listener != null) listener.onSetUpToolbar();
  }

  private SearchFragmentComponent initComponent() {
    return DaggerSearchFragmentComponent.builder()
        .applicationComponent(App.get(getActivity()).getApplicationComponent())
        .build();
  }

  @Override
  public void showResult(List<PlaceDTO> places) {
    placesAdapter = new PlacesAdapter(getContext(), places, clickListener);
    lvCities.setAdapter(placesAdapter);
  }

  private PlacesAdapter.ClickListener clickListener = new PlacesAdapter.ClickListener() {
    @Override
    public void onClick(PlaceDTO place) {
      listener.onSelectItem(place);
    }
  };

  public interface InteractionListener {
    void onSetUpToolbar();

    void onShowToolbar();

    void onSearchTextChange(String query);

    void onSelectItem(PlaceDTO placeDTO);
  }
}
