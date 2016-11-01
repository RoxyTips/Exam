package com.rox.app.movieproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rox.app.movieproject.Adapters.ReviewAdapter;
import com.rox.app.movieproject.api.IRetrofitCallBack;
import com.rox.app.movieproject.api.MovieProjectService;
import com.rox.app.movieproject.pojo.ReviewServiceResponse;

public class ReviewFragment extends Fragment {


    private ReviewAdapter adapter;
    private Context mContext = getActivity();
    private int movieId;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReviewFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    /*public static ReviewFragment newInstance(int columnCount) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            movieId = getArguments().getInt("movieId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_review, container, false);
        final ListView listViewReview = (ListView) view.findViewById(R.id.listview_review);

        MovieProjectService service = new MovieProjectService();
        service.getReviews(movieId, Constant.API_KEY, new IRetrofitCallBack<ReviewServiceResponse>() {
            @Override
            public void onSuccess(ReviewServiceResponse response) {
                adapter = new ReviewAdapter(getActivity());
                adapter.setReviewServiceResponse(response);
                listViewReview.setBackgroundColor(0xFFFFFFFF);
                listViewReview.setAdapter(adapter);
            }

            @Override
            public void onFailure(String codeError) {
                Log.e("You should not PASS !", codeError);
            }
        });

        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    return true;
                }
                return false;
            }
        });

        return view;
    }



/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }*/
}
