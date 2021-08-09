package algonquin.cst2335.finalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface sv_Api {

    @GET("/")
    Call<sv_ModelMovieInformation> getmoviewInformation(@Query("t") String t, @Query("plot") String plot, @Query("apikey") String apikey);

}
