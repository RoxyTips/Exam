package com.rox.app.movieproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.rox.app.movieproject.api.IRetrofitCallBack;
import com.rox.app.movieproject.api.MovieProjectService;
import com.rox.app.movieproject.pojo.MovieServiceResponse;

public class MainActivity extends AppCompatActivity {

    private PosterAdapter adapter;
    private String sort = "popular";
    GridView posterGridView;
    Spinner sortSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
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
                    refreshData();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            refreshData();
        }
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
        if("Most Popular".toLowerCase().trim().equals(itemValue.toLowerCase().trim())){
            return "popular";
        }else{
            return "top_rated";
        }
    }

}
