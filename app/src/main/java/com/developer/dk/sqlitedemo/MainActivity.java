package com.developer.dk.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private JsonPlaceHolderApi api;
    private ArrayList<Integer> articleIdList;
    public static final String TAG = "Main";
    private ArrayList<String> articleTitleList,articleUrlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        try {

            articleTitleList = new ArrayList<>();
            articleUrlList = new ArrayList<>();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://hacker-news.firebaseio.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(JsonPlaceHolderApi.class);

            Call<List<Integer>> call = api.getTopStories();

            articleIdList = new ArrayList<>();

            call.enqueue(new Callback<List<Integer>>() {
                @Override
                public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Error : " + response.code(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "onResponse: " + response.code());
                    }

                    Log.e(TAG, "onResponse: enter in enqueue");

                    List<Integer> articleId = response.body();

                    for (Integer i : articleId){

//                        while (numberOfArticles != 0){

                            Call<ArticleResponse> articleCall = api.getArticles(i);

                            articleCall.enqueue(new Callback<ArticleResponse>() {
                                @Override
                                public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                                    if (!response.isSuccessful()){
                                        Log.e(TAG, "onResponse: "+ response.code());
                                    }

                                    ArticleResponse article = response.body();

                                    articleTitleList.add(article.getArticleTitle());
                                    articleUrlList.add(article.getArticleUrl());

                                    Log.e(TAG, "onResponse: TITLE AND URL : "+ article.getArticleTitle() + " " + article.getArticleUrl());

                                    arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,articleTitleList);
                                    listView.setAdapter(arrayAdapter);
                                }

                                @Override
                                public void onFailure(Call<ArticleResponse> call, Throwable t) {

                                }
                            });

//                            numberOfArticles--;
//                        }
                    }

                }

                @Override
                public void onFailure(Call<List<Integer>> call, Throwable t) {
                    Log.e(TAG, "onFailure: "+ t.toString());
                }
            });

            Log.e(TAG, "onCreate: " + articleIdList.toString());

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MainActivity.this,NewsActivity.class);
                    i.putExtra("articleUrl",articleUrlList.get(position));
                    startActivity(i);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}