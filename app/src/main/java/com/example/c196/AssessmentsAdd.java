package com.example.c196;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;

import java.util.List;

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
    boolean isValidCourse;

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

    boolean isDigitsOnly(String str) {
        return str.isEmpty() && TextUtils.isDigitsOnly(str);
    }

    public void saveBtn(View view) {
        // TODO: The courseId is not correctly flowing down, and currently requires explicit generation from the EditText
        String courseIdString = editCourse.getText().toString();
        isValidCourse = false;
        boolean isChar = !isDigitsOnly(courseIdString);
        boolean courseIdIsEmpty = courseIdString.equals("");
        if (!courseIdIsEmpty && !isChar) {
            courseId = Integer.parseInt(editCourse.getText().toString());
        }
        List<Course> allCourses = repository.getAllCourses();
        for (Course course : allCourses) {
            if (course.getCourseID() == Integer.parseInt(editCourse.getText().toString())) {
                isValidCourse = true;
            }
        }

        if (isValidCourse) {
            assessmentCount = repository.getCourseAssessmentsCount(Integer.parseInt(editCourse.getText().toString()));
        }

        Assessment assessment;
        // TODO: Challenge talking point, assessment counts
        if (isValidCourse && assessmentId == -1 && assessmentCount < 5) {
            int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(newId, editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editType.getText().toString(), Integer.parseInt(editCourse.getText().toString()));
            repository.insert(assessment);
            Intent intent = new Intent(AssessmentsAdd.this, Assessments.class);
            startActivity(intent);
        } else if (courseIdIsEmpty) {
            Toast.makeText(AssessmentsAdd.this, "Course ID is required.", Toast.LENGTH_LONG).show();
        } else if (!isValidCourse) {
            Toast.makeText(AssessmentsAdd.this, "Course ID not found", Toast.LENGTH_LONG).show();
        } else if (assessmentCount >= 5) {
            Toast.makeText(AssessmentsAdd.this, "Assessment limit (5) has already reached", Toast.LENGTH_LONG).show();
        }
    }
}
