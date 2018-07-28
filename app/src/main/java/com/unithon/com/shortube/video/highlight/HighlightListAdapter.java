package com.unithon.com.shortube.video.highlight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unithon.com.shortube.R;
import com.unithon.com.shortube.video.inter.VideoHighlightTrigger;

import java.util.List;

/**
 * Created by macbook on 2018. 7. 29..
 */

public class HighlightListAdapter extends RecyclerView.Adapter<HighlightListViewHolder> {

    private List<HighlightData> highlightDataList;
    private Context context;

    private VideoHighlightTrigger videoHighlightTrigger;

    public HighlightListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HighlightListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.viewholder_video_highlight, parent, false);
        return new HighlightListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightListViewHolder holder, int position) {
        holder.setData(highlightDataList.get(position), context, videoHighlightTrigger);
    }

    @Override
    public int getItemCount() {
        return highlightDataList.size();
    }

    public void setHighlightDataList(List<HighlightData> list){
        this.highlightDataList = list;
    }

    public void setVideoHighlightTrigger(VideoHighlightTrigger videoHighlightTrigger){
        this.videoHighlightTrigger = videoHighlightTrigger;
    }
}
