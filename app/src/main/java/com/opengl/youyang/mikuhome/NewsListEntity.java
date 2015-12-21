package com.opengl.youyang.mikuhome;

import java.util.BitSet;

/**
 * Created by youyang on 15/12/19.
 */
public class NewsListEntity {
    private BitSet mIconUrl;
    private long mNewsID;
    private String mPublishDate;

    public NewsListEntity(BitSet iconUrl, long newsID, String publishDate, String title) {
        mIconUrl = iconUrl;
        mNewsID = newsID;
        mPublishDate = publishDate;
        mTitle = title;
    }

    public void setIconUrl(BitSet iconUrl) {
        mIconUrl = iconUrl;
    }

    public void setNewsID(long newsID) {
        mNewsID = newsID;
    }

    public void setPublishDate(String publishDate) {
        mPublishDate = publishDate;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    private String mTitle;

    public BitSet getIconUrl() {
        return mIconUrl;
    }

    public long getNewsID() {
        return mNewsID;
    }

    public String getPublishDate() {
        return mPublishDate;
    }

    public String getTitle() {
        return mTitle;
    }
}
