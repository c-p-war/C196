package com.example.c196;

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
import com.example.c196.Entity.Term;

import java.util.List;

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
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    boolean isValidTerm(int termId){
        boolean valid = false;
        List<Term> allTerms = repository.getAllTerms();
        for (Term term : allTerms) {
            if (term.getTermID() == termId ){
                valid = true;
            }
        }
        return valid;
    }

    boolean isDigitsOnly(String str) {
        return !str.isEmpty() && TextUtils.isDigitsOnly(str);
    }

    int setIntFromText (EditText editText){
        return Integer.parseInt(editText.getText().toString());
    }

    String getString (EditText editText) { return editText.getText().toString();
    }

    public void saveBtn(View view) {
        int termId = 0;
        Course course;
        String termIdString = getString(editTerm);

        if (isDigitsOnly(termIdString)){
            termId = setIntFromText(editTerm);
        }

        if (courseId == -1 && isValidTerm(termId)) {
            int newId = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            course = new Course(newId, Integer.parseInt(editTerm.getText().toString()), editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus_.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());
            repository.insert(course);
            Intent intent = new Intent(CoursesAdd.this, Courses.class);
            startActivity(intent);
        } else if (termIdString.isEmpty()) {
            Toast.makeText(CoursesAdd.this, "Term ID is required.", Toast.LENGTH_LONG).show();
        } else if (!isValidTerm(termId)){
            Toast.makeText(CoursesAdd.this, "Term ID not found", Toast.LENGTH_LONG).show();
        }
    }
}
