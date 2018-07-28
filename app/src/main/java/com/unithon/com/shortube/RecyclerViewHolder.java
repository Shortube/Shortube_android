package com.unithon.com.shortube;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView mThumbnail;
    public ImageView mProfile;
    public TextView mtitle;
    public TextView mdescribe;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mThumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
        mProfile = (ImageView)itemView.findViewById(R.id.profile);
        mtitle = (TextView)itemView.findViewById(R.id.title);
        mdescribe = (TextView)itemView.findViewById(R.id.subscribe);
        mProfile.setClipToOutline(true);
    }
}
