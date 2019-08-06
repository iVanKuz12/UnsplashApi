package ru.kuznecov.ivan.testapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kuznecov.ivan.testapp.pojo.UnsplashObj;

public interface UnsplashApi {
    @GET("search/photos?client_id=9ee6f412c4515cf616a1ef03ae0db300c4d4c31b0532dbc1bc47e272005df525")
    Call<UnsplashObj> getResult(@Query("page") int page, @Query("query") String query);
}
