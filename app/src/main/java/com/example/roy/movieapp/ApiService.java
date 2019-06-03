package com.example.roy.movieapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL = "https://api.themoviedb.org/3/discover/movie/";
    /**
     * Create a retrofit client.
     */
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("?api_key=041fbdc5e7e62e58544500f66ac87f9b&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")

    Call<Movies> getMovies(@Query("primary_release_year") int year);
}