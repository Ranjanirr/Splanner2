package com.example.demouser.splanner;

public class Course {
    private String courseTitle;
    private String courseNumber;
    private String meetingDays;
    private String meetingTime;
    private int startTime;
    private int endTime;

    public Course (String courseNumber, String courseTitle, String meetingDays, String meetingTime){
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.meetingDays = meetingDays;
        this.meetingTime = meetingTime;

        String[] meetTimes = meetingTime.split(" - ");

        String start = meetTimes[0].substring(0, meetTimes[0].length()-2).replaceAll(":", "");
        String dayTime = meetTimes[0].substring(meetTimes[0].length()-2);

        startTime = convertTime(start, dayTime);

        String end = meetTimes[1].substring(0, meetTimes[1].length()-2).replaceAll(":", "");
        String dayTime2 = meetTimes[1].substring(meetTimes[1].length()-2);

        endTime = convertTime(end, dayTime2);

    }

    public String getCourseTitle(){
        return courseTitle;
    }

    public String getCourseNumber(){
        return courseNumber;
    }

    public String getMeetingDays(){
        return meetingDays;
    }

    public String getTimeString(){
        return meetingTime;
    }

    public Integer getStartTime(){
        return startTime;
    }

    public Integer getEndTime(){
        return endTime;
    }

    private Integer convertTime (String tempTime, String day){
        if (day.equals("AM"))
            return Integer.parseInt(tempTime);
        else if (day.equals("PM"))
            return Integer.parseInt(tempTime) + 1200;
        return 0;
    }
}
