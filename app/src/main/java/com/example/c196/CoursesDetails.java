package com.example.c196;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CoursesDetails extends AppCompatActivity {
    // Setting the fields for the detail view
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editStatus_;
    EditText editName;
    EditText editPhone;
    EditText editEmail;
    String courseTitle;
    String courseDateStart;
    String courseDateEnd;
    String status;
    String instructorName;
    String instructorPhone;
    String instructorEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);
        editTitle=findViewById(R.id.editCourseTitle);
        editStart=findViewById(R.id.editCourseDateStart);
        editEnd=findViewById(R.id.editCourseDateEnd);
        editStatus_=findViewById(R.id.editStatus);
        editName=findViewById(R.id.editInstructorName);
        editPhone=findViewById(R.id.editInstructorPhone);
        editEmail=findViewById(R.id.editInstructorEmail);
        // Using getDoubleExtra will require a default value
        courseTitle=getIntent().getStringExtra("courseTitle");
        courseDateStart=getIntent().getStringExtra("courseDateStart");
        courseDateEnd=getIntent().getStringExtra("courseDateEnd");
        status=getIntent().getStringExtra("status");
        instructorName=getIntent().getStringExtra("instructorName");
        instructorPhone=getIntent().getStringExtra("instructorPhone");
        instructorEmail=getIntent().getStringExtra("instructorEmail");

        editTitle.setText(courseTitle);
        editStart.setText(courseDateStart);
        editEnd.setText(courseDateEnd);
        editStatus_.setText(status);
        editName.setText(instructorName);
        editPhone.setText(instructorPhone);
        editEmail.setText(instructorEmail);
    }

}
