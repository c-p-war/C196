package com.example.c196.Database;

import android.app.Application;
import com.example.c196.DAO.AssessmentsDAO;
import com.example.c196.DAO.CoursesDAO;
import com.example.c196.DAO.TermsDAO;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Term;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Holds methods to call for the DAO
public class Repository {
    private AssessmentsDAO mAssessmentsDAO;
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private List<Assessment> mAllAssesments;
    private List<Course> mAllCourses;
    private List<Term> mListAllTerms;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        C196DatabaseBuilder db = C196DatabaseBuilder.getDatabase(application);
        mAssessmentsDAO = db.assessmentsDAO();
        mCoursesDAO = db.coursesDAO();
        mTermsDAO = db.termsDAO();
    }

    // TODO: Create one of these for each CRUD action
    public void insert(Assessment assessment) {
        databaseExecutor.execute(() -> {
            mAssessmentsDAO.insert(assessment);
        });
        // To see results in real time, set delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
