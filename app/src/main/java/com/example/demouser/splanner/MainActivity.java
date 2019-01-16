package com.example.demouser.splanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CourseSorter";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Course c1 = new Course("1", "Advanced Programming", "TT", "02:55PM - 04:10PM");
        Course c2 = new Course("COMSC-109-01", "iDesign Studio",
                "Monday, Wednesday", "02:55PM - 04:10PM");
        Course c3 = new Course("COMSC-109-01", "Sins",
                "Monday, Wednesday", "02:55PM - 04:10PM");
        Course c4 = new Course("COMSC-109-01", "Optimist",
                "Monday, Wednesday", "02:55PM - 04:10PM");
        Course c5 = new Course("COMSC-109-01", "Close",
                "Monday, Wednesday", "02:55PM - 04:10PM");

        Course[] courses = new Course[]{c1, c2, c3, c4, c5};
        int x = 0;
        LinkedList<Course[]> result = new LinkedList<Course[]>();
        for (int i = 0; i < courses.length - 2; i++) {
            for (int j = i + 1; j < courses.length - 1; j++) {
                for (int k = j + 1; k < courses.length; k++) {
                    Course[] plan = new Course[3];
                    plan[0] = courses[i];
                    plan[1] = courses[j];
                    plan[2] = courses[k];
                    result.add(x, plan);
                    x++;
                }
            }
        }

    }

}