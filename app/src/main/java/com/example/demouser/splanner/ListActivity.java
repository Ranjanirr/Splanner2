package com.example.demouser.splanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private SearchActivity activity;

    private Button btnLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btnLookup = (Button)findViewById(R.id.button);
        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("List Activity", "Display the course size: " + activity.courseDisplayList.size());
                String str = "Check items:\n";

                for (int i=0; i<activity.courseDisplayList.size(); i++){
                    if (activity.courseDisplayList.get(i).isChecked()){
                        str += activity.courseDisplayList.get(i).getCourseTitle() + "\n";
                    }
                }

                Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });
    }
}
