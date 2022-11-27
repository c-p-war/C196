package com.example.c196;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AssessmentsDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    EditText editType;
    String aTitle;
    String aStart;
    String aEnd;
    String aType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesments_details);
        editTitle = findViewById(R.id.editATitle);
        editStart = findViewById(R.id.editAStart);
        editEnd = findViewById(R.id.editAEnd);
        editType = findViewById(R.id.editAType);
        aTitle = getIntent().getStringExtra("assessmentTitle");
        aStart = getIntent().getStringExtra("assessmentDateStart");
        aEnd = getIntent().getStringExtra("assessmentDateEnd");
        aType = getIntent().getStringExtra("assessmentType");
        editTitle.setText(aTitle);
        editStart.setText(aStart);
        editEnd.setText(aEnd);
        editType.setText(aType);
    }

    public void saveBtn(View view) {
    }
}
