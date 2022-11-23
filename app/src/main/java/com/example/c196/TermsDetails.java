package com.example.c196;

import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TermsDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    String termTitle;
    String termStart;
    String termEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_details);
        editTitle=findViewById(R.id.editTermTitle);
        editStart=findViewById(R.id.editTermDateStart);
        editEnd=findViewById(R.id.editTermDateEnd);
        termTitle=getIntent().getStringExtra("termTitle");
        termStart=getIntent().getStringExtra("termDateStart");
        termEnd=getIntent().getStringExtra("termDateEnd");
        editTitle.setText(termTitle);
        editStart.setText(termStart);
        editEnd.setText(termEnd);
    }
}
