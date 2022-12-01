package com.example.c196;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Note;

public class CoursesNotes extends AppCompatActivity {
    EditText editNote;
    String noteText;
    int noteId;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Get course id
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_notes);
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
}
