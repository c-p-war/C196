package com.example.c196;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Course;

import static java.sql.Types.NULL;

public class CoursesAdd extends AppCompatActivity {
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
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_add);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        repository = new Repository(getApplication());
        editTerm = findViewById(R.id.editTermId);
        editId = findViewById(R.id.editCourseId);
        editTitle = findViewById(R.id.edit_course_title);
        editStart = findViewById(R.id.edit_course_start_date);
        editEnd = findViewById(R.id.edit_end_date);
        editStatus_ = findViewById(R.id.edit_status);
        editName = findViewById(R.id.edit_inst_name);
        editPhone = findViewById(R.id.edit_inst_phone);
        editEmail = findViewById(R.id.edit_inst_email);

        courseId = getIntent().getIntExtra("courseID", -1);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveBtn(View view) {
        Course course;
        if (courseId == -1) {
            int newId = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            course = new Course(newId, Integer.parseInt(editTerm.getText().toString()), editTitle.getText().toString(),editStart.getText().toString(), editEnd.getText().toString(), editStatus_.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());
            repository.insert(course);
        } else {
            System.out.println("Unable to add course");
        }
    }
}
