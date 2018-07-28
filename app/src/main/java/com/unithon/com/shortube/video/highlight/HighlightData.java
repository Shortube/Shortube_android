package com.unithon.com.shortube.video.highlight;

/**
 * Created by macbook on 2018. 7. 29..
 */

public class HighlightData {
    String urlKey;
    String thumbnail;
    String videoName;
    long sectionStart;
    long sectionEnd;


    public String getUrlKey() {
        return urlKey;
    }

    public void setUrlKey(String urlKey) {
        this.urlKey = urlKey;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public long getSectionStart() {
        return sectionStart;
    }

    public void setSectionStart(long sectionStart) {
        this.sectionStart = sectionStart;
    }

    public long getSectionEnd() {
        return sectionEnd;
    }

    public void setSectionEnd(long sectionEnd) {
        this.sectionEnd = sectionEnd;
    }

    public HighlightData(String urlKey, String thumbnail, String videoName, long sectionStart, long sectionEnd) {

        this.urlKey = urlKey;
        this.thumbnail = thumbnail;
        this.videoName = videoName;
        this.sectionStart = sectionStart;
        this.sectionEnd = sectionEnd;
    }
}
