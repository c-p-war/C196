package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;

public class AssessmentsAdd extends AppCompatActivity {
    EditText editCourse;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editType;
    int courseId;
    int assessmentId;
    int assessmentCount;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repository = new Repository(getApplication());
        editCourse = findViewById(R.id.edit_course_id);
        editTitle = findViewById(R.id.edit_assessment_title);
        editStart = findViewById(R.id.edit_assessment_start_date);
        editEnd = findViewById(R.id.edit_end_date);
        editType = findViewById(R.id.edit_type);
        assessmentId = getIntent().getIntExtra("assessmentID", -1);
    }

    public void saveBtn(View view) {
        courseId = Integer.parseInt(editCourse.getText().toString());
        if (courseId > 0) {
        assessmentCount = repository.getCourseAssessmentsCount(courseId);
        }
        Assessment assessment;
        // TODO: Challenge talking point, assessment counts
        if (assessmentId == -1 && assessmentCount < 5) {
            int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(newId, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editType.getText().toString(), Integer.parseInt(editCourse.getText().toString()));
            repository.insert(assessment);
            Intent intent = new Intent(AssessmentsAdd.this, Assessments.class);
            startActivity(intent);
        } else {
            System.out.println("Too many assessments");
            // TODO: validation alerts
        }
    }
}
