package com.example.shoumik.facebooklikefeedview;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shoumik.facebooklikefeedview.API.HivaAPI;
import com.example.shoumik.facebooklikefeedview.model.HiveModel;


import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG="testTag";
    private ListView list;
    private String API="http://api.androidhive.info/feed/";
    private FeedListAdapter listAdapter;
    private HiveModel hivemodel=new HiveModel();


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
                hivemodel = hiveModel;
                listAdapter = new FeedListAdapter(MainActivity.this, hiveModel);
                list.setAdapter(listAdapter);


            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "" + error);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                switch (position){
//                    case 0:
//                        Log.d("RESPONSE 0",""+hivemodel.getFeed()[position].getName());
//                        break;
//                    case 1:
//                        Log.d("RESPONSE 1",""+hivemodel.getFeed()[position].getName());
//                        break;
//                }


                Intent i=new Intent(MainActivity.this,ListItem.class);
                i.putExtra("itemName", hivemodel.getFeed()[position].getName());
                startActivity(i);
            }
        });




    }


}
