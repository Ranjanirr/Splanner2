package com.example.demouser.splanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchBtn = findViewById(R.id.searchButton);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });

        Button myListBtn = findViewById(R.id.listButton);
        myListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyLists();
            }
        });

        Button myPlansBtn = findViewById(R.id.planButton);
        myPlansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyPlans();
            }
        });

//        Course c1 = new Course("1", "Advanced Programming","TT", "1");
//        Course temp1 = new Course("COMSC-109-01", "iDesign Studio",
//                "Monday, Wednesday", "02:55PM - 04:10PM");

    }

    private void onMyPlans() {
        Intent intent = new Intent(this, PlansActivity.class);
        startActivity(intent);
    }

    private void onMyLists() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    protected void onSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}

