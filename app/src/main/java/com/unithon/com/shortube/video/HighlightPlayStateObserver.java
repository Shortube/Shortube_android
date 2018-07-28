package com.unithon.com.shortube.video;

import android.util.Log;

import com.unithon.com.shortube.video.inter.HighlightPlayStateListener;

/**
 * Created by macbook on 2018. 7. 29..
 */

public class HighlightPlayStateObserver {
    private long start;
    private long end;

    private HighlightPlayStateListener highlightPlayStateListener;

    public HighlightPlayStateObserver(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isTimeToFinish(long currentTime){
        if(currentTime == end){
            return true;
        } else {
            return false;
        }
    }
}
