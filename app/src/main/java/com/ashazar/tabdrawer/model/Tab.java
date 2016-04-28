package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class Tab {
    public int pos;
    public static int posCumulative = -1;

    public String title = null;
    public int drawableId = 0;
    public int selectedDrawableId = 0;
    ArrayList<TabDetail> list = null;

    /*
    boolean withIcon;
    boolean hasList;
    */


    public Tab(String tabTitle) {
        title = tabTitle;
        posCumulative++;
        pos = posCumulative;
    }

    public Tab(int...iconDrawableId) {
        drawableId = iconDrawableId[0];
        if (iconDrawableId.length >= 2) selectedDrawableId = iconDrawableId[1];
        posCumulative++;
        pos = posCumulative;
    }

    public Tab(String tabTitle, int...iconDrawableId) {
        title = tabTitle;
        drawableId = iconDrawableId[0];
        if (iconDrawableId.length >= 2) selectedDrawableId = iconDrawableId[1];
        posCumulative++;
        pos = posCumulative;
    }


    public void addList(ArrayList<TabDetail> tabList) {
        list = tabList;
    }
}
