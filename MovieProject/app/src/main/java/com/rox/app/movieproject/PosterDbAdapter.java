package com.rox.app.movieproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.rox.app.movieproject.Data.MovieContract;
import com.rox.app.movieproject.pojo.MovieServiceResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by admuin on 31/10/2016.
 */

public class PosterDbAdapter extends CursorAdapter {

    private Context mContext;
    private static int sLoaderID;
    private Cursor curs;

    public PosterDbAdapter(Context context, Cursor c, int flags, int loaderID){
        super(context, c, flags);
        mContext = context;
        sLoaderID = loaderID;
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Récupération des variables
        Integer id = cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID_MOVIE_DB));
        String title = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE));
        String releaseDate = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE));
        String poster = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER));
        String overview = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_SYNOPSIS));
        Float vote = cursor.getFloat(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RATE));

        ImageView imageView = (ImageView) view.getTag(R.id.imageViewAdapter);

        int imageIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER);
        String posterPath = Constant.URL_POSTERS + cursor.getString(imageIndex);
        Picasso.with(mContext)
                .load(posterPath)
                .into(imageView);
        //MovieServiceResponse resp = new MovieServiceResponse();
        //resp.movies.add(0,new MovieServiceResponse().new Movie(poster, overview, releaseDate, vote, title, id));
        final MovieServiceResponse.Movie currentMovie = new MovieServiceResponse().new Movie(poster, overview, releaseDate, vote, title, id);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("movie_extra", currentMovie);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int layoutId = R.layout.image_adapter;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        view.setTag(R.id.imageViewAdapter, view.findViewById(R.id.imageViewAdapter));
        return view;
    }


}
