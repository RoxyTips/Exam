package com.rox.app.movieproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.rox.app.movieproject.api.IRetrofitCallBack;
import com.rox.app.movieproject.api.MovieProjectService;
import com.rox.app.movieproject.pojo.MovieServiceResponse;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private PosterAdapter adapter;
    GridView posterGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            setContentView(R.layout.activity_main);
            /*MovieListFragment fragment = new MovieListFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);

            args.putString("tri", getIntent().getExtras().getString("tri", "pop"));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();*/
            adapter = new PosterAdapter(this);

            posterGridView = (GridView)findViewById(R.id.movie_poster_grid);

            MovieProjectService service = new MovieProjectService();
            service.getMovies("popular", Constant.API_KEY, new IRetrofitCallBack<MovieServiceResponse>() {
                @Override
                public void onSuccess(MovieServiceResponse response) {
                    Log.d("Response success", response.toString());

                    adapter.setMovieServiceResponse(response);
                    posterGridView.setAdapter(adapter);
                }

                @Override
                public void onFailure(String codeError) {
                    Log.e("Response error", codeError);
                }
            });
        }



    }

    @OnClick(R.id.movie_poster_grid)
    public void onClickPoster() {

    }

}
