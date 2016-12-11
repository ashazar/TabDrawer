package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 25/11/16.
 */

public class TabArray {
    private ArrayList<Tab> tabArray = null;
    private int tabItemListTextColor;
    private int tabItemListTextSize;

    public TabArray() { tabArray = new ArrayList<>(); }

    public TabArray addTab(Tab tab) {
        if (tab.getItemListTextColor() == 0) {
            for (TabDetail tabDetail : tab.getTabItemList()) {
                tabDetail.setTextColor(tabItemListTextColor);
            }
        }

        if (tab.getItemListTextSize() == 0) {
            for (TabDetail tabDetail : tab.getTabItemList()) {
                tabDetail.setTextSize(tabItemListTextSize);
            }
        }

        tabArray.add(tab);
        return this;
    }

    public TabArray setTabItemListTextColor(int color) {
        tabItemListTextColor = color;
        return this;
    }
    public TabArray setTabItemListTextSize(int size) {
        tabItemListTextSize = size;
        return this;
    }

    public int size() { return tabArray.size(); }
    public Tab getTab(int position) { return tabArray.get(position); }
}
