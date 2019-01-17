package com.example.demouser.splanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlansActivity extends AppCompatActivity {

    //    private ArrayList<Plan> myPlanList = new ArrayList<>();
    private List<Course> plan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        TextView planAtv = findViewById(R.id.plan1Text);
//        TextView planBtv = findViewById(R.id.plan2Text);


//        myPlanList = CoursesData.instance.getPlans();
        plan = CoursesData.instance.getPlan();
        Log.i("plan", " \n" + plan);


        for (Course course: plan) {
//            planBtv.setText(planBtv.getText()+ course.toString() + "\n" + "\n");
            planAtv.setText(planAtv.getText()+ course.toString() + "\n" + "\n");
        }

//        for (Plan plan: myPlanList) {
        //           planAtv.setText(plan.toString());
//        }
    }


}