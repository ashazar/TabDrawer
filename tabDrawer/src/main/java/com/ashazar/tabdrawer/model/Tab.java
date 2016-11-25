package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class Tab {
    private String title = null;
    private int drawableId = 0;
    private int drawableId_selected = 0;
    private ArrayList<TabDetail> list = null;

    public Tab() { }

    public Tab setTitle(String title) {
        this.title = title;
        return this;
    }

    public Tab setDrawableId(int drawableId) {
        this.drawableId = drawableId;
        return this;
    }

    public Tab setSelectedDrawableId(int selectedDrawableId) {
        this.drawableId_selected = selectedDrawableId;
        return this;
    }

    public Tab addTabDetailItem(TabDetail item) {
        if (list == null)
            list = new ArrayList<>();

        list.add(item);

        return this;
    }


    public String getTitle() { return title; }
    public int getDrawableId() { return drawableId; }
    public int getDrawableId_selected() { return drawableId_selected; }
    public ArrayList<TabDetail> getTabItemList() { return list; }
}