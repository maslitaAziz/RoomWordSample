package my.edu.utem.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

//ini class repository dan ada connection kpd DAO
public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();

    }

    //buat implementation untuk DAO, ni read all
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    //Buat implementation utk DAO, create atau insert data
    public void insert(Word word) {
       //pindahkan line ni ke do inbackground
        //mWordDao.insert(word);
        //ganti dengan line ni panggil class baru tu
        new insertAsyncTask(mWordDao).execute(word);
    }

    //buat implementation utk DAO, delete
    public void deleteAll() {

        //mWordDao.deleteAll();
        //tukar line atas dgn yg bawah ni
        new deleteAsyncTask(mWordDao).execute();
    }

    //buat dalam thread berbeza bila berkaitan database, URL connection , file tracking kena guna async task
    //livedata dah buat thread berbeza.

    public static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;
        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    public static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao mAsyncTaskDao;

        public deleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
