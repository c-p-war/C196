package com.example.c196.DAO;

import androidx.room.*;
import com.example.c196.Entity.Course;

import java.util.List;

@Dao
public interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);
    @Update
    void update(Course course);
    @Delete
    void delete(Course courses);
    @Query("SELECT * FROM courses ORDER BY courseID ASC")
    List<Course> getAllCourses();
}
