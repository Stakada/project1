package com.example.shotatakada.project1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerWebActivity extends AppCompatActivity implements Adapter.OnItemClickListener{
    public static final String TITLE = "title";
    public static final String DIRECTOR = "unknown";
    public static final String YEAR = "year";
    public static final String IMAGE = "url";
    public static final String DESC= "decs";



    Context context;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    String url = "http://stakada.icoolshow.net/zombie_movies.json";
    Adapter adapter;
    RequestQueue queue;

    ArrayList<MovieItems> movies = new ArrayList<MovieItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        //create the Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!isConnected()){
            Toast.makeText(RecyclerWebActivity.this,"There is no internet connection",
                    Toast.LENGTH_SHORT).show();

        }

        context = getApplicationContext();

        recyclerView = findViewById(R.id.myrecyclerview);
        recyclerViewLayoutManager = new LinearLayoutManager(context);

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        queue = Volley.newRequestQueue(this);
        parseJSON();

    }
    private void parseJSON(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                String title = response.getJSONObject(i).getString("title");
                                String year = response.getJSONObject(i).getString("year");
                                String director = response.getJSONObject(i).getString("director");
                                String image = response.getJSONObject(i).getString("image");
                                String desc = response.getJSONObject(i).getString("description");

                                movies.add(new MovieItems(title, year, director, image, desc));
                            }
                            adapter = new Adapter(RecyclerWebActivity.this, movies);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(RecyclerWebActivity.this);

                        } catch (JSONException e) {
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, Detail.class);
        MovieItems clickedItem = movies.get(position);


        detailIntent.putExtra(TITLE, clickedItem.getTitle());
        detailIntent.putExtra(YEAR, clickedItem.getYear());
        if(clickedItem.getDirector() != null){
            detailIntent.putExtra(DIRECTOR, clickedItem.getDirector());
        }else {
            detailIntent.putExtra(DIRECTOR, "unknown");
        }

        detailIntent.putExtra(IMAGE, clickedItem.getImage());
        detailIntent.putExtra(DESC, clickedItem.getDesc());

        startActivity(detailIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();


        if(netInfo != null && netInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
                return true;
            }
        }
        return false;
    }


}