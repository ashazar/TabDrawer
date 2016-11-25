package com.ashazar.tabdrawer.model;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabDetail {
    private String title;
    private int drawableId;
    private boolean isSelected;

    public TabDetail() { }

    public TabDetail(String itemTitle) {
        title = itemTitle;
    }

    public TabDetail(String itemTitle, int iconDrawableId) {
        title = itemTitle;
        drawableId = iconDrawableId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getDrawableId() { return drawableId; }
    public void setDrawableId(int drawableId) { this.drawableId = drawableId; }

    public void setSelected(boolean status) { this.isSelected = status; }
    public boolean isSelected() { return isSelected; }
}
