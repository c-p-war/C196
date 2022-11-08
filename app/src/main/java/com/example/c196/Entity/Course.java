package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "courses",
        foreignKeys = @ForeignKey(entity = Term.class,
                parentColumns = "termID", childColumns = "termID", onDelete = CASCADE))
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private int termID;
    private String courseTitle;
    private String courseDateStart;
    private String courseDateEnd;
    // in progress, completed, planned
    private String status;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;

    public Course(int courseID, int termID, String courseTitle, String courseDateStart, String courseDateEnd, String status, String instructorName, String instructorPhone, String instructorEmail) {
        this.courseID = courseID;
        this.termID = termID;
        this.courseTitle = courseTitle;
        this.courseDateStart = courseDateStart;
        this.courseDateEnd = courseDateEnd;
        this.status = status;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", termID=" + termID +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseDateStart='" + courseDateStart + '\'' +
                ", courseDateEnd='" + courseDateEnd + '\'' +
                ", status='" + status + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", instructorPhone='" + instructorPhone + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDateStart() {
        return courseDateStart;
    }

    public void setCourseDateStart(String courseDateStart) {
        this.courseDateStart = courseDateStart;
    }

    public String getCourseDateEnd() {
        return courseDateEnd;
    }

    public void setCourseDateEnd(String courseDateEnd) {
        this.courseDateEnd = courseDateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}
