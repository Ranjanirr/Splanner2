package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private SearchActivity activity;
    private ListView listView;
    private Button btnLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final LinearLayout classLayout = (LinearLayout) findViewById(R.id.linny);
        final TextView classView = findViewById(R.id.textView7);

        String str = "Check items:\n";

        for (int i = 0; i < CourseList.instance.getCourses().size(); i++) {
            if (CourseList.instance.getCourses().get(i).isChecked()) {

                classView.setText( classView.getText() + CourseList.instance.getCourses().get(i).getCourseTitle() +"\n" );

                }



        }






     }
}
