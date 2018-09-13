package my.edu.utem.roomwordssample;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
//DAO ialah utk mapping function ke query
public interface WordDao {

    //setiap satu kena mapkan
    @Insert
    void insert(Word word);

    @Query("DELETE from word_table")
    void deleteAll();

    //Utk keluarkan semua data
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();    //utk data yg dinamik setiap kali refresh guna LiveData

    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    @Delete
    void deleteWord(Word word);

}
