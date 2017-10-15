package kmitl.natcha58070069.com.mylazyinstagram.api;

import kmitl.natcha58070069.com.mylazyinstagram.model.UserProfile;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nacha on 07-Oct-17.
 */

public interface MylazyInstagramApi {
    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);
}
