package com.example.mubashshir.newyorktimesstoriesapi.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mubashshir.newyorktimesstoriesapi.R;
import com.example.mubashshir.newyorktimesstoriesapi.model.Story;
import com.example.mubashshir.newyorktimesstoriesapi.adapter.StoriesAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Story> data=new ArrayList<>();
    StoriesAdapter storiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(!isNetworkAvaliable(this)){
            Toast.makeText(getApplicationContext(),"Internet connection is not available",Toast.LENGTH_SHORT).show();
            return;
        }
        data = new ArrayList<>();
        getData();
    }
    public  void getData(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/topstories/v2/home.json";
        Log.v("stuff", url);
        RequestParams params = new RequestParams();
        params.put("api-key", "14ac0c9417a24a03a456e90958fdc7ac");
      //  params.put("page", "0"
        //
        // );
        Log.v("stuff", url);
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray articleJsonResults = null;
                        articleJsonResults = response.getJSONArray("results");
                        for (int i = 0; i < articleJsonResults.length(); i++) {
                            JSONObject temp = articleJsonResults.getJSONObject(i);
                            Story model = new Story(temp);
                            data.add(model);
                        }
                        storiesAdapter = new StoriesAdapter(MainActivity.this, data);
                        recyclerView.setAdapter(storiesAdapter);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        } );
    }

    public static boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }

}
