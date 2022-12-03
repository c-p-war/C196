package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;

public class AssessmentsDetails extends AppCompatActivity {
    EditText editCourseId;
    EditText editAId;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editType;
    String aTitle;
    String aStart;
    String aEnd;
    String aType;
    int assessmentId;
    int courseId;
    Repository repository;
    Assessment currentAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesments_details);
        Repository repo = new Repository(getApplication());
        repository = repo;
        editAId = findViewById(R.id.editAId);
        editCourseId = findViewById(R.id.editACourseId);
        editTitle = findViewById(R.id.editATitle);
        editStart = findViewById(R.id.editAStart);
        editEnd = findViewById(R.id.editAEnd);
        editType = findViewById(R.id.editAType);
        courseId = getIntent().getIntExtra("courseID", -1);
        assessmentId = getIntent().getIntExtra("assessmentID", -1);
        aTitle = getIntent().getStringExtra("assessmentTitle");
        aStart = getIntent().getStringExtra("assessmentDateStart");
        aEnd = getIntent().getStringExtra("assessmentDateEnd");
        aType = getIntent().getStringExtra("assessmentType");
        editAId.setText(Integer.toString(assessmentId));
        editCourseId.setText(Integer.toString(courseId));
        editTitle.setText(aTitle);
        editStart.setText(aStart);
        editEnd.setText(aEnd);
        editType.setText(aType);
    }

    public void saveBtn(View view) {
        Assessment assessment;
        if (assessmentId == -1) {
            int newID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(
                    newID,
                    editTitle.getText().toString(),
                    editStart.getText().toString(),
                    editEnd.getText().toString(),
                    editType.getText().toString(),
                    Integer.parseInt(editCourseId.getText().toString())
            );
            repository.insert(assessment);
            Intent intent = new Intent(AssessmentsDetails.this, Assessments.class);
            startActivity(intent);
        } else {
            assessment = new Assessment(
                    Integer.parseInt(editAId.getText().toString()),
                    editTitle.getText().toString(),
                    editStart.getText().toString(),
                    editEnd.getText().toString(),
                    editType.getText().toString(),
                    Integer.parseInt(editCourseId.getText().toString()));
            repository.update(assessment);
            Intent intent = new Intent(AssessmentsDetails.this, Assessments.class);
            startActivity(intent);
        }
    }

    public void deleteBtn(View view) {
        if (assessmentId != -1) {
            for (Assessment assessment : repository.getAllAssessments()) {
                if (assessment.getAssessmentID() == assessmentId) currentAssessment = assessment;
            }
            repository.delete(currentAssessment);
            Intent intent = new Intent(AssessmentsDetails.this, Assessments.class);
            startActivity(intent);
        }
    }
}
