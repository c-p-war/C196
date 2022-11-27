package com.example.c196;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Term;

import java.util.ArrayList;
import java.util.List;

public class CoursesDetails extends AppCompatActivity {
    // Setting the fields for the detail view
    EditText editTerm;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);
        editTerm=findViewById(R.id.editTermId);
        editTitle=findViewById(R.id.editCourseTitle);
        editStart=findViewById(R.id.editCourseDateStart);
        editEnd=findViewById(R.id.editCourseDateEnd);
        editStatus_=findViewById(R.id.editStatus);
        editName=findViewById(R.id.editInstructorName);
        editPhone=findViewById(R.id.editInstructorPhone);
        editEmail=findViewById(R.id.editInstructorEmail);
        // Using getDoubleExtra will require a default value
        courseId = getIntent().getIntExtra("courseID", -1);
        termId = getIntent().getIntExtra("termID", -1);
        courseTitle=getIntent().getStringExtra("courseTitle");
        courseDateStart=getIntent().getStringExtra("courseDateStart");
        courseDateEnd=getIntent().getStringExtra("courseDateEnd");
        status=getIntent().getStringExtra("status");
        instructorName=getIntent().getStringExtra("instructorName");
        instructorPhone=getIntent().getStringExtra("instructorPhone");
        instructorEmail=getIntent().getStringExtra("instructorEmail");

        editTerm.setText(Integer.toString(termId));
        editTitle.setText(courseTitle);
        editStart.setText(courseDateStart);
        editEnd.setText(courseDateEnd);
        editStatus_.setText(status);
        editName.setText(instructorName);
        editPhone.setText(instructorPhone);
        editEmail.setText(instructorEmail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.course_assessments_recycler);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        Repository repo = new Repository(getApplication());
        repository = repo;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> matchingAssessments = new ArrayList<>();
        for (Assessment a : repo.getAllAssessments()){
            if(a.getCourseID() == courseId) matchingAssessments.add(a);
        }
        adapter.setAssessments(matchingAssessments);
    }

    public void saveBtn(View view) {
        // TODO: Ensure that you convert the string to an int for the id
        Course course;
        if (courseId == -1){
            int newID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
//            course = new Course(courseId,);
//            repository.insert(course);
        } else {
//            course = new Course(courseId);
//            repository.update(course);
        }
    }
}
