package com.rox.app.movieproject;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rox.app.movieproject.Data.MovieContract;
import com.rox.app.movieproject.api.IRetrofitCallBack;
import com.rox.app.movieproject.api.MovieProjectService;
import com.rox.app.movieproject.pojo.MovieServiceResponse;
import com.rox.app.movieproject.pojo.TrailerServiceResponse;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private Context contextActivity;
    private MovieServiceResponse.Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movie = (MovieServiceResponse.Movie) getIntent().getSerializableExtra("movie_extra");
        setContentView(R.layout.activity_detail);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(movie.title);

        contextActivity = this;
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
        synopsisTextView.setMovementMethod(new ScrollingMovementMethod());
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

        int idMovie = movie.id;
        ListView trailerList = (ListView) findViewById(R.id.listview_trailers);
        MovieProjectService service = new MovieProjectService();
        service.getTrailers(idMovie, Constant.API_KEY, new IRetrofitCallBack<TrailerServiceResponse>() {
            @Override
            public void onSuccess(TrailerServiceResponse response) {
                TrailerAdapter adapter = new TrailerAdapter(contextActivity, response);
                ListView listView = (ListView)findViewById(R.id.listview_trailers);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String codeError) {

            }
        });

        ImageButton addFavoriteButton = (ImageButton)findViewById(R.id.imageButton);
        addFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavoriteToDb();
            }
        });
    }

    private void addFavoriteToDb(){
        ContentValues[] movieArr = new ContentValues[1];
        movieArr[0] = new ContentValues();
        movieArr[0].put(MovieContract.MovieEntry.COLUMN_ID_MOVIE_DB, movie.id);
        movieArr[0].put(MovieContract.MovieEntry.COLUMN_TITLE, movie.title);
        movieArr[0].put(MovieContract.MovieEntry.COLUMN_POSTER, movie.posterPath);
        movieArr[0].put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movie.releaseDate);
        movieArr[0].put(MovieContract.MovieEntry.COLUMN_SYNOPSIS,movie.overview);
        movieArr[0].put(MovieContract.MovieEntry.COLUMN_RATE, movie.voteAverage/2);

        getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI, movieArr);
    }
}
