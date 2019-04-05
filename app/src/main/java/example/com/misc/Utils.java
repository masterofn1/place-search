package example.com.misc;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public final class Utils {
  private Utils() {
  }

  public static void hideSoftKeyboard(@NonNull View view) {
    Context context = view.getContext();
    if (context == null) return;
    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null) {
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }
}
