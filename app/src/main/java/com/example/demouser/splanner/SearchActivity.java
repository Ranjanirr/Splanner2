package com.example.demouser.splanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class SearchActivity extends AppCompatActivity {

    private CheckBox checkBox1;
    private Course temp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Button checkBtn = findViewById(R.id.checkButton);
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckMyLists();
            }
        });


        temp1 = new Course("COMSC-109-01", "iDesign Studio",
                "Monday, Wednesday", "02:55PM - 04:10PM");

        checkBox1 = findViewById(R.id.checkBox);
        checkBox1.setText(temp1.getCourseTitle());

        Log.i("Test", "the text for checkBox1" + checkBox1.getText());

        Button addBtn = findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToMyLists();
            }
        });

    }

    private void addToMyLists() {
        if (checkBox1.isChecked()) {

        }
    }

    // show my list on a new page
    private void onCheckMyLists() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
