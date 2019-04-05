package example.com.myapplication.net.places;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import example.com.myapplication.api.LemiService;
import example.com.myapplication.model.PlaceDTO;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class SearchOperation implements SearchLoader {
  @NonNull private final LemiService service;
  @Nullable private CallBack rqCallBack;


  public SearchOperation(@NonNull LemiService lemiService) {
    this.service = lemiService;
  }

  @Override
  public void performSearch(@NonNull String searchTerm, CallBack callBack) {
    rqCallBack = callBack;
    Observable<List<PlaceDTO>> request = service.search(searchTerm);
    SearchReceiver receiver = new SearchReceiver(new Receiver());
//    request.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    send(request, receiver);
  }

  private class Receiver implements SearchReceiver.CallBack {

    @Override
    public void onSuccess(@NonNull List<PlaceDTO> places) {
      CallBack callBack = rqCallBack;
      if (callBack == null) return;
      rqCallBack.onSearchResponse(places);
    }

    @Override
    public void onError(@Nullable Throwable error) {
      CallBack callBack = rqCallBack;
      if (callBack == null) return;
      rqCallBack.onError(error);
    }
  }

  private <T> void send(@NonNull Observable<T> observable, @NonNull Observer<T> observer) {
    observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
  }

}
