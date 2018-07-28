package com.unithon.com.shortube.video.comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unithon.com.shortube.R;
import com.unithon.com.shortube.video.VideoSectionJumper;

import java.util.List;

/**
 * Created by macbook on 2018. 7. 28..
 */

public class VideoCommentListAdapter extends RecyclerView.Adapter<VideoCommentViewHolder> {
    private static final String TAG = VideoSectionJumper.class.getSimpleName();
    private List<VideoComment> videoCommentList;
    private Context mContext;

    private VideoSectionJumper videoSectionJumper;

    public VideoCommentListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VideoCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.viewholder_video_comment, parent, false);
        return new VideoCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoCommentViewHolder holder, int position) {
        holder.setData(videoCommentList.get(position), videoSectionJumper);
    }

    @Override
    public int getItemCount() {
        return videoCommentList.size();
    }

    public void setData(List<VideoComment> videoCommentList){
        this.videoCommentList = videoCommentList;
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    public void setVideoSectionJumper(VideoSectionJumper videoSectionJumper){
        this.videoSectionJumper = videoSectionJumper;
    }
}
