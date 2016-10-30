package com.rox.app.movieproject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rox.app.movieproject.pojo.MovieServiceResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by admuin on 30/10/2016.
 */

public class PosterAdapter extends BaseAdapter {

    private Context mContext;
    private MovieServiceResponse movieServiceResponse;

    public PosterAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return movieServiceResponse.movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movieServiceResponse.movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movieServiceResponse.movies.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
        final MovieServiceResponse.Movie currentMovie = movieServiceResponse.movies.get(position);
        String posterPath = Constant.URL_POSTERS + movieServiceResponse.movies.get(position).posterPath;
        Picasso.with(mContext)
                .load(posterPath)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("movie_extra", currentMovie);
                mContext.startActivity(intent);
            }
        });
        return imageView;

    }

    public void setMovieServiceResponse(MovieServiceResponse movieServiceResponse) {
        this.movieServiceResponse = movieServiceResponse;
    }
}
