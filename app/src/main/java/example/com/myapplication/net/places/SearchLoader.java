package example.com.myapplication.net.places;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import example.com.myapplication.model.PlaceDTO;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public interface SearchLoader {
  void performSearch(@NonNull String searchTerm, CallBack callBack);

  interface CallBack {
    void onSearchResponse(@NonNull List<PlaceDTO> places);
    void onError(@Nullable Throwable e);
  }
}
