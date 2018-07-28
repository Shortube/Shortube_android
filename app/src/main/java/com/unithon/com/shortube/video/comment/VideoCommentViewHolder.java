package com.unithon.com.shortube.video.comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unithon.com.shortube.R;
import com.unithon.com.shortube.SectionData;
import com.unithon.com.shortube.video.inter.VideoSectionJumper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by macbook on 2018. 7. 28..
 */

public class VideoCommentViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = VideoCommentViewHolder.class.getSimpleName();
    private static final int ERROR_INDEX = -1;

    CircleImageView userThumb;
    TextView nickName;
    TextView commentDate;
    TextView content;

    VideoSectionJumper videoSectionJumper;

    public VideoCommentViewHolder(View itemView) {
        super(itemView);
        userThumb = itemView.findViewById(R.id.video_comment_profile_thumb);
        nickName = itemView.findViewById(R.id.video_comment_user_nick);
        commentDate = itemView.findViewById(R.id.video_comment_date);
        content = itemView.findViewById(R.id.video_comment_content);
    }

    public void setData(Context context, VideoComment videoComment, VideoSectionJumper videoSectionJumper) {
        this.videoSectionJumper = videoSectionJumper;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(videoComment.getCommentDate());

        Glide.with(context).load("https://yt3.ggpht.com/-J2_hBTvzaN4/AAAAAAAAAAI/AAAAAAAAAAA/WyOa8gCY_H0/s288-mo-c-c0xffffffff-rj-k-no/photo.jpg").into(userThumb);

        nickName.setText(videoComment.getNickName());
        content.setText(createClickableSpanStrForJumpDestination(videoComment.getContent()));
        content.setMovementMethod(LinkMovementMethod.getInstance());
        commentDate.setText(dateFormat.format(date));
    }


    public SpannableString createClickableSpanStrForJumpDestination(String text) {
        Log.d(TAG, "createSpan called, " + text);
        List<SectionData> sectionDataList = findJumpSection(text);
        SpannableString spannableString = new SpannableString(text);

        for (final SectionData sectionData : sectionDataList) {
            if(sectionData.getStartIndex() == ERROR_INDEX || sectionData.getEndIndex() == ERROR_INDEX) {
                continue;
            }
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    // TODO: 2018. 7. 28. 해당 범위에서 long 값 추출하기
                    Log.d(TAG, "clicked");
                    videoSectionJumper.jump(sectionToLong(sectionData.getSection()));
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };
            spannableString.setSpan(clickableSpan,
                    sectionData.getStartIndex(),
                    sectionData.getEndIndex(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }


    public List<SectionData> findJumpSection(String input) {
        Log.d(TAG, "findJumpSection called");
        Pattern pattern = Pattern.compile("([0-9]{1,2}:)*([0-9]{1,2}:[0-9]{1,2})");
        Matcher matcher = pattern.matcher(input);

        List<SectionData> sectionDataList = new ArrayList<>();

        while(matcher.find()){

            SectionData sectionData = new SectionData(matcher.start(), matcher.end(), matcher.group());
            Log.d(TAG, "index : " + matcher.start() + " : " + matcher.end() + " , " +matcher.group());
            sectionDataList.add(sectionData);
        }

        return sectionDataList;
    }

    public long sectionToLong(String section){
        return 110;
    }
}
