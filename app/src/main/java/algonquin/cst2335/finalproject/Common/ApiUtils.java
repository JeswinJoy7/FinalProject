package algonquin.cst2335.finalproject.Common;

public class ApiUtils {

    /**** BaseUrl of this application**/

    public static final String BASE_URL = "https://api.openchargemap.io/v3/";


    private ApiUtils() {
    }

    public static Api getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(Api.class);

    }

}
