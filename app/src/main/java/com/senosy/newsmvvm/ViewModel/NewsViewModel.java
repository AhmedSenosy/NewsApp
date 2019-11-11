package com.senosy.newsmvvm.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.senosy.newsmvvm.Model.NewsResponse;
import com.senosy.newsmvvm.Network.Repo.NewsRepository;

public class NewsViewModel extends ViewModel {
    private NewsRepository repository;
    private MutableLiveData<NewsResponse> newsResponseMutableLiveData;

    public void init(){
        if(newsResponseMutableLiveData != null)
        {
            return;
        }
        repository = NewsRepository.getInstance();
        newsResponseMutableLiveData = repository.getNews("google-news","");
    }

    public MutableLiveData<NewsResponse> getNewsResponseMutableLiveData() {
        return newsResponseMutableLiveData;
    }
}
