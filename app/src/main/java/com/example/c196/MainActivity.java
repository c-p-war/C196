package com.example.c196;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Term;

public class MainActivity extends AppCompatActivity {
// TODO: Create and populate mock data on load in
    // TODO: Setup terms fields
// TODO: Display mock data in terms


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repo = new Repository(getApplication());
        repo.insert(new Term(1,"12-1-2022", "06-1-2023", "Term 1"));
        repo.insert(new Term(2,"06-1-2023", "12-1-2023", "Term 2"));

        repo.insert(new Course(1, 1, "Course A", "12-1-2022", "06-1-2023", "Not Started", "John", "123-456-1234", "john@gmail.com"));
        repo.insert(new Course(2, 1, "Course B", "12-1-2022", "06-1-2023", "Not Started", "Jane", "987-654-3210", "jane@gmail.com"));
//        repo.insert(new Course(3, 2, "Course C", "12-1-2022", "06-1-2023", "Not Started", "John", "123-456-1234", "john@gmail.com"));
//        repo.insert(new Course(4, 2, "Course D", "12-1-2022", "06-1-2023", "Not Started", "Jane", "987-654-3210", "jane@gmail.com"));

        repo.insert(new Assessment(1, "11-11-2022", "11-12-2022"));
    }

    public void toTerms(View view) {
        Intent intent = new Intent(MainActivity.this, Terms.class);
        startActivity(intent);
    }

    public void toCourses(View view) {
        Intent intent = new Intent(MainActivity.this, Courses.class);
        startActivity(intent);
    }

    public void toAssessments(View view) {
        Intent intent = new Intent(MainActivity.this, Assessments.class);
        startActivity(intent);
    }
}
