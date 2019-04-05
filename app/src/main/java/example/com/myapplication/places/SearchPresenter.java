package example.com.myapplication.places;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import example.com.myapplication.model.PlaceDTO;
import example.com.myapplication.net.places.SearchLoader;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class SearchPresenter implements SearchContract.Presenter {
  @NonNull private final SearchContract.View view;
  @NonNull private final SearchLoader loader;


  public SearchPresenter(@NonNull SearchContract.View view, @NonNull SearchLoader searchLoader) {
    this.view = view;
    this.loader = searchLoader;
  }

  @Override
  public void search(String searchTerm) {
    loader.performSearch(searchTerm, new SearchLoader.CallBack() {
      @Override
      public void onSearchResponse(@NonNull List<PlaceDTO> places) {
        view.showResult(places);
      }

      @Override
      public void onError(@Nullable Throwable e) {

      }
    });
  }
}
