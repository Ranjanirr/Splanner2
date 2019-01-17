package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {
    private SearchActivity activity;
    private ListView listView;
    private Button btnLookup;

    private ArrayList<Course> myCourseList = new ArrayList<>();
    private ArrayList<Plan> myPlanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnLookup = (Button) findViewById(R.id.button);
        final LinearLayout classLayout = (LinearLayout) findViewById(R.id.linny);
        final TextView classView = findViewById(R.id.textView7);

        ArrayList<Course> selectedCourse = (ArrayList<Course>) CoursesData.instance.getSelectedCourses();
        Log.i("test", "the selected courses are "+selectedCourse);

        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = "Check items:\n";

                for (int i = 0; i < CourseList.instance.getCourses().size(); i++) {
                    if (CourseList.instance.getCourses().get(i).isChecked()) {
                        classView.setText(classView.getText() + CourseList.instance.getCourses().get(i).getCourseTitle() + "\n");

                    }

                    Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

