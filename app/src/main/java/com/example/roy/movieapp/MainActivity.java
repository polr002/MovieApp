package com.example.roy.movieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ItemClickListener{

    private ApiService service;
    private List<Movies> moviesList;
    private List<Result> results;
    public EditText searchField;
    public Button submitBtn;
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    private int modifyPosition;
    public static final String CLICKED_MOVIE = "movie";
    public static final int MOVIE_INFO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchField);
        submitBtn = findViewById(R.id.submitBtn);
        recyclerView = findViewById(R.id.recyclerView);

        service = ApiService.retrofit.create(ApiService.class);
        moviesList = new ArrayList<>();


        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = searchField.getText().toString();
                int year = Integer.parseInt(value);
                if(year != 0) {
                    requestData(year);
                }
            }
        });
    }
    private void requestData(int year) {
        Call<Movies> call = service.getMovies(year);
        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                Context context = getApplicationContext();
                String toastText = "Request Successful";

                moviesList.add(response.body());

                results = moviesList.get(0).getResults();

                updateView();
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d("error",t.toString());
            }
        });
    }
    public void updateView() {
        mAdapter = new MoviesAdapter(getApplicationContext(), results, this);
        recyclerView.setAdapter(mAdapter);
    }
    public void onClick(int i) {
        Intent intent = new Intent(MainActivity.this, MovieDetails.class);
        modifyPosition = i;
        intent.putExtra("title", results.get(i).getTitle());
        intent.putExtra("poster_path", results.get(i).getPosterPath());
        intent.putExtra("backdrop_path", results.get(i).getBackdropPath());
        intent.putExtra("release_date", results.get(i).getReleaseDate());
        intent.putExtra("rating", results.get(i).getVoteAverage());
        intent.putExtra("overview", results.get(i).getOverview());
        startActivityForResult(intent, MOVIE_INFO);
    }
}
