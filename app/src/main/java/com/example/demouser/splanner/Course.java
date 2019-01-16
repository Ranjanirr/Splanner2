package com.example.demouser.splanner;

public class Course {
    private String courseTitle;
    private String courseNumber;
    private String meetingDays;
    private String meetingTime;
    private String credit;
    private int startTime;
    private int endTime;

    private boolean checked;

    public Course(String courseNumber, String courseTitle, String meetingDays, String meetingTime, String credit) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.meetingDays = meetingDays;
        this.meetingTime = meetingTime;
        this.credit = credit;

        String[] meetTimes = meetingTime.split(" - ");

        String start = meetTimes[0].substring(0, meetTimes[0].length() - 2).replaceAll(":", "");
        String dayTime = meetTimes[0].substring(meetTimes[0].length() - 2);

        startTime = convertTime(start, dayTime);

        String end = meetTimes[1].substring(0, meetTimes[1].length() - 2).replaceAll(":", "");
        String dayTime2 = meetTimes[1].substring(meetTimes[1].length() - 2);

        endTime = convertTime(end, dayTime2);

    }

    public Course(){

    }

    public void setCourseTitle(String courseTitle){ this.courseTitle = courseTitle; }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setMeetingDays(String meetingDays) {
        this.meetingDays = meetingDays;
    }

    public void setCourseTime(String meetingTime) {
        this.meetingTime = meetingTime;

        String[] meetTimes = meetingTime.split(" - ");

        String start = meetTimes[0].substring(0, meetTimes[0].length() - 2).replaceAll(":", "");
        String dayTime = meetTimes[0].substring(meetTimes[0].length() - 2);

        startTime = convertTime(start, dayTime);

        String end = meetTimes[1].substring(0, meetTimes[1].length() - 2).replaceAll(":", "");
        String dayTime2 = meetTimes[1].substring(meetTimes[1].length() - 2);

        endTime = convertTime(end, dayTime2);
    }

    public void setCredit(String credit){
        this.credit = credit;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getMeetingDays() {
        return meetingDays;
    }

    public String getTimeString() {
        return meetingTime;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public String getCredit(){
        return credit;
    }

    // convert time
    private Integer convertTime(String tempTime, String day) {
        if (day.equals("AM"))
            return Integer.parseInt(tempTime);
        else if (day.equals("PM"))
            return Integer.parseInt(tempTime) + 1200;
        return 0;
    }

    public void setChecked(boolean b){
        this.checked = b;
    }

    public boolean isChecked(){
        return checked;
    }

    // return a string which contains the whole information about this class
    public String toString() {
        String res = "";
        res += courseNumber + " " + courseTitle + "\n"
                + meetingDays + " " + meetingTime;
        return res;
    }
}