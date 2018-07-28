package com.unithon.com.shortube;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class VideoRepository {

    private VideoDao mVideoDao;
    private LiveData<List<Video>> mAllVideos;
    VideoRepository(Application application) {
        VideoRoomDatabase db = VideoRoomDatabase.getDatabase(application);
        mVideoDao = db.videoDao();
        mAllVideos = mVideoDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Video>> getAllVideos() {
        return mAllVideos;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (Video video) {
        new insertAsyncTask(mVideoDao).execute(video);
    }

    private static class insertAsyncTask extends AsyncTask<Video, Void, Void> {

        private VideoDao mAsyncTaskDao;

        insertAsyncTask(VideoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Video... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
