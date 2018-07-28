package com.unithon.com.shortube.video.comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbook on 2018. 7. 28..
 */

public class VideoCommentTestDataBuilder {
    public static int TEST_COMMENT_COUNT = 10;

    public static List<VideoComment> createTestDataList() {
        List<VideoComment> testCommentList = new ArrayList<>();
        for (int i = 0; i < TEST_COMMENT_COUNT; i++) {
            VideoComment videoComment = new VideoComment(
                    "와썹맨" + i,
                    "2:22",
                    "2:22 내가...? (짜장면 싫다고) 안 했는데..?1:11",
                    213123255,
                    1234,
                    15
            );
            testCommentList.add(videoComment);
        }
        return testCommentList;
    }

}
