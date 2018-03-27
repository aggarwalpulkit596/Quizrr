package quizrr.quizrr.quizrr.REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiClients {

    public static final String BASE_URL = "http://ec2-13-126-208-145.ap-south-1.compute.amazonaws.com:3000/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}