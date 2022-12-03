package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Note;

public class CoursesNotes extends AppCompatActivity {
    EditText editNote;
    String noteText;
    int noteId;
    Repository repository;
    Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Get course id
        // TODO: Add back logic
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_notes);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        editNote = findViewById(R.id.courseNote);
        noteText = getIntent().getStringExtra("noteText");
        noteId = getIntent().getIntExtra("noteID", -1);
        editNote.setText(noteText);
        Repository repo = new Repository(getApplication());
        repository = repo;
    }

    public void shareBtn(View view) {
    }

    public void saveBtn(View view) {
        // TODO: ensure a blank note is available is none are listed
        Note note;
        if(noteId == -1){
            int newID = repository.getAllNotes().get(repository.getAllNotes().size() -1).getNoteID() + 1;
//            note = new Note(newID, )
        }
    }

    public void deleteBtn(View view) {
        if (noteId != -1){
            for (Note note : repository.getAllNotes()) {
                if (note.getNoteID() == noteId) currentNote = note;
            }
            repository.delete(currentNote);
            Intent intent = new Intent(CoursesNotes.this, Courses.class);
            startActivity(intent);
        }
    }
}
