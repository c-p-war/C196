package com.example.c196;

import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Terms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
