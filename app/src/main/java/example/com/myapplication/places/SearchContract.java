package example.com.myapplication.places;

import java.util.List;

import example.com.myapplication.model.PlaceDTO;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class SearchContract {
  interface View {
    void showResult(List<PlaceDTO> places);
  }

  interface Presenter {
    void search(String searchTerm);
  }
}
