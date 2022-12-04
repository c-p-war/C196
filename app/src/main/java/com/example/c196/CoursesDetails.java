package com.example.c196;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CoursesDetails extends AppCompatActivity {
    EditText editTerm;
    EditText editId;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
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
    Course currentCourse;
    ArrayList<Note> matchingNotes;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_details);
        editTerm = findViewById(R.id.editTermId);
        editId = findViewById(R.id.editCourseId);
        editTitle = findViewById(R.id.editCourseTitle);
        editStart = findViewById(R.id.editCourseDateStart);
        editEnd = findViewById(R.id.editCourseDateEnd);
        editStatus_ = findViewById(R.id.editStatus);
        editName = findViewById(R.id.editInstructorName);
        editPhone = findViewById(R.id.editInstructorPhone);
        editEmail = findViewById(R.id.editInstructorEmail);

        String myFormat = "MM-dd-yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        // Start Date Pickers
        // EditText -> Calendar
        editStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We want it the date picker to show up as the date on our text
                Date date;
                String info = editStart.getText().toString();
                if (info.equals("")) info = "01-01-2023";
                try {
                    myCalendarStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CoursesDetails.this, startDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        // Calendar -> EditText
        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        editEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We want it the date picker to show up as the date on our text
                Date date;
                String info = editEnd.getText().toString();
                if (info.equals("")) info = "01-01-2023";
                try {
                    myCalendarEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CoursesDetails.this, endDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };
        // End Date Picker

        termId = getIntent().getIntExtra("termID", -1);
        courseId = getIntent().getIntExtra("courseID", -1);
        courseTitle = getIntent().getStringExtra("courseTitle");
        courseDateStart = getIntent().getStringExtra("courseDateStart");
        courseDateEnd = getIntent().getStringExtra("courseDateEnd");
        status = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("instructorName");
        instructorPhone = getIntent().getStringExtra("instructorPhone");
        instructorEmail = getIntent().getStringExtra("instructorEmail");

        editTerm.setText(Integer.toString(termId));
        editId.setText(Integer.toString(courseId));
        editTitle.setText(courseTitle);
        editStart.setText(courseDateStart);
        editEnd.setText(courseDateEnd);
        editStatus_.setText(status);
        editName.setText(instructorName);
        editPhone.setText(instructorPhone);
        editEmail.setText(instructorEmail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Assessments list
        RecyclerView recyclerView = findViewById(R.id.course_assessments_recycler);
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        Repository repo = new Repository(getApplication());
        repository = repo;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Assessment> matchingAssessments = new ArrayList<>();
        for (Assessment a : repo.getAllAssessments()) {
            if (a.getCourseID() == courseId) matchingAssessments.add(a);
        }
        adapter.setAssessments(matchingAssessments);
        // Notes list
        RecyclerView recyclerViewNotes = findViewById(R.id.course_notes_recycler);
        final NoteAdapter noteAdapter = new NoteAdapter(this);
        Repository repoNote = new Repository(getApplication());
        recyclerViewNotes.setAdapter(noteAdapter);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        matchingNotes = new ArrayList<>();
        for (Note n : repoNote.getAllNotes()) {
            if (n.getCourseID() == courseId) matchingNotes.add(n);
        }
        noteAdapter.setNotes(matchingNotes);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_alert, menu);
        return true;
    }
    private void updateLabelStart() {
        editStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        editEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.notifyEnd:
                String endDateFromScreen = editEnd.getText().toString();
                Date myEndDate = null;
                try {
                    myEndDate = sdf.parse(endDateFromScreen);
                } catch (ParseException e){
                    e.printStackTrace();
                }
                Long endDateTrigger = myEndDate.getTime();
                Intent endIntent = new Intent(CoursesDetails.this, MyReceiver.class);
                String endDateNotification = courseTitle + " ends today.";
                endIntent.putExtra("key", endDateNotification);
                PendingIntent endSender = PendingIntent.getBroadcast(CoursesDetails.this, MainActivity.numAlert++, endIntent, 0);
                AlarmManager endAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                endAlarmManager.set(AlarmManager.RTC_WAKEUP, endDateTrigger, endSender);
                return true;
            case R.id.notifyStart:
                // Label -> Date Object
                String startDateFromScreen = editStart.getText().toString();
                Date myStartDate = null;
                try {
                    myStartDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // We want the notification to go off in the future
                Long startDateTrigger = myStartDate.getTime(); // returns date object in mills
                // Broadcast receiver must be in AndroidManifest
                Intent intent = new Intent(CoursesDetails.this, MyReceiver.class);
                String startDateNotification = courseTitle + " starts today.";
                intent.putExtra("key", startDateNotification);
                // The second argument must be unique for the whole app, aka course and assessment cannot be the same
                PendingIntent sender = PendingIntent.getBroadcast(CoursesDetails.this, MainActivity.numAlert++, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, startDateTrigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveBtn(View view) {
        Course course;
        if (courseId == -1) {
            int newID = repository.getAllCourses().get(repository.getAllCourses().size() - 1).getCourseID() + 1;
            course = new Course(newID, Integer.parseInt(editTerm.getText().toString()), editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus_.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());
            repository.insert(course);
            Intent intent = new Intent(CoursesDetails.this, Courses.class);
            startActivity(intent);
        } else {
            course = new Course(Integer.parseInt(editId.getText().toString()), Integer.parseInt(editTerm.getText().toString()), editTitle.getText().toString(), editStart.getText().toString(), editEnd.getText().toString(), editStatus_.getText().toString(), editName.getText().toString(), editPhone.getText().toString(), editEmail.getText().toString());
            repository.update(course);
            Intent intent = new Intent(CoursesDetails.this, Courses.class);
            startActivity(intent);
        }
    }

    public void deleteBtn(View view) {
        if (courseId != -1){
            for (Course course : repository.getAllCourses()) {
                if (course.getCourseID() == courseId) currentCourse = course;
            }
            repository.delete(currentCourse);
            Intent intent = new Intent(CoursesDetails.this, Courses.class);
            startActivity(intent);
        }
    }

    public void addNoteBtn(View view) {
        if (matchingNotes.size() == 0){
        Intent intent = new Intent(CoursesDetails.this, NotesAdd.class);
        intent.putExtra("courseID", courseId);
        startActivity(intent);
        }
    }
}
