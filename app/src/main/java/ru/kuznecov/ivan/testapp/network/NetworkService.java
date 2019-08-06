package ru.kuznecov.ivan.testapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String CLIENT_ID = "";
    private static final String BASE_URL = "https://api.unsplash.com/";
    private Retrofit mRetrofit;

    private NetworkService(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public UnsplashApi getJson(){
        return mRetrofit.create(UnsplashApi.class);
    }

}
