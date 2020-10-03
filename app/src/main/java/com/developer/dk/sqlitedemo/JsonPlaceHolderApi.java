package com.developer.dk.sqlitedemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();

    @GET("v0/item/{articleId}.json?print=pretty")
    Call<ArticleResponse> getArticles(@Path("articleId") int id);
}
