package com.example.shotatakada.project1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class show_list_activity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    // 2D data array
    String[][] subjects =
            {
                    { "ANDROID", "1" },
                    { "PHP", "2" },
                    { "JSON", "3" },
                    { "SWIFT", "4" },
                    { "OBJECTIVE-C", "5" },
                    { "SQL", "" },
                    { "JAVA", "" },
                    { "JAVASCRIPT", "" },
                    { "REACT", "" },
                    { "PYTHON", "" },
                    { "ANGULAR", "" },
                    { "JQUERY", "" },
                    { "CANVAS", "" },
                    { "D3", "" },
                    { "MATPLOTLIB", "" },
                    { "NODE", "" }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_activity);


        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        recyclerViewLayoutManager = new LinearLayoutManager(context);

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new CustomAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTitle;
            public TextView mDetail;
            public ViewHolder(View v) {
                super(v);
                mTitle = (TextView) v.findViewById(R.id.title);
                mDetail = (TextView) v.findViewById(R.id.year);
            }
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View item = getLayoutInflater().inflate(R.layout.items, parent,
                    false);

            ViewHolder vh = new ViewHolder(item);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTitle.setText(subjects[position][0]);
            holder.mDetail.setText(subjects[position][1]);
        }

        @Override
        public int getItemCount() {
            return subjects.length;
        }
    }
}
