package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentDateStart;
    private String assessmentDateEnd;

    public Assessment(int assessmentID, String assessmentDateStart, String assessmentDateEnd) {
        this.assessmentID = assessmentID;
        this.assessmentDateEnd = assessmentDateEnd;
        this.assessmentDateStart = assessmentDateStart;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
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
}
