package com.unithon.com.shortube;

/**
 * Created by macbook on 2018. 7. 28..
 */

public class SectionData {
    int startIndex;
    int endIndex;
    String section;

    public SectionData(int startIndex, int endIndex, String section) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.section = section;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
