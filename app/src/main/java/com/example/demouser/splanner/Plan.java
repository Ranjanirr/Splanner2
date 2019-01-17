package com.example.demouser.splanner;

import java.util.ArrayList;

public class Plan
{
    protected ArrayList<Course> plan;

    protected Plan() {
        plan = new ArrayList<>();
    }

    protected void add(Course c) {
        plan.add(c);
    }

    protected int size() {
        return plan.size();
    }

    protected boolean isEmpty() {
        return plan.isEmpty();
    }

    protected Course get(int index) {
        return plan.get(index);
    }

    public String toString(){
        String res = "";

        for (Course c: plan) {
            res += c.toString() + "\n";
        }

        return res;
    }

    public ArrayList<Course> getPlan() {
        return plan;
    }

    protected void remove(Course c) {
        plan.remove(c);
    }

    protected void remove(int index) {
        plan.remove(index);
    }

}
