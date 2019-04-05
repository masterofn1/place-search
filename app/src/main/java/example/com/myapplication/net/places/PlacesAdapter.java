package example.com.myapplication.net.places;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
  private  Context context;
  private final ClickListener listener;

  public PlacesAdapter(@NonNull Context context, @NonNull List<PlaceDTO> objects, ClickListener clickListener) {
    super(context, 0, objects);
    this.context = context;
    this.listener = clickListener;
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
      Picasso.with(context)
          .load(url)
          .noFade()
          .fit()
          .centerCrop()
          .into(holder.ivBanner);

      convertView.setOnClickListener(view -> listener.onClick(place));
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

  public interface ClickListener {
    void onClick(PlaceDTO place);
  }
}
