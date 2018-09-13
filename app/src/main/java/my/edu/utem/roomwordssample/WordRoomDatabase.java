package my.edu.utem.roomwordssample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

//nak beritahu berapa banyak table/entity yg nak guna
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    //buat reference ke DAO
    //dan reference ke database
    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    //yg ni E Wan kata coding sama jek
    //ada 3 design pattern 1. singleton 2.factory (.create ) /builder (creational pattern) 3.observer
    static WordRoomDatabase getDatabase(final Context context) {
        //kalau x de create baru
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;  //kalau ada return kan
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDatabaseAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {
        String[] words = {"dolphin", "crocodile", "cobra"};
        private final WordDao mDao;

        PopulateDatabaseAsync(WordRoomDatabase database) {
            mDao = database.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            //kalau x de word, insert kan
            if (mDao.getAnyWord().length == 0) {
              //  mDao.deleteAll(); //delete semua dlm database
                for (int i = 0; i < words.length; i++) {
                    mDao.insert(new Word(words[i]));  //initialise database
                }
            }

         /*   for (int i = 0; i <= words.length - 1; i++) {
                Word word = new Word(words[i]);
                mDao.insert(word);
            }*/
            return null;
        }
    }

}
