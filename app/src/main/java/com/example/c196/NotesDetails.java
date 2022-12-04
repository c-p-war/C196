package com.example.c196;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_notes_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, noteText);
                sendIntent.putExtra(Intent.EXTRA_TITLE, noteTitle);
                // Bellow will not work without text/plain
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
            Intent intent = new Intent(NotesDetails.this, Courses.class);
            startActivity(intent);
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
