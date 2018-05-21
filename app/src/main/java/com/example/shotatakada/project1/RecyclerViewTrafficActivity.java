package com.example.shotatakada.project1;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

public class RecyclerViewTrafficActivity extends AppCompatActivity{
    
    Context context;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    String url = "https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";
    AdapterTraffic adapter;

    ArrayList<trafficItems> traffic = new ArrayList<trafficItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_traffic);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        //create the Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(!isConnected()){
            Toast.makeText(RecyclerViewTrafficActivity.this,"There is no internet connection",
                    Toast.LENGTH_SHORT).show();

        }
        context = getApplicationContext();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewLayoutManager = new LinearLayoutManager(context);

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterTraffic(RecyclerViewTrafficActivity.this,traffic);
        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray features = response.getJSONArray("Features");
                            try {
                                for (int i = 0; i < features.length(); i++) {
                                    JSONObject obj = features.getJSONObject(i);

                                    //Get coordinate
                                    JSONArray coord = obj.getJSONArray("PointCoordinate");
                                    Double lat = coord.optDouble(0);
                                    Double lan = coord.optDouble(1);


                                    //iterate through the Camera array
                                    JSONArray finalArr = obj.getJSONArray("Cameras");
                                    for (int j = 0; j < finalArr.length(); j++) {

                                        String label = finalArr.getJSONObject(j).getString("Description");
                                        String img = finalArr.getJSONObject(j).getString("ImageUrl");
                                        String type = finalArr.getJSONObject(j).getString("Type");

                                        traffic.add(new trafficItems(label, img, type, lat, lan));
                                    }


                                }
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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
