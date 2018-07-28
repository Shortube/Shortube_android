package com.unithon.com.shortube.video.highlight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.unithon.com.shortube.R;
import com.unithon.com.shortube.video.inter.VideoHighlightTrigger;

/**
 * Created by macbook on 2018. 7. 29..
 */

public class HighlightListViewHolder extends RecyclerView.ViewHolder {
    private ImageView iv_highlight_thumb;

    public HighlightListViewHolder(View itemView) {
        super(itemView);
        iv_highlight_thumb = itemView.findViewById(R.id.iv_highlight_thumb);
    }

    public void setData(final HighlightData highlightData, Context context,
                        final VideoHighlightTrigger videoHighlightTrigger){
        Glide.with(context)
                .load("https://img.youtube.com/vi/k_4p7xyE_ok/0.jpg")
                .into(iv_highlight_thumb);
        iv_highlight_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoHighlightTrigger.trigger(
                        highlightData.getSectionStart(),
                        highlightData.getSectionEnd()
                );
            }
        });
    }
}
