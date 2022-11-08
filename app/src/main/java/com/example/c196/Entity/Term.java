package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "terms")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termDateStart;
    private String termDateEnd;
    private String termTitle;

    public Term(int termID, String termDateStart, String termDateEnd, String termTitle) {
        this.termID = termID;
        this.termDateStart = termDateStart;
        this.termDateEnd = termDateEnd;
        this.termTitle = termTitle;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "termDateStart='" + termDateStart + '\'' +
                ", termDateEnd='" + termDateEnd + '\'' +
                ", termTitle='" + termTitle + '\'' +
                ", termID=" + termID +
                '}';
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getTermDateStart() {
        return termDateStart;
    }

    public void setTermDateStart(String termDateStart) {
        this.termDateStart = termDateStart;
    }

    public String getTermDateEnd() {
        return termDateEnd;
    }

    public void setTermDateEnd(String termDateEnd) {
        this.termDateEnd = termDateEnd;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }
}
