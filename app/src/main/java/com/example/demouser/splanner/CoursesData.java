package com.example.demouser.splanner;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CoursesData {

    // contains CS classes from Spring 2019 data
    private HashMap<String, Course> courseList;
    public List<Course> courseDisplayList;
    public List<Course> selectedCourses;

    public static final CoursesData instance = new CoursesData(SearchActivity.getContext());

    private CoursesData(Context myContext){
        courseList = new HashMap<>();
        courseDisplayList = new LinkedList<>();
        selectedCourses = new ArrayList<>();


        // parsing course information and store to a HashMap and LinkedList
        AssetManager assetManager = myContext.getAssets();
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
            Toast toast = Toast.makeText(myContext, "Could not load courses", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public List<Course> getCourses(){
        return courseDisplayList;
    }

    public List<Course> getSelectedCourses() {
        return selectedCourses;
    }

    public void addToSelectedCourses(ArrayList<Course> a) { selectedCourses.addAll(a);}
}
