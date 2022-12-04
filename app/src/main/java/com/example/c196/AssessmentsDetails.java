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
import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AssessmentsDetails extends AppCompatActivity {
    EditText editCourseId;
    EditText editAId;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;


    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();


    EditText editType;
    String aTitle;
    String aStart;
    String aEnd;
    String aType;
    int assessmentId;
    int courseId;
    Repository repository;
    Assessment currentAssessment;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesments_details);
        Repository repo = new Repository(getApplication());
        repository = repo;
        editAId = findViewById(R.id.editAId);
        editCourseId = findViewById(R.id.editACourseId);
        editTitle = findViewById(R.id.editATitle);
        editStart = findViewById(R.id.editAStart);
        editEnd = findViewById(R.id.editAEnd);
        editType = findViewById(R.id.editAType);
        String myFormat = "MM-dd-yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        // Start Date Picker
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
                new DatePickerDialog(AssessmentsDetails.this, startDate, myCalendarStart.get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH), myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();

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
        // End Date Picker
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
                new DatePickerDialog(AssessmentsDetails.this, endDate, myCalendarEnd.get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();

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


        courseId = getIntent().getIntExtra("courseID", -1);
        assessmentId = getIntent().getIntExtra("assessmentID", -1);
        aTitle = getIntent().getStringExtra("assessmentTitle");
        aStart = getIntent().getStringExtra("assessmentDateStart");
        aEnd = getIntent().getStringExtra("assessmentDateEnd");
        aType = getIntent().getStringExtra("assessmentType");
        editAId.setText(Integer.toString(assessmentId));
        editCourseId.setText(Integer.toString(courseId));
        editTitle.setText(aTitle);
        editStart.setText(aStart);
        editEnd.setText(aEnd);
        editType.setText(aType);
    }

    private void updateLabelStart() {
        editStart.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void updateLabelEnd() {
        editEnd.setText(sdf.format(myCalendarEnd.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_alert, menu);
        return true;
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
                Intent endIntent = new Intent(AssessmentsDetails.this, MyReceiver.class);
                String endDateNotification = "Assessment " + aTitle + " ends today.";
                endIntent.putExtra("key", endDateNotification);
                PendingIntent endSender = PendingIntent.getBroadcast(AssessmentsDetails.this, MainActivity.numAlert++, endIntent, 0);
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
                Intent intent = new Intent(AssessmentsDetails.this, MyReceiver.class);
                String startDateNotification = "Assessment " + aTitle + " starts today.";
                intent.putExtra("key", startDateNotification);
                // The second argument must be unique for the whole app, aka course and assessment cannot be the same
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentsDetails.this, MainActivity.numAlert++, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, startDateTrigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveBtn(View view) {
        Assessment assessment;
        if (assessmentId == -1) {
            int newID = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(
                    newID,
                    editTitle.getText().toString(),
                    editStart.getText().toString(),
                    editEnd.getText().toString(),
                    editType.getText().toString(),
                    Integer.parseInt(editCourseId.getText().toString())
            );
            repository.insert(assessment);
            Intent intent = new Intent(AssessmentsDetails.this, Assessments.class);
            startActivity(intent);
        } else {
            assessment = new Assessment(
                    Integer.parseInt(editAId.getText().toString()),
                    editTitle.getText().toString(),
                    editStart.getText().toString(),
                    editEnd.getText().toString(),
                    editType.getText().toString(),
                    Integer.parseInt(editCourseId.getText().toString()));
            repository.update(assessment);
            Intent intent = new Intent(AssessmentsDetails.this, Assessments.class);
            startActivity(intent);
        }
    }

    public void deleteBtn(View view) {
        if (assessmentId != -1) {
            for (Assessment assessment : repository.getAllAssessments()) {
                if (assessment.getAssessmentID() == assessmentId) currentAssessment = assessment;
            }
            repository.delete(currentAssessment);
            Intent intent = new Intent(AssessmentsDetails.this, Assessments.class);
            startActivity(intent);
        }
    }
}
