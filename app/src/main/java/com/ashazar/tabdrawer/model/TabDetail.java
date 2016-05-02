package com.ashazar.tabdrawer.model;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabDetail {
    public String title;
    public int drawableId;

    public TabDetail(String itemTitle) {
        title = itemTitle;
    }

    public TabDetail(String itemTitle, int iconDrawableId) {
        title = itemTitle;
        drawableId = iconDrawableId;
    }
}
