package example.com.myapplication.net.places;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import example.com.myapplication.model.PlaceDTO;
import example.com.myapplication.net.ServiceReceiver;
import io.reactivex.disposables.Disposable;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class SearchReceiver extends ServiceReceiver<List<PlaceDTO>> {
  private final CallBack callBack;

  public SearchReceiver(CallBack callBack) {
    this.callBack = callBack;
  }

  @Override
  public void onSubscribe(Disposable d) {

  }

  interface CallBack {
    void onSuccess(@NonNull List<PlaceDTO> places);

    void onError(@Nullable Throwable error);
  }

  @Override
  protected void onReceive(@Nullable List<PlaceDTO> response, @Nullable Throwable error) {
    if (response != null) callBack.onSuccess(response);
    else callBack.onError(error);
  }
}
