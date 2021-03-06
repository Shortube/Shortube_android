package com.unithon.com.shortube;

import android.graphics.Bitmap;
import android.net.Uri;

public class Items {
    Uri Thumbnails;
    Uri Profile;
    String title;
    String describe;
    int num;

    public Items(Uri Thumbnails, Uri Profile, String title, String describe, int num){
        this.Thumbnails = Thumbnails;
        this.Profile = Profile;
        this.title = title;
        this.describe = describe;
        this.num = num;
    }

    public Uri getThumbnails() {
        return Thumbnails;
    }

    public Uri getProfile() {
        return Profile;
    }

    public String getTitle() {
        return title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setThumbnails(Uri thumbnails) {
        Thumbnails = thumbnails;
    }

    public void setProfile(Uri profile) {
        Profile = profile;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
