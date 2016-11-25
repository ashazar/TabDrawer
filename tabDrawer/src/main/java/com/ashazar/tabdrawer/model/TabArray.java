package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 25/11/16.
 */

public class TabArray {
    private ArrayList<Tab> tabArray = null;

    public TabArray() { tabArray = new ArrayList<>(); }

    public TabArray addTab(Tab tab) {
        tabArray.add(tab);
        return this;
    }

    public int size() { return tabArray.size(); }
    public Tab getTab(int position) { return tabArray.get(position); }
}
