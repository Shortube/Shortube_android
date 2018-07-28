package com.example.android.roomwordssample;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class LikeViewModel extends AndroidViewModel {

    private LikeRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<LikeVideo>> mAllLikes;

    public LikeViewModel (Application application) {
        super(application);
        mRepository = new LikeRepository(application);
        mAllLikes = mRepository.getAllLikes();
    }

    LiveData<List<LikeVideo>> getAllLikes() {return mAllLikes;}

    public void likeInsert(LikeVideo likeVideo) {mRepository.likeInsert(likeVideo);}
}