package example.com.myapplication.api;

import java.util.List;

import example.com.myapplication.model.PlaceDTO;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public interface LemiService {
  @GET("/api/v5/cities")
  Observable<List<PlaceDTO>> search(@Query("q") final String query);

}
