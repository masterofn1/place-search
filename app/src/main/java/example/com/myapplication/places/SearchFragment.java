package example.com.myapplication.places;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.myapplication.App;
import example.com.myapplication.R;
import example.com.myapplication.api.LemiService;
import example.com.myapplication.di.components.DaggerSearchFragmentComponent;
import example.com.myapplication.di.components.SearchFragmentComponent;
import example.com.myapplication.model.PlaceDTO;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kestrella on 4/4/19.
 * ken.aque@gmail.com
 */
public class SearchFragment extends Fragment {
  @BindView(R.id.lv_cities) ListView lvCities;

  @Nullable private InteractionListener listener;
  @Inject LemiService lemiService;
  @Inject Gson gson;

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
    Disposable d = search().observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(result -> {
          // todo
        }, e -> {
          // something went wrong
        });
  }

  private Observable<List<PlaceDTO>> search() {
    return lemiService.search("lond").flatMap(Observable::just);
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

  }

  private void setUpToolBar() {
    if (listener != null) listener.onSetUpToolbar();
  }

  private SearchFragmentComponent initComponent() {
    return DaggerSearchFragmentComponent.builder()
        .applicationComponent(App.get(getActivity()).getApplicationComponent())
        .build();
  }

  public interface InteractionListener {
    void onSetUpToolbar();

    void onShowToolbar();

    void onSearchTextChange(String query);
  }
}
