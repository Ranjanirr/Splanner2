package com.example.demouser.splanner;

import java.util.ArrayList;
import java.util.List;

public class CourseList {

    public static final CourseList instance = new CourseList();
    private List<Course> courses = new ArrayList<>();
    private List<Course> selectedCourses = new ArrayList<>();

    private CourseList(){

    }

    public List<Course> getCourses() {
        return courses;
    }
    public List<Course> getSelectedCourses() {
        return selectedCourses;
    }
    public void addCourse(Course course){
        courses.add(course);
    }
    public void selectCourse(Course course){
        selectedCourses.add(course);
    }

}
