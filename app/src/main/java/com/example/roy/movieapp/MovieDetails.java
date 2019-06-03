package com.example.roy.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity{

    private TextView titleView;
    private TextView releaseDateView;
    private TextView ratingView;
    private TextView overviewView;
    private ImageView backdropImgView;
    private ImageView posterImgView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        titleView = findViewById(R.id.title);
        releaseDateView = findViewById(R.id.releaseDate);
        backdropImgView = findViewById(R.id.backdropImg);
        posterImgView = findViewById(R.id.posterImg);
        overviewView = findViewById(R.id.overview);
        ratingView = findViewById(R.id.rating);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String releaseDate = intent.getStringExtra("release_date");
        String backdropImg = intent.getStringExtra("backdrop_path");
        String posterImg = intent.getStringExtra("poster_path");
        String overview = intent.getStringExtra("overview");
        double rating = intent.getDoubleExtra("rating", 0);

        String posterBaseURL = "https://image.tmdb.org/t/p/w342";
        String backdropBaseURL = "https://image.tmdb.org/t/p/w1280";

        String posterURL = posterBaseURL + posterImg;
        String backdropURL = backdropBaseURL + backdropImg;


        Picasso.get().load(posterURL).into(posterImgView);
        Picasso.get().load(backdropURL).into(backdropImgView);
        titleView.setText(title);
        releaseDateView.setText(releaseDate);
        overviewView.setText(overview);
        ratingView.setText(Double.toString(rating));
    }
}
