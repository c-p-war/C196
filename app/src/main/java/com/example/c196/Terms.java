package com.example.c196;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Term;

import java.util.List;

public class Terms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.terms_recycler);
        Repository repo = new Repository(getApplication());
        List<Term> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }
    // Creates the menu
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_assessments_list, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            // Switch will have more items later
            case android.R.id.home:
                // this.finish() maintains the product state when navigating backwards
                this.finish();
                return true;
        }
                return super.onOptionsItemSelected(item);

        }

    public void toAddTerm(View view) {
        Intent intent = new Intent(Terms.this, TermsAdd.class);
        startActivity(intent);
    }
}
