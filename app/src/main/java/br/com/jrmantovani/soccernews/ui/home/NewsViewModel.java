package br.com.jrmantovani.soccernews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.jrmantovani.soccernews.domain.News;
import br.com.jrmantovani.soccernews.remote.SoccerNewsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
   private SoccerNewsApi soccerNewsApi;
    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jrodolfom.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        soccerNewsApi = retrofit.create(SoccerNewsApi.class);

        findNews();
    }

    private void findNews() {
        soccerNewsApi.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()){
                    news.setValue(response.body());
                }else{
                    //TODO Pensar ema estrategia de tratamento de erro
                }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO Pensar ema estrategia de tratamento de erro
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}