package com.rox.app.movieproject;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rox.app.movieproject.Adapters.PosterAdapter;
import com.rox.app.movieproject.Adapters.PosterDbAdapter;
import com.rox.app.movieproject.Data.MovieContract;
import com.rox.app.movieproject.api.IRetrofitCallBack;
import com.rox.app.movieproject.api.MovieProjectService;
import com.rox.app.movieproject.pojo.MovieServiceResponse;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{

    private PosterAdapter adapter;
    private PosterDbAdapter dbAdapter;
    private String sort = "popular";
    private static final int CURSOR_LOADER_ID = 0;
    private static final int MOVIE_POPULAR_LOADER = 0;
    private static final int MOVIE_RATING_LOADER = 1;
    private static final int MOVIE_FAVORITE_LOADER = 2;

    GridView posterGridView;
    Spinner sortSpinner;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            mcontext=this;
            setContentView(R.layout.activity_main);
            adapter = new PosterAdapter(this);

            posterGridView = (GridView) findViewById(R.id.movie_poster_grid);
            sortSpinner = (Spinner) findViewById(R.id.spinner);

            ArrayAdapter<CharSequence> adapterSort = ArrayAdapter.createFromResource(this,
                    R.array.sort_array, android.R.layout.simple_spinner_item);
            adapterSort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sortSpinner.setAdapter(adapterSort);
            sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    sort = getValueOfSort(parent.getItemAtPosition(position).toString());
                    if(!"favorite".equals(sort)){
                        refreshData();
                    }else{
                        //Find favorite from DB
                        getMoviesFromDB();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //refreshData();
        }
    }

    private void getMoviesFromDB(){
        if(null != this.getLoaderManager().getLoader(CURSOR_LOADER_ID) && this.getLoaderManager().getLoader(CURSOR_LOADER_ID).isStarted()){
            this.getLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
        }else{
            this.getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        }

        dbAdapter = new PosterDbAdapter(this, null, 0, CURSOR_LOADER_ID);
        GridView gridView = (GridView)findViewById(R.id.movie_poster_grid);
        gridView.setAdapter(dbAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "GG!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void refreshData(){
        MovieProjectService service = new MovieProjectService();
        service.getMovies(sort, Constant.API_KEY, new IRetrofitCallBack<MovieServiceResponse>() {
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

    private String getValueOfSort(String itemValue){
        if(getString(R.string.mostpopular).equals(itemValue.toLowerCase().replaceAll(" ",""))){
            return getString(R.string.valueMostPop);
        }else if(getString(R.string.valueFavorite).equals(itemValue.toLowerCase().trim())){
            return getString(R.string.valueFavorite);
        }else{
            return getString(R.string.valueTopRated);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       return new CursorLoader(mcontext,
                MovieContract.MovieEntry.CONTENT_URI,
                Constant.MOVIE_COL,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        dbAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dbAdapter.swapCursor(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sort.equals(getString(R.string.valueFavorite))){
            getLoaderManager().destroyLoader(CURSOR_LOADER_ID);
            getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(sort.equals(getString(R.string.valueFavorite))) {
            getLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
        }
    }
}
