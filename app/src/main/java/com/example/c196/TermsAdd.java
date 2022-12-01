package com.example.c196;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Term;

public class TermsAdd extends AppCompatActivity {
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    int termId;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_add);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        repository = new Repository(getApplication());
        editTitle = findViewById(R.id.edit_term_title);
        editStart = findViewById(R.id.edit_term_start_date);
        editEnd = findViewById(R.id.edit_end_date);
        termId = getIntent().getIntExtra("termID", -1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void saveBtn(View view) {
        Term term;
        if (termId == -1) {
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermID() + 1;
            term = new Term(newId, editStart.getText().toString(), editEnd.getText().toString(), editTitle.getText().toString());
            repository.insert(term);
        } else {
            System.out.println("Unable to add term");
        }
    }
}
