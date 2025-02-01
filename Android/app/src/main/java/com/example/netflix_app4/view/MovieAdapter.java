package com.example.netflix_app4.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.netflix_app4.R;
import com.example.netflix_app4.model.MovieModel;
import com.example.netflix_app4.model.UserInfo;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Context context;
    private final List<MovieModel> movies;
    private final CategoryAdapter.OnMovieClickListener movieClickListener;

    private final UserInfo userInfo;

    public MovieAdapter(Context context, List<MovieModel> movies, UserInfo userInfo, CategoryAdapter.OnMovieClickListener movieClickListener) {
        this.context = context;
        this.movies = movies;
        this.movieClickListener = movieClickListener;
        this.userInfo = userInfo;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieModel movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ImageView movieThumbnail;
        private final TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieThumbnail = itemView.findViewById(R.id.movieThumbnail);
            movieTitle = itemView.findViewById(R.id.movieTitle);

            // Handle item click
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (movieClickListener != null) {
                        movieClickListener.onMovieClick(movies.get(position));
                    } else {
                        // If no click listener, launch MovieDetailsActivity directly
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("movieDetails", movies.get(position));
                        intent.putExtra("USER_INFO", userInfo);
                        context.startActivity(intent);
                    }
                }
            });
        }

        public void bind(MovieModel movie) {
            Glide.with(context)
                    .load(movie.getThumbnail())
                    .placeholder(R.drawable.placeholder_image)
                    .into(movieThumbnail);
            movieTitle.setText(movie.getTitle());
        }
    }

    public void updateMovies(List<MovieModel> newMovies) {
        this.movies.clear();
        this.movies.addAll(newMovies);
        notifyDataSetChanged();
    }
}