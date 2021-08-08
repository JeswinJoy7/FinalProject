package algonquin.cst2335.finalproject.Common;


import java.util.List;

import algonquin.cst2335.finalproject.Model.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Api {

    @GET("poi/")
    Call<List<Example>> getChargingPoint(@Header("X-API-Key") String Key);

}
