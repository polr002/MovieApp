package com.example.roy.movieapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private MoviesAdapter.ItemClickListener itemClickListener;

    public ImageView movieImg;
    public TextView movieRank;
    public View view;

    public MoviesViewHolder(View itemView, MoviesAdapter.ItemClickListener itemClickListener) {
        super(itemView);
        movieRank = itemView.findViewById(R.id.movieRank);
        movieImg = itemView.findViewById(R.id.movieImg);
        view = itemView;

        this.itemClickListener = itemClickListener;
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        itemClickListener.onClick(clickedPosition);

    }

}
