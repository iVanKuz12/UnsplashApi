package ru.kuznecov.ivan.testapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.kuznecov.ivan.testapp.network.NetworkService;
import ru.kuznecov.ivan.testapp.pojo.UnsplashObj;

public class MyViewModel extends ViewModel {

    private MutableLiveData<UnsplashObj> liveData;
    private int pages;
    private String query;
    private long countPage;


    public LiveData<UnsplashObj> getLiveData(){
        if (liveData == null) {
            liveData = new MutableLiveData<>();
            loadData();
        }
        return liveData;
    }

    public void setQuery(String query) {
        if (this.query == null) {
            this.query = query;
            return;
        }
        if (!this.query.equals(query)) {
            this.query = query;
            loadData();
        }
    }

    private void loadData() {
        pages = 1;
        reqestData(pages);
    }

    public void loadNextPages(){
        if (countPage >= pages + 1) {
            pages += 1;
            reqestData(pages);
        }
    }

    private void reqestData(int pages) {
        NetworkService
                .getInstance()
                .getJson()
                .getResult(pages, query)
                .enqueue(new Callback<UnsplashObj>() {
                    @Override
                    public void onResponse(Call<UnsplashObj> call, Response<UnsplashObj> response) {
                        UnsplashObj unsplashObj = response.body();
                        countPage = unsplashObj.getTotalPages();
                        liveData.postValue(unsplashObj);
                    }

                    @Override
                    public void onFailure(Call<UnsplashObj> call, Throwable t) {

                    }
                });
    }


}
