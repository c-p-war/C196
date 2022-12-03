package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Note;

public class NotesAdd extends AppCompatActivity {
    EditText editNote;
    EditText editCourseId;
    EditText editTitle;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editNote = findViewById(R.id.editCourseNote);
        editCourseId = findViewById(R.id.editCourseId);
        editTitle = findViewById(R.id.editTitle);
        Repository repo = new Repository(getApplication());
        repository = repo;
    }

    public void saveBtn(View view) {
        Note note;
        int newID = repository.getAllNotes().get(repository.getAllNotes().size() - 1).getNoteID() + 1;
        note = new Note(newID, Integer.parseInt(editCourseId.getText().toString()), editTitle.getText().toString(), editNote.getText().toString());
        repository.insert(note);
        Intent intent = new Intent(NotesAdd.this, Courses.class);
        startActivity(intent);
    }
}
