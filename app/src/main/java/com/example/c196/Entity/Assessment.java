package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "assessments",
        foreignKeys = @ForeignKey(entity = Course.class,
                parentColumns = "courseID", childColumns = "courseID", onDelete = CASCADE))
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentTitle;
    private String assessmentDateStart;
    private String assessmentDateEnd;
    private String assessmentType;

    private int courseID;

    public Assessment(int assessmentID, String assessmentTitle, String assessmentDateStart, String assessmentDateEnd, String assessmentType, int courseID) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.assessmentDateEnd = assessmentDateEnd;
        this.assessmentDateStart = assessmentDateStart;
        this.assessmentType = assessmentType;
        this.courseID = courseID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentTitle(){return assessmentTitle;}
    public void setAssessmentTitle(String assessmentTitle){
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentDateStart() {
        return assessmentDateStart;
    }

    public void setAssessmentDateStart(String assessmentDateStart) {
        this.assessmentDateStart = assessmentDateStart;
    }

    public String getAssessmentDateEnd() {
        return assessmentDateEnd;
    }

    public void setAssessmentDateEnd(String assessmentDateEnd) {
        this.assessmentDateEnd = assessmentDateEnd;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
