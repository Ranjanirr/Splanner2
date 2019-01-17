package com.example.demouser.splanner;

import android.app.Activity;
import android.content.Context;
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

import java.util.LinkedList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Button deleteButton;
    private Button generateButton;
    private Button selectButton;
    private ListView listView;
    private ItemsListAdapter courseListAdapter;

    private List<Course> myCourseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        deleteButton = (Button) findViewById(R.id.delete);
        generateButton = (Button) findViewById(R.id.generatePlan);
        selectButton = (Button) findViewById(R.id.selectAll);
        listView = (ListView)findViewById(R.id.listview);

//        myCourseList = new LinkedList<>();
//        initCourseData();
        myCourseList = CoursesData.instance.getSelectedCourses();

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
                            itemStr + "setOnClickListener\nchecked: " + newState,
                            Toast.LENGTH_LONG).show();
                }
            });

            viewHolder.checkBox.setChecked(isChecked(position));

            return rowView;
        }
    }
    //    private SearchActivity activity;
//    private ListView listView;
//    private Button btnLookup;
//
//    private ArrayList<Course> myCourseList = new ArrayList<>();
//    private ArrayList<Plan> myPlanList = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
//
//        btnLookup = (Button) findViewById(R.id.button);
//        final LinearLayout classLayout = (LinearLayout) findViewById(R.id.linny);
//        final TextView classView = findViewById(R.id.textView7);
//
//        btnLookup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String str = "Check items:\n";
//
//                for (int i = 0; i < CourseList.instance.getCourses().size(); i++) {
//                    if (CourseList.instance.getCourses().get(i).isChecked()) {
//                        classView.setText(classView.getText() + CourseList.instance.getCourses().get(i).getCourseTitle() + "\n");
//
//                    }
//
//                    Toast.makeText(ListActivity.this, str, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

//        initCourseData();

//        Button generateBtn = findViewById(R.id.generateButton);
//        generateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                generateClassPlans(new Plan(), new HashSet<Course>(),0, 0);
//            }
//        });
//
//        //Log.i("test","conflict? "+ myCourseList.get(0).isConflict(myCourseList.get(1)));
//    }

    private void initCourseData() {
        Course temp1 = new Course("COMSC-109-01", "iDesign Studio", "Monday", "02:55PM - 04:10PM", "4");
        myCourseList.add(temp1);
        myCourseList.add(new Course("COMSC-132-01", "Engineering for Everyone", "Thursday", "01:30PM - 02:45PM", "4"));
        myCourseList.add(new Course("COMSC-151AA-01", "CS Prob Solv: Algorithmic Art", "Monday, Wednesday", "02:55PM - 04:10PM", "4"));
        myCourseList.add(new Course("COMSC-151AAL-01", "CS Prob Solv: Algorithmic Lab", "Friday", "01:30PM - 04:20PM", "4"));
        myCourseList.add(new Course("COMSC-151AR-01", "CS Prov Solv: Artificl Intel.", "Tuesday, Thursday", "08:30AM - 09:45AM", "4"));
        myCourseList.add(new Course("COMSC-205-01", "Data Structures", "Tuesday, Thursday", "11:30AM - 12:45PM", "4"));
        myCourseList.add(new Course("COMSC-226-01", "Engineering Robotic Systems", "Tuesday", "10:00AM - 11:15AM", "4"));
        myCourseList.add(new Course("COMSC-312-01", "Algorithms", "Monday, Wednesday", "11:00AM - 12:15PM", "4"));
        myCourseList.add(new Course("COMSC-322-01", "Operating Systems", "Tuesday, Thursday", "11:30AM - 12:45PM", "4"));
        myCourseList.add(new Course("COMSC-335-01", "Machine Learning", "Tuesday, Thursday", "10:00AM - 11:15AM", "4"));
    }
//        });




//     }
//        Log.i("another test", "is conflict? " +myCourseList.get(0).isTimeConflict(myCourseList.get(7)));
//        Log.i("another test", "course1 start time? " +myCourseList.get(7).getStartTime() +" " + myCourseList.get(7).getEndTime());
//    }

    // for all selected classes (stored in myCourseList)
    // choose 4 from them without any time slot overlapped
    // for now we only aim for generating 2 plans
    // courseNum: how many courses are there in current course plan
    // index: marks the startpoint that had been used in myCourseList
//    private void generateClassPlans(Plan currentPlan, HashSet<Course> candidates, int courseNum, int index) {
//       if (courseNum == 3) {
//           myPlanList.add(currentPlan);
//           Log.i("test", "my current plan length: "+ currentPlan.size());
//           Log.i("test", "my current plan: "+ currentPlan.toString());
//           return;
//       }
//       else {
//
//           // initialize step
//            if (currentPlan.isEmpty()) {
//                Course currentCourse = myCourseList.get(index);
//                currentPlan.add(currentCourse);
//                for (Course c: myCourseList) {
//                    if (!c.isConflict(currentCourse)) {
//                        candidates.add(c);
//                    }
//                }
//            }
//            // re-edit the hash set
//            // remove things from the set
//            else {
//                // the last item in current plan
//                Course currentCourse = currentPlan.get(currentPlan.size()-1);
//                Log.i("another test", "current course : \n"+ currentCourse.toString());
//                for (Course c: myCourseList) {
//                    if (c.isConflict(currentCourse)) {
//                        candidates.remove(c);
//                    }
//                }
//           }
//
//           Log.i("another test", "my candidates are: \n"+ candidates.toString() + "\n" +candidates.size() + "\n");
//
//           // recursive call for every candidate
//           for (Course candidate: candidates) {
//                currentPlan.add(candidate);
//                generateClassPlans(currentPlan, candidates,courseNum+1, index+1);
//               // break;
//                currentPlan.remove(candidate);
//           }
//       }
//    }

}

