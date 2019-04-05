package example.com.myapplication.net;

import androidx.annotation.Nullable;
import io.reactivex.Observer;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public abstract class ServiceReceiver<T> implements Observer<T> {
  @Nullable
  private T response;
  @Nullable
  private Throwable error;

  protected abstract void onReceive(@Nullable T response, @Nullable Throwable error);


  @Override
  public void onComplete() {
    onReceive(response, error);
  }

  @Override
  public void onError(Throwable e) {
    error = e;
    onReceive(response, error);
  }

  @Override
  public void onNext(T t) {
    response = t;
  }

}