package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * TabArray model (Everything -all tabs and their list items- included) for TabDrawer
 * <p>
 * Created by Serdar Hazar on 25/11/16.
 */
public class TabArray {
    private ArrayList<Tab> tabArray = null;
    private int tabItemListTextColor = 0;
    private int tabItemListTextSize = 16;

    /**
     * Instantiates a new Tab array.
     */
    public TabArray() { tabArray = new ArrayList<>(); }

    /**
     * Adds a Tab to TabArray.
     *
     * @param tab the Tab
     * @return the TabArray
     */
    public TabArray addTab(Tab tab) {
        /*  Before adding the Tab,
            set TabList item's text color and text size, if not set before.
         */
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

    /**
     * Sets tab item list text color.
     *
     * @param color the color
     * @return the TabArray
     */
    public TabArray setTabItemListTextColor(int color) {
        tabItemListTextColor = color;
        return this;
    }

    /**
     * Sets tab item list text size.
     *
     * @param size the size
     * @return the TabArray
     */
    public TabArray setTabItemListTextSize(int size) {
        tabItemListTextSize = size;
        return this;
    }

    /**
     * Get the tab count of TabArray.
     *
     * @return the size of the array
     */
    public int size() { return tabArray.size(); }

    /**
     * Gets the Tab at given position
     *
     * @param position the position
     * @return the Tab at given position
     */
    public Tab getTab(int position) { return tabArray.get(position); }
}
