package com.rox.app.movieproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rox.app.movieproject.pojo.MovieServiceResponse;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    private MovieServiceResponse.Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = (MovieServiceResponse.Movie) getIntent().getSerializableExtra("movie_extra");
        setContentView(R.layout.activity_detail);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(movie.title);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String posterPath = Constant.URL_POSTERS + movie.posterPath;

        TextView titleTextView = (TextView)findViewById(R.id.title_movie_detail2);
        TextView synopsisTextView = (TextView)findViewById(R.id.synopsis_movie_detail2);
        TextView releaseDateTextView = (TextView)findViewById(R.id.release_date_movie_detail2);
        RatingBar ratingBarDetail = (RatingBar)findViewById(R.id.ratingBar_movie_detail2);
        ImageView imageViewPoster = (ImageView)findViewById(R.id.poster_movie_detail2);

        titleTextView.setText(movie.title);
        synopsisTextView.setText(movie.overview);
        releaseDateTextView.setText(movie.releaseDate);
        ratingBarDetail.setRating(movie.voteAverage/2);
        Picasso.with(this)
                .load(posterPath)
                .into(imageViewPoster);

    }
}
