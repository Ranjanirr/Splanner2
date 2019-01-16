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

//    // contains CS classes from Spring 2019 data
//    private HashMap<String, Course> courseList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        AssetManager assetManager = getAssets();
//        try {
//            InputStream inputStream = assetManager.open("courses.txt");
//            BufferedReader in  = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while((line = in.readLine()) != null){
//                String[] courseInfo = line.split("\\|");
//                Course newCourse = new Course();
//                newCourse.setCourseNumber(courseInfo[0]);
//                newCourse.setCourseTitle(courseInfo[1]);
//                newCourse.setMeetingDays(courseInfo[2]);
//                newCourse.setCourseTime(courseInfo[3]);
//                newCourse.setCredit(courseInfo[4]);
//
//                courseList.put(newCourse.getCourseTitle(), newCourse);
//            }
//
//        } catch (IOException e) {
//            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
//            toast.show();
//        }

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

        Course temp1 = new Course("COMSC-109-01", "iDesign Studio", "Monday, Wednesday", "02:55PM - 04:10PM", "4");
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

