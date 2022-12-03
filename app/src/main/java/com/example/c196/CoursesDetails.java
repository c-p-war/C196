package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Note;

import java.util.ArrayList;
import java.util.List;

public class CoursesDetails extends AppCompatActivity {
    EditText editTerm;
    EditText editId;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editStatus_;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    int courseId;
    int termId;
    String courseTitle;
    String courseDateStart;
    String courseDateEnd;
    String status;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    Repository repository;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);
        editTerm = findViewById(R.id.editTermId);
        editId = findViewById(R.id.editCourseId);
        editTitle = findViewById(R.id.editCourseTitle);
        editStart = findViewById(R.id.editCourseDateStart);
        editEnd = findViewById(R.id.editCourseDateEnd);
        editStatus_ = findViewById(R.id.editStatus);
        editName = findViewById(R.id.editInstructorName);
        editPhone = findViewById(R.id.editInstructorPhone);
        editEmail = findViewById(R.id.editInstructorEmail);

        termId = getIntent().getIntExtra("termID", -1);
        courseId = getIntent().getIntExtra("courseID", -1);
        courseTitle = getIntent().getStringExtra("courseTitle");
        courseDateStart = getIntent().getStringExtra("courseDateStart");
        courseDateEnd = getIntent().getStringExtra("courseDateEnd");
        status = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");

        editTerm.setText(Integer.toString(termId));
        editId.setText(Integer.toString(courseId));
        editTitle.setText(courseTitle);
        editStart.setText(courseDateStart);
        editEnd.setText(courseDateEnd);
        editStatus_.setText(status);
        editName.setText(instructorName);
        editPhone.setText(instructorPhone);
        editEmail.setText(instructorEmail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Assessments list
        RecyclerView recyclerView = findViewById(R.id.course_assessments_recycler);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        Repository repo = new Repository(getApplication());
        repository = repo;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> matchingAssessments = new ArrayList<>();
        for (Assessment a : repo.getAllAssessments()) {
            if (a.getCourseID() == courseId) matchingAssessments.add(a);
        }
        adapter.setAssessments(matchingAssessments);
        // Notes list
        RecyclerView recyclerViewNotes = findViewById(R.id.course_notes_recycler);
        final NoteAdapter noteAdapter = new NoteAdapter(this);
        Repository repoNote = new Repository(getApplication());
        recyclerViewNotes.setAdapter(noteAdapter);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        List<Note> matchingNotes = new ArrayList<>();
        for (Note n : repoNote.getAllNotes()) {
            if (n.getCourseID() == courseId) matchingNotes.add(n);
        }
        noteAdapter.setNotes(matchingNotes);
    }

    public void saveBtn(View view) {
        Course course;
        if (courseId == -1) {
            int newID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            course = new Course(newID, Integer.parseInt(editTerm.getText().toString()), editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus_.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());
            repository.insert(course);
            Intent intent = new Intent(CoursesDetails.this, Courses.class);
            startActivity(intent);
        } else {
            course = new Course(Integer.parseInt(editId.getText().toString()), Integer.parseInt(editTerm.getText().toString()), editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus_.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());
            repository.update(course);
            Intent intent = new Intent(CoursesDetails.this, Courses.class);
            startActivity(intent);
        }
    }

    public void deleteBtn(View view) {
        if (courseId != -1){
            for (Course course : repository.getAllCourses()) {
                if (course.getCourseID() == courseId) currentCourse = course;
            }
            repository.delete(currentCourse);
            Intent intent = new Intent(CoursesDetails.this, Courses.class);
            startActivity(intent);
        }
    }

    public void addNoteBtn(View view) {
        Intent intent = new Intent(CoursesDetails.this, NotesAdd.class);
        startActivity(intent);
    }
}
