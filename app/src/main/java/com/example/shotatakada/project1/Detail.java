package com.example.shotatakada.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.example.shotatakada.project1.RecyclerWebActivity.DESC;
import static com.example.shotatakada.project1.RecyclerWebActivity.DIRECTOR;
import static com.example.shotatakada.project1.RecyclerWebActivity.IMAGE;
import static com.example.shotatakada.project1.RecyclerWebActivity.TITLE;
import static com.example.shotatakada.project1.RecyclerWebActivity.YEAR;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get String value from the selected item
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);
        String year = intent.getStringExtra(YEAR);
        String director = intent.getStringExtra(DIRECTOR);
        String image = intent.getStringExtra(IMAGE);
        String desc = intent.getStringExtra(DESC);


        TextView mTitle = findViewById(R.id.mTitle);
        TextView mYear = findViewById(R.id.mYear);
        TextView mDirector = findViewById(R.id.mDirector);
        ImageView mImage = findViewById(R.id.mImage);
        TextView mDesc = findViewById(R.id.mDesc);

        //set the values to each item in the model
        Picasso.with(this).load(image).fit().into(mImage);
        mTitle.setText(title);
        mYear.setText("In " + year);
        mDirector.setText(director);
        mDesc.setText(desc);
    }
}
