package com.unithon.com.shortube.video.highlight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbook on 2018. 7. 29..
 */

public class HighlightTestDataBuilder {
    private static final int TEST_HIGHLIGHT_COUNT = 5;

    public static List<HighlightData> createTestDataList() {

        List<HighlightData> highlightDataList = new ArrayList<>();

        for (int idx = 0; idx < TEST_HIGHLIGHT_COUNT; idx++) {
            HighlightData highlightData = new HighlightData(
                    "aaa",
                    "aaa",
                    "aaa",
                    1,
                    2
            );
            highlightDataList.add(highlightData);
        }
        return highlightDataList;
    }
}
