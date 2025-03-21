package com.example.c196.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.c196.DAO.AssessmentsDAO;
import com.example.c196.DAO.CoursesDAO;
import com.example.c196.DAO.NotesDAO;
import com.example.c196.DAO.TermsDAO;
import com.example.c196.Entity.Assessment;
import com.example.c196.Entity.Course;
import com.example.c196.Entity.Note;
import com.example.c196.Entity.Term;

@Database(entities = {Assessment.class, Course.class, Term.class, Note.class}, version = 27, exportSchema = false)
public abstract class C196DatabaseBuilder extends RoomDatabase {
    public abstract AssessmentsDAO assessmentsDAO();

    public abstract CoursesDAO coursesDAO();

    public abstract TermsDAO termsDAO();

    public abstract NotesDAO notesDAO();

    private static volatile C196DatabaseBuilder INSTANCE;

    // Context is a basic part of android, it tells you where you are in the flow of the program
    // allowing main thread queries would be after .build()
    static C196DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (C196DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), C196DatabaseBuilder.class, "c196Database")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
