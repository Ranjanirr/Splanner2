package com.example.demouser.splanner;

import java.util.ArrayList;
import java.util.List;

public class CourseList {

    public static final CourseList instance = new CourseList();
    private List<Course> courses;

    private CourseList(){
        courses = new ArrayList();
    }

    public List<Course> getCourses() {
        return courses;
    }
    public void addCourse(Course course){
        courses.add(course);
    }
}
