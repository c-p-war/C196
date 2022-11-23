
package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "notes",
        foreignKeys = @ForeignKey(entity = Course.class,
                parentColumns = "courseID", childColumns = "courseID", onDelete = CASCADE))

public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteID;

    private int courseID;
    private String noteTitle;

    @Override
    public String toString() {
        return "EntityNote{" +
                "noteID=" + noteID +
                ", courseID=" + courseID +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteText='" + noteText + '\'' +
                '}';
    }

    private String noteText;

    public Note(int noteID, int courseID, String noteTitle, String noteText) {
        this.noteID = noteID;
        this.courseID = courseID;
        this.noteTitle = noteTitle;
        this.noteText = noteText;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

}
