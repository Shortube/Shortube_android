package com.unithon.com.shortube.video;

import android.support.annotation.NonNull;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker;

/**
 * Created by macbook on 2018. 7. 28..
 */

public class CustomVideoTracker extends YouTubePlayerTracker {

    private StateTrackListener stateTracker;

    interface StateTrackListener {
        void onPause();
        void onEnded();
    }

    public CustomVideoTracker(StateTrackListener stateTracker) {
        this.stateTracker = stateTracker;
    }

    @Override
    public void onStateChange(@NonNull PlayerConstants.PlayerState state) {
        super.onStateChange(state);
        if(state == PlayerConstants.PlayerState.ENDED){
            stateTracker.onEnded();
        }
    }
}
