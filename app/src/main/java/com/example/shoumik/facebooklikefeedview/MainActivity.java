package com.example.shoumik.facebooklikefeedview;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shoumik.facebooklikefeedview.API.HivaAPI;
import com.example.shoumik.facebooklikefeedview.model.Feed;
import com.example.shoumik.facebooklikefeedview.model.HiveModel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity {

    private String TAG="testTag";
    private ListView list;
    private String API="http://api.androidhive.info/feed/";
    private FeedListAdapter listAdapter;
    private HiveModel hivemodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RestAdapter restAdapter=new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(API).build();
        HivaAPI hiveAPI=restAdapter.create(HivaAPI.class);
        list=(ListView)findViewById(R.id.list);




        hiveAPI.getFeed(new Callback<HiveModel>() {
            @Override
            public void success(HiveModel hiveModel, Response response) {
                //Log.d(TAG,""+hiveModel.getFeed().getName());

                listAdapter = new FeedListAdapter(MainActivity.this, hiveModel);
                list.setAdapter(listAdapter);

                Log.d("RESPONSE", "This is main");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "" + error);
            }
        });




        //JSONParser();
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        Intent i=new Intent(this,ListItem.class);
//        i.putExtra("itemName",hivemodel.getFeed()[position].getName());
////        i.putExtra("itemUrl", hivemodel.getFeed()[position].getUrl());
////        i.putExtra("itemstatus",hivemodel.getFeed()[position].getStatus());
//        startActivity(i);
//
//    }

//    public void JSONParser(){
//
//
//    }
}
