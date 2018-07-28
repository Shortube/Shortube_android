package com.example.android.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class LikeRepository {
    private WordDao mWordDao;
    private LiveData<List<LikeVideo>> mAllLikes;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    LikeRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllLikes = mWordDao.getLikeWords();
    }

    // Room executes all queries on a separate thread.
    LiveData<List<LikeVideo>> getAllLikes() {return mAllLikes;}


    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.

    public void likeInsert(LikeVideo likeVideo) {new LikeRepository.likeInsertAsyncTask(mWordDao).execute(likeVideo);}


    private static class likeInsertAsyncTask extends AsyncTask<LikeVideo, Void, Void> {

        private WordDao mAsyncTaskDao;

        likeInsertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final LikeVideo... params) {
            mAsyncTaskDao.likeInsert(params[0]);
            return null;
        }
    }
}
