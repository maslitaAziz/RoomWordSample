package my.edu.utem.roomwordssample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//This class will be mapped to table Word melalui entity
@Entity(tableName = "word_table")
public class Word {

    //Utk auto generate id gunakan
    //@PrimaryKey(autoGenarate = true)
    //private int id;


    @PrimaryKey //Jadikan primary key guna @PrimaryKey
    @NonNull   //Jadikan non null
    @ColumnInfo(name="word") //dalam table nanti panggil column ni word
    private String mWord;

    public Word( @NonNull String mWord) {
        this.mWord = mWord;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord( @NonNull String mWord) {
        this.mWord = mWord;
    }
}
