package com.senosy.newsmvvm.Network.Repo;

import androidx.lifecycle.MutableLiveData;

import com.senosy.newsmvvm.Model.NewsResponse;
import com.senosy.newsmvvm.Network.API.NewsApi;
import com.senosy.newsmvvm.util.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;

    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsApi newsApi;

    public NewsRepository(){
        newsApi = RetrofitService.getRetrofitInstance().create(NewsApi.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key){
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        newsApi.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call,
                                   Response<NewsResponse> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
