package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class Tab {
    public String title = null;
    public int drawableId = 0;
    public int selectedDrawableId = 0;
    public ArrayList<TabDetail> list = null;


    public Tab(String tabTitle) {
        title = tabTitle;
    }

    public Tab(int...iconDrawableId) {
        drawableId = iconDrawableId[0];
        if (iconDrawableId.length >= 2) selectedDrawableId = iconDrawableId[1];
    }

    public Tab(String tabTitle, int...iconDrawableId) {
        title = tabTitle;
        drawableId = iconDrawableId[0];
        if (iconDrawableId.length >= 2) selectedDrawableId = iconDrawableId[1];
    }

    public void addList(ArrayList<TabDetail> tabList) {
        list = tabList;
    }
}
