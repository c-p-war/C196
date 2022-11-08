package com.example.c196.DAO;

import androidx.room.*;
import com.example.c196.Entity.Assessment;

import java.util.List;

@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);
    @Update
    void update(Assessment assessment);
    @Delete
    void delete(Assessment assessment);
    @Query("SELECT * FROM assessments")
    List<Assessment> getAllAssessments();


}
