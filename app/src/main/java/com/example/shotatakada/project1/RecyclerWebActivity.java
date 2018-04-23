package com.example.shotatakada.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

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


}