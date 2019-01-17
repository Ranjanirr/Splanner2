package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.LinkedList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // contains CS classes from Spring 2019 data
    private ItemsListAdapter myItemsListAdapter;
    private ListView listView;
    private Button addButton;
    private Button seeList;
    private Button deselectAll;

    private SearchView simpleSearchView; // inititate a search view


    private static Context myContext;
    private List<Course> listOfCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myContext = this;
        listOfCourses = CoursesData.instance.getCourses();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView)findViewById(R.id.listview);
        addButton = (Button)findViewById(R.id.addCourse);
        seeList = (Button)findViewById(R.id.seeList);
        deselectAll = (Button)findViewById(R.id.deselectAll);

        myItemsListAdapter = new ItemsListAdapter(this, listOfCourses);
        listView.setAdapter(myItemsListAdapter);


        simpleSearchView = findViewById(R.id.searchView);
        simpleSearchView.setQueryHint("Search for classes");

        simpleSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                myItemsListAdapter.reset();
                listView.setAdapter(myItemsListAdapter);
                return false;
            }
        });

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(myItemsListAdapter.searchCourse(query).size() == 0){
                    listView.setAdapter(myItemsListAdapter);
                    Toast.makeText(SearchActivity.this, "No result", Toast.LENGTH_LONG).show();
                }
                else{
                    listView.setAdapter(myItemsListAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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

                for (int i=0; i<listOfCourses.size(); i++){
                    if (listOfCourses.get(i).isChecked()){
                        str += listOfCourses.get(i).getCourseTitle() + "\n";
                    }
                }

                Toast.makeText(SearchActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });

        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyLists();
            }
        });
        deselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i < listOfCourses.size(); i++){
                    if (listOfCourses.get(i).isChecked()){
                        listOfCourses.get(i).setChecked(false);
                    }
                }
                listView.setAdapter(myItemsListAdapter);
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

        public List<Course> searchCourse(String keyword){
            int count = 0;
            List<Course> result = new LinkedList<>();
            while(count < database.size()){
                String title = database.get(count).getCourseTitle().toLowerCase();
                String key = keyword.toLowerCase();
                String number = database.get(count).getCourseNumber().toLowerCase();
                if(title.contains(key) || number.contains(key)){
                    result.add(database.get(count));
                }
                count++;
            }
            list = result;
            return result;
        }

        public void reset(){
            list = database;
        }
    }

    private void onMyLists() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public static Context getContext(){
        return myContext;
    }
}
