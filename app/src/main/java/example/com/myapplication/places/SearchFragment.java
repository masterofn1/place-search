package example.com.myapplication.places;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * Created by kestrella on 4/4/19.
 * ken.aque@gmail.com
 */
public class SearchFragment extends Fragment {

  static SearchFragment newInstance() {
    Bundle args = new Bundle();
    SearchFragment fragment = new SearchFragment();
    fragment.setArguments(args);
    return fragment;
  }
}
