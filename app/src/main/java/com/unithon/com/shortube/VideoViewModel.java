package com.unithon.com.shortube;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {

    private VideoRepository mRepository;
    private LiveData<List<Video>> mAllVideos;

    public VideoViewModel (Application application) {
        super(application);
        mRepository = new VideoRepository(application);
        mAllVideos = mRepository.getAllVideos();
    }

    LiveData<List<Video>> getAllVideos() { return mAllVideos; }

    public void insert(Video video) { mRepository.insert(video); }
}