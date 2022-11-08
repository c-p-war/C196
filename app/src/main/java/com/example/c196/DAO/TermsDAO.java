package com.example.c196.DAO;

import androidx.room.*;
import com.example.c196.Entity.Term;

import java.util.List;

@Dao
public interface TermsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);
    @Update
    void update(Term term);
    @Delete
    void delete(Term term);
    @Query("SELECT * FROM terms ORDER BY termID ASC")
    List<Term> getAllTerms();

}
