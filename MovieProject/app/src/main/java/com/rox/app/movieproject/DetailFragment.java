package com.rox.app.movieproject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rox.app.movieproject.pojo.MovieServiceResponse;

/**
 * Created by admuin on 01/11/2016.
 */

public class DetailFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    static MovieServiceResponse.Movie movie;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            mCurrentPosition = args.getInt(ARG_POSITION);
            movie = (MovieServiceResponse.Movie) getActivity().getIntent().getSerializableExtra("movie_extra");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
