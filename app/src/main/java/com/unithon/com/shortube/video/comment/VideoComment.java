package com.unithon.com.shortube.video.comment;

/**
 * Created by macbook on 2018. 7. 28..
 */

public class VideoComment {
    private long commentId;
    private String nickName;
    private String jumpInfo;
    private String content;
    private long commentDate;
    private long like;

    public VideoComment(String nickName, String jumpInfo, String content, long commentDate, long commentId, long like) {
        this.nickName = nickName;
        this.jumpInfo = jumpInfo;
        this.content = content;
        this.commentDate = commentDate;
        this.commentId = commentId;
        this.like = like;
    }

    public String getJumpInfo() {
        return jumpInfo;
    }

    public long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public long getLike() {
        return like;
    }

    public String getNickName() {
        return nickName;
    }

    public long getCommentDate() {
        return commentDate;
    }
}
