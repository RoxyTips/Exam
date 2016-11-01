package com.rox.app.movieproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rox.app.movieproject.R;
import com.rox.app.movieproject.pojo.ReviewServiceResponse;

/**
 * Created by admuin on 31/10/2016.
 */

public class ReviewAdapter extends BaseAdapter {
    private Context mContext;
    private ReviewServiceResponse reviewServiceResponse;

    public ReviewAdapter(Context context){
        mContext = context;
    }
    @Override
    public int getCount() {
        return reviewServiceResponse.results.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewServiceResponse.results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView userTextView;
        TextView contentTextView;
        LinearLayout view;
        if(convertView ==  null){
            view = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.fragment_review, parent, false);
        }else{
            view = (LinearLayout) convertView;
        }

        userTextView = (TextView) view.findViewById(R.id.userReview);
        contentTextView = (TextView) view.findViewById(R.id.contentReview);

        ReviewServiceResponse.Result currentRes = reviewServiceResponse.results.get(position);

        userTextView.setText(mContext.getString(R.string.reviewBy) + " " + currentRes.author);
        contentTextView.setText(currentRes.content);

        return view;
    }

    public void setReviewServiceResponse(ReviewServiceResponse reviewServiceResponse) {
        this.reviewServiceResponse = reviewServiceResponse;
    }
}
