package example.com.myapplication.places;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.com.myapplication.R;

/**
 * Created by kestrella on 4/4/19.
 * ken.aque@gmail.com
 */
public class IndexFragment extends Fragment {

  public static IndexFragment newInstance() {
    Bundle args = new Bundle();
    IndexFragment fragment = new IndexFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_index, container, false);
    return view;
  }

}
