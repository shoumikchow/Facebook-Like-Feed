package com.example.shoumik.facebooklikefeedview;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoumik.facebooklikefeedview.model.HiveModel;
import com.example.shoumik.facebooklikefeedview.model.Feed;
import com.squareup.picasso.Picasso;
/**
 * Created by shoumik on 2/9/16.
 */
public class FeedListAdapter extends BaseAdapter {

    private HiveModel hivemodel;
    private LayoutInflater inflater;

    public FeedListAdapter(Context context,HiveModel hivemodel) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.hivemodel = hivemodel;



    }

    @Override
    public int getCount() {
        return hivemodel.getFeed().length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Feed getItem(int position) {
        return hivemodel.getFeed()[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View customView=inflater.inflate(R.layout.feed_item,null);


        TextView name=(TextView) customView.findViewById(R.id.name);
        TextView timestamp=(TextView) customView.findViewById(R.id.timestamp);
        TextView statusMsg=(TextView) customView.findViewById(R.id.txtStatusMsg);
        TextView url=(TextView) customView.findViewById(R.id.txtUrl);
        ImageView profile_pic=(ImageView) customView.findViewById(R.id.profile_pic);
        ImageView feed_image=(ImageView) customView.findViewById(R.id.feed_image);



         //Converting timestamp into x ago format

        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(getItem(position).getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        name.setText(getItem(position).getName());
        statusMsg.setText(getItem(position).getStatus());
        url.setText(getItem(position).getUrl());
        Picasso.with(parent.getContext()).load(getItem(position).getImage()).into(feed_image);
        Picasso.with(parent.getContext()).load(getItem(position).getProfilePic()).resize(75,75).into(profile_pic);




        return customView;
    }


}
