package com.example.demouser.splanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlansActivity extends AppCompatActivity {

    private RecyclerView.Adapter planAdapter;
    private ArrayList<Course> myCourseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        TextView planAtv = findViewById(R.id.plan1Text);
        TextView planBtv = findViewById(R.id.plan2Text);

        Course temp1 = new Course("COMSC-109-01", "iDesign Studio", "Monday, Wednesday", "02:55PM - 04:10PM", "4");
        myCourseList.add(temp1);
        myCourseList.add(temp1);
        myCourseList.add(temp1);
        myCourseList.add(temp1);

        for (Course course: myCourseList) {
            planBtv.setText(planBtv.getText()+ course.toString() + "\n" + "\n");
            planAtv.setText(planAtv.getText()+ course.toString() + "\n" + "\n");
        }
    }


}
