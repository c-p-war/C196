package com.example.c196;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Term;

import java.util.ArrayList;
import java.util.List;

public class TermsDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    String termTitle;
    String termStart;
    String termEnd;
    int termId;
    Repository repository;
    Term currentTerm;
    int courseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);
        editTitle = findViewById(R.id.editTermTitle);
        editStart = findViewById(R.id.editTermDateStart);
        editEnd = findViewById(R.id.editTermDateEnd);
        termTitle = getIntent().getStringExtra("termTitle");
        termStart = getIntent().getStringExtra("termDateStart");
        termEnd = getIntent().getStringExtra("termDateEnd");
        termId = getIntent().getIntExtra("termID", -1);
        editTitle.setText(termTitle);
        editStart.setText(termStart);
        editEnd.setText(termEnd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.term_courses_recycler);
        final CourseAdapter adapter = new CourseAdapter(this);
        Repository repo = new Repository(getApplication());
        repository = repo;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> matchingCourses = new ArrayList<>();
        for (Course c : repo.getAllCourses()) {
            if (c.getTermID() == termId) matchingCourses.add(c);
        }
        adapter.setCourses(matchingCourses);
    }

    public void saveBtn(View view) {
        Term term;
        if (termId == -1) {
            int newID = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermID() + 1;
            term = new Term(newID, editStart.getText().toString(), editEnd.getText().toString(), editTitle.getText().toString());
            repository.insert(term);
            Intent intent = new Intent(TermsDetails.this, Terms.class);
            startActivity(intent);
        } else {
            term = new Term(termId, editStart.getText().toString(), editEnd.getText().toString(), editTitle.getText().toString());
            repository.update(term);
            Intent intent = new Intent(TermsDetails.this, Terms.class);
            startActivity(intent);
        }
    }

    public void deleteBtn(View view) {
        for (Term term : repository.getAllTerms()) {
            if (term.getTermID() == termId) currentTerm = term;
        }
        courseCount = 0;

        for (Course course : repository.getAllCourses()) {
            if (course.getTermID() == termId) ++courseCount;
        }

        if (courseCount == 0) {
            repository.delete(currentTerm);
            Intent intent = new Intent(TermsDetails.this, Terms.class);
            startActivity(intent);
        } else {
            Toast.makeText(TermsDetails.this, "Cannot delete a term that has assigned courses. Please delete the courses first", Toast.LENGTH_LONG).show();
        }
    }
}
