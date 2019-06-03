package com.example.roy.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {

    private final ItemClickListener itemClickListener;
    private Context context;
    public List<Result> moviesList;


    public interface ItemClickListener {
        void onClick(int i);
    }
    public MoviesAdapter(Context context, List<Result> moviesList, ItemClickListener itemClickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new MoviesViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, final int position) {
        ImageView movieImg = holder.movieImg;

        //List<Result> movie = moviesList.get(position).getResults();
        int rank = (position + 1);
        String movieRank = Integer.toString(rank);
        String imageBaseURL = "https://image.tmdb.org/t/p/w342";
        String imageURL = imageBaseURL + moviesList.get(position).getPosterPath();

        holder.movieRank.setText(movieRank);
        Picasso.get().load(imageURL).into(movieImg);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
