package com.rox.app.movieproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rox.app.movieproject.Constant;
import com.rox.app.movieproject.R;
import com.rox.app.movieproject.pojo.TrailerServiceResponse;

import static android.content.Intent.ACTION_VIEW;

/**
 * Created by admuin on 31/10/2016.
 */

public class TrailerAdapter extends BaseAdapter {

    private Context mContext;
    private TrailerServiceResponse trailerServiceResponse;

    public TrailerAdapter(Context context, TrailerServiceResponse trailerResponse){
        mContext = context;
        trailerServiceResponse = trailerResponse;
    }

    @Override
    public int getCount() {
        return trailerServiceResponse.getResults().size();
    }

    @Override
    public Object getItem(int position) {
        return trailerServiceResponse.trailers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.adapter_list_trailer, parent, false);
        } else {
            layout = (LinearLayout) convertView;
        }
        final TrailerServiceResponse.Trailer trailer = (TrailerServiceResponse.Trailer)getItem(position);
        TextView tv = (TextView) layout.findViewById(R.id.trailer_textview);
        tv.setText("Watch " + trailer.getName());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_VIEW, Uri.parse(Constant.URL_YOUTUBE + trailer.getKey()));
                mContext.startActivity(intent);
            }
        });

        return layout;
    }
}
