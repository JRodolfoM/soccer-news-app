package br.com.jrmantovani.soccernews.data.remote;

import java.util.List;

import br.com.jrmantovani.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;


public interface SoccerNewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
