package com.example.shotatakada.project1;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private EditText username;

    private SharedPreferences sharedPref;
    private SharedPreferencesHelper sharedPreferencesHelper;

    String[] arr = {"Zombie", "About", "Traffic", "Map" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPreferencesHelper = new SharedPreferencesHelper(sharedPref);

        username = (EditText) findViewById(R.id.userName);
        username.setText(sharedPreferencesHelper.getEntry());

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        int id = item.getItemId();

                        switch(id){
                            case R.id.nav_about:
                                Intent intent1 = new Intent(getBaseContext(), About.class);
                                startActivity(intent1);
                                break;

                            case R.id.nav_movie:
                                Intent intent2 = new Intent(getBaseContext(), RecyclerWebActivity.class);
                                startActivity(intent2);
                                break;
                        }
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ButtonAdapter(this));


    }


    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.userName);
        String message = editText.getText().toString();
        if (checkInput(message)) {
            sharedPreferencesHelper.saveEntry(message);
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }

    public boolean checkInput(String message) {
        if(message.length() == 0){
            return false;
        }
        return true;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public class ButtonAdapter extends BaseAdapter {
        private Context mContext;

        public ButtonAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return arr.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                button = new Button(mContext);
            } else {
                button = (Button) convertView;
            }

            button.setText(arr[position]);
            button.setId(position);
            button.setOnClickListener(new BtnOnClickListener());
            return button;
        }

    }

    class BtnOnClickListener implements View.OnClickListener
    {

        public void onClick(View v)
        {
            Log.d(TAG, "tapped button");
            int id = v.getId();
            Intent intent;
            switch (id) {
                case 0:
                    intent = new Intent(getBaseContext(), RecyclerWebActivity.class);
                    startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(getBaseContext(), About.class);
                    startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(getBaseContext(), RecyclerViewTrafficActivity.class);
                    startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(getBaseContext(), MapActivity.class);
                    startActivity(intent);
                    break;

                default:
                    Button b = (Button) v;
                    String label = b.getText().toString();
                    Toast.makeText(MainActivity.this, label,
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
