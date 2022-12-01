package com.example.c196;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;

public class AssessmentsAdd extends AppCompatActivity {
    EditText editCourse;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editType;
    int courseId;
    int assessmentId;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_add);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        repository = new Repository(getApplication());
        editCourse = findViewById(R.id.edit_course_id);
        editTitle = findViewById(R.id.edit_assessment_title);
        editStart = findViewById(R.id.edit_assessment_start_date);
        editEnd = findViewById(R.id.edit_end_date);
        editType = findViewById(R.id.edit_type);
        courseId = getIntent().getIntExtra("courseID", -1);
        assessmentId = getIntent().getIntExtra("assessmentID", -1);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void saveBtn(View view) {
        Assessment assessment;
        if (assessmentId == -1) {
            int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(newId, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editType.getText().toString(), Integer.parseInt(editCourse.getText().toString()));
            repository.insert(assessment);
        } else {
            System.out.println("Unable to add assessment");
        }

    }
}
