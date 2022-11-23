package com.example.c196.DAO;
import com.example.c196.Entity.Note;
import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note entityNote);

    @Update
    void update(Note entityNote);

    @Delete
    void delete(Note entityNote);

    @Query("SELECT * FROM notes ORDER BY noteID ASC")
    List<Note> getAllNotes();

}
