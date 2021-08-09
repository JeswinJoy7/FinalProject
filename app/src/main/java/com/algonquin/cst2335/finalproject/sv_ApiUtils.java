package com.algonquin.cst2335.finalproject;

public class sv_ApiUtils {

    /**** BaseUrl of this application**/

//    Local Url
//public static final String BASE_URL = "http://182.156.200.178:211/API/";

    //Live Url
    public static final String BASE_URL = "https://www.omdbapi.com/";


    private sv_ApiUtils() {
    }

    public static sv_Api getAPIService() {

        return sv_RetrofitClient.getClient(BASE_URL).create(sv_Api.class);

    }

}
