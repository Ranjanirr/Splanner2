package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Button deleteButton;
    private Button generateButton;
    private Button selectButton;
    private ListView listView;
    private ItemsListAdapter courseListAdapter;

    private List<Course> myCourseList;
    private List<Course> plan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        deleteButton = (Button) findViewById(R.id.delete);
        generateButton = (Button) findViewById(R.id.generatePlan);
        selectButton = (Button) findViewById(R.id.selectAll);
        listView = (ListView)findViewById(R.id.listview);

        myCourseList = new LinkedList<>();
        myCourseList = CoursesData.instance.getSelectedCourses();
        plan =  CoursesData.instance.getPlan();

        courseListAdapter = new ItemsListAdapter(this, myCourseList);
        listView.setAdapter(courseListAdapter);


        // delete selected courses
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "You deleted:\n";

                for (int i=0; i<myCourseList.size(); i++){
                    if (myCourseList.get(i).isChecked()){
                        str += myCourseList.get(i).getCourseTitle() + "\n";
                        myCourseList.set(i, null);
                    }
                }

                int count = 0;
                while (count < myCourseList.size()) {
                    if (myCourseList.get(count) == null){
                        myCourseList.remove(count);
                        count--;
                    }
                    count++;
                }

                Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
                listView.setAdapter(courseListAdapter);

            }
        });

        // generate course plan
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("test", "hhhh?");
                generateClassPlans();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this,
                        ((Course)(parent.getItemAtPosition(position))).getCourseTitle(),
                        Toast.LENGTH_LONG).show();
            }});

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i < myCourseList.size(); i++){
                    if (!myCourseList.get(i).isChecked()){
                        myCourseList.get(i).setChecked(true);
                    }
                }
                listView.setAdapter(courseListAdapter);
            }
        });
    }

    /* Got from http://android-er.blogspot.com/2017/03/android-listview-with-checkbox.html */
    static class ViewHolder {
        CheckBox checkBox;
        TextView text;
    }

    /* Got from http://android-er.blogspot.com/2017/03/android-listview-with-checkbox.html */
    public class ItemsListAdapter extends BaseAdapter {

        private Context context;
        private List<Course> list;

        private List<Course> database;
        ItemsListAdapter(Context c, List<Course> l) {
            context = c;
            database = l;
            list = database;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public boolean isChecked(int position) {
            return list.get(position).isChecked();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            // reuse views
            SearchActivity.ViewHolder viewHolder = new SearchActivity.ViewHolder();
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.row, null);

                viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.rowCheckBox);
                viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (SearchActivity.ViewHolder) rowView.getTag();
            }

            viewHolder.checkBox.setChecked(list.get(position).isChecked());

            final String itemStr = list.get(position).getCourseTitle();
            final String courseNum = list.get(position).getCourseNumber();
            viewHolder.text.setText(courseNum + "\n" + itemStr);

            viewHolder.checkBox.setTag(position);

            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean newState = !list.get(position).isChecked();
                    list.get(position).setChecked(newState);
                    Toast.makeText(getApplicationContext(),
                            "You selected: \n"+itemStr,
                            Toast.LENGTH_LONG).show();
                }
            });

            viewHolder.checkBox.setChecked(isChecked(position));

            return rowView;
        }
    }

    private void generateClassPlans() {
        ArrayList<Course> currentPlan = new ArrayList<>();
        currentPlan.clear();

        for (Course c: myCourseList) {
            if (c.isChecked()) {
                currentPlan.add(c);
            }
        }
//        Log.i("test", "\n" + currentPlan);

        // if there are less than 4 courses
        // add all the un-overlap courses to plans
//        if (currentPlan.size() <= 4) {
//            Log.i("enter", "\n" + currentPlan);
            for (int i = 0; i < currentPlan.size(); i++) {
                for (int j = i+1; j < currentPlan.size(); j++) {
                   if (currentPlan.get(i).isConflict(currentPlan.get(j))) {
                       currentPlan.remove(currentPlan.get(i));
                   }
                }
            }
//            Log.i("after", "\n" + currentPlan);
            plan.clear();
            plan.addAll(currentPlan);
//            Log.i("after after", "\n" + currentPlan);
//            Log.i("after that my plan is", "\n" + plan);
            Intent intent = new Intent(this, PlansActivity.class);
            startActivity(intent);
//        }

    }
}


