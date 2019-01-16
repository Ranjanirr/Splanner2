package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // contains CS classes from Spring 2019 data
    private HashMap<String, Course> courseList = new HashMap<>();
    public List<Course> courseDisplayList;
    private ItemsListAdapter myItemsListAdapter;
    private ListView listView;
    private Button addButton;


    private SearchView simpleSearchView; // inititate a search view
    private CharSequence query; // get the query string currently in the text field

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView)findViewById(R.id.listview);
        addButton = (Button)findViewById(R.id.addCourse);



        simpleSearchView = (SearchView) findViewById(R.id.searchView);
        simpleSearchView.setQueryHint("Search for classes");
        query = simpleSearchView.getQuery();

//        Log.i("userinput", query.toString());






        initCourseList();
        myItemsListAdapter = new ItemsListAdapter(this, courseDisplayList);
        listView.setAdapter(myItemsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SearchActivity.this,
                        ((Course)(parent.getItemAtPosition(position))).getCourseTitle(),
                        Toast.LENGTH_LONG).show();
            }});

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "Check items:\n";

                for (int i=0; i<courseDisplayList.size(); i++){
                    if (courseDisplayList.get(i).isChecked()){
                        str += i + "\n";
                    }
                }

                Toast.makeText(SearchActivity.this, str, Toast.LENGTH_LONG).show();
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

        ItemsListAdapter(Context c, List<Course> l) {
            context = c;
            list = l;
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
            ViewHolder viewHolder = new ViewHolder();
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.row, null);

                viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.rowCheckBox);
                viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
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
                            itemStr + "setOnClickListener\nchecked: " + newState,
                            Toast.LENGTH_LONG).show();
                }
            });

            viewHolder.checkBox.setChecked(isChecked(position));

            return rowView;
        }
    }



    private void onCheckMyLists() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void initCourseList(){
        courseDisplayList = new LinkedList<>();
        // parsing course information and store to a HashMap and LinkedList
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("courses.txt");
            BufferedReader in  = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = in.readLine()) != null){
                String[] courseInfo = line.split("\\|");
                Course newCourse = new Course();
                newCourse.setCourseNumber(courseInfo[0]);
                newCourse.setCourseTitle(courseInfo[1]);
                newCourse.setMeetingDays(courseInfo[2]);
                newCourse.setCourseTime(courseInfo[3]);
                newCourse.setCredit(courseInfo[4]);

                courseList.put(newCourse.getCourseTitle(), newCourse);
                courseDisplayList.add(newCourse);
            }

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load courses", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
