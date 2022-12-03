package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Note;

public class NotesDetails extends AppCompatActivity {
    EditText editNote;
    EditText editCourseId;
    EditText editNoteId;
    EditText editTitle;
    String noteText;
    String noteTitle;
    int noteId;
    int courseId;
    Repository repository;
    Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Repository repo = new Repository(getApplication());
        repository = repo;
        editNote = findViewById(R.id.editCourseNote);
        editCourseId = findViewById(R.id.editCourseId);
        editNoteId = findViewById(R.id.editNoteId);
        editTitle = findViewById(R.id.editTitle);

        noteText = getIntent().getStringExtra("noteText");
        noteTitle = getIntent().getStringExtra("noteTitle");
        noteId = getIntent().getIntExtra("noteID", -1);
        courseId = getIntent().getIntExtra("courseID", -1);

        editNote.setText(noteText);
        editTitle.setText(noteTitle);
        editCourseId.setText(Integer.toString(courseId));
        editNoteId.setText(Integer.toString(noteId));
    }

    public void shareBtn(View view) {
    }

    public void saveBtn(View view) {
        Note note;
        if (noteId == -1) {
            int newID = repository.getAllNotes().get(repository.getAllNotes().size() - 1).getNoteID() + 1;
            note = new Note(newID, Integer.parseInt(editCourseId.getText().toString()), editTitle.getText().toString(), editNote.getText().toString());
            repository.insert(note);
        } else {
            note = new Note(Integer.parseInt(editNoteId.getText().toString()), Integer.parseInt(editCourseId.getText().toString()), editTitle.getText().toString(), editNote.getText().toString());
            repository.update(note);
        }
    }

    public void deleteBtn(View view) {
        if (noteId != -1) {
            for (Note note : repository.getAllNotes()) {
                if (note.getNoteID() == noteId) currentNote = note;
            }
            repository.delete(currentNote);
            Intent intent = new Intent(NotesDetails.this, Courses.class);
            startActivity(intent);
        }
    }
}
