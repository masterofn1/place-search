package example.com.myapplication.net.places;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.myapplication.R;
import example.com.myapplication.model.PlaceDTO;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class PlacesAdapter extends ArrayAdapter<PlaceDTO> {
  public PlacesAdapter(@NonNull Context context, @NonNull List<PlaceDTO> objects) {
    super(context, 0, objects);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ViewHolder holder;
    if (convertView != null) {
      holder = (ViewHolder) convertView.getTag();
    } else {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_item, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(holder);
    }

    final PlaceDTO place = getItem(position);
    if (place != null) {
      holder.tvCity.setText(place.getName());
      holder.tvSubtitle.setText(place.getSubtitle());
      String url = place.getBanner() != null && !place.getBanner().isEmpty() ? place.getBanner() : null;
    }

    return convertView;
  }

  static class ViewHolder {
    @BindView(R.id.iv_banner) ImageView ivBanner;
    @BindView(R.id.tv_city) TextView tvCity;
    @BindView(R.id.tv_subtitle) TextView tvSubtitle;

    private ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}