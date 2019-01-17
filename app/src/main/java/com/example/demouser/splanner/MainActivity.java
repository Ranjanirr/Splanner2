package com.example.demouser.splanner;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button myPlansBtn, myListBtn;
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

        myListBtn = findViewById(R.id.listButton);
        myListBtn.setEnabled(false);
        myListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyLists();
            }
        });

        myPlansBtn = findViewById(R.id.planButton);
        myPlansBtn.setEnabled(false);
        myPlansBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyPlans();
            }
        });
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
        myPlansBtn.setEnabled(true);
        myListBtn.setEnabled(true);
    }
}

