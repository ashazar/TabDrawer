package com.ashazar.tabdrawer.model;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabDetail {
    private String title;
    private int drawableId = -1;
    private boolean isSelected;
    private int textColor;
    private int textSize;

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

    public void setTextColor(int color) { textColor = color; }
    public int getTextColor() { return textColor; }

    public void setTextSize(int size) { textSize = size; }
    public int getTextSize() { return textSize; }
}
