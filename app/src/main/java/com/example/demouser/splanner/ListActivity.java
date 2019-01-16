package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private SearchActivity activity;
    private ListView listView;
    private Button btnLookup;
    private ItemsListAdapter myItemsListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        btnLookup = (Button)findViewById(R.id.button);
        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = "Check items:\n";

                for (int i=0; i<CourseList.instance.getCourses().size(); i++){
                    if (CourseList.instance.getCourses().get(i).isChecked()){
                        str += CourseList.instance.getCourses().get(i).getCourseTitle() + "\n";
                    }
                }

                Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });
        myItemsListAdapter = new ItemsListAdapter(this, CourseList.instance.getCourses());
        listView.setAdapter(myItemsListAdapter);
    }


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
            viewHolder.text.setText(itemStr);

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
}
