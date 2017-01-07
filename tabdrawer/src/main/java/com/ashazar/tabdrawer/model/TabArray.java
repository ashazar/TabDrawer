package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * TabArray model (Everything -all tabs and their list items- included) for TabDrawer
 * <p>
 * Created by Serdar Hazar on 25/11/16.
 */
public class TabArray {
    private ArrayList<Tab> tabArray = null;
    
    private int tabLayoutResourceId = 0;
    private int tabBackgroundColor = 0;
    private int tabBackgroundColor_selected = 0;
    private int tabBackgroundColor_selectedInactive = 0;
    private int titleSize = 12;
    private int tabTitleColor = 0;
    private int tabTitleColor_selected = 0;
    private int tabTitleColor_selectedInactive = 0;
    private int tabIconColor = 0;
    private int tabIconColor_selected = 0;
    private int tabIconColor_selectedInactive = 0;
    
    private int tabListItemTextColor = 0;
    private int tabListItemTextSize = 16;

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
            set TabListItem's text color and text size, if not set before.
         */
        if (tab.getListItemTextColor() == 0) {
            for (TabListItem tabListItem : tab.getTabItemList()) {
                tabListItem.setTextColor(tabListItemTextColor);
            }
        }

        if (tab.getListItemTextSize() == 0) {
            for (TabListItem tabListItem : tab.getTabItemList()) {
                tabListItem.setTextSize(tabListItemTextSize);
            }
        }

        tabArray.add(tab);
        return this;
    }

    /**
     * Gets the Tab at given position
     *
     * @param position the position
     * @return the Tab at given position
     */
    public Tab getTab(int position) { return tabArray.get(position); }

    /**
     * Get the tab count of TabArray.
     *
     * @return the size of the array
     */
    public int getTabCount() { return tabArray.size(); }

    /**
     * Sets the Layout Resource Id of tabs
     * All tabs will use this layout, unless a specific layout for a tab not set
     *
     * @param resourceId Resource Layout Id of the tabs
     * @return the TabArray
     */
    public TabArray setCustomTabLayoutResourceId(int resourceId) {
        tabLayoutResourceId = resourceId;
        return this;
    }

    /**
     * Get the layout resource Id for the tabs
     *
     * @return int Tab Layout Resource Id
     */
    public int getCustomTabLayoutResourceId() { return tabLayoutResourceId; }

    public TabArray setTabBackgroundColor(int color) {
        tabBackgroundColor = color;
        return this;
    }

    public int getTabBackgroundColor() { return tabBackgroundColor; }

    public TabArray setSelectedTabBackgroundColor(int color) {
        tabBackgroundColor_selected = color;
        return this;
    }

    public int getSelectedTabBackgroundColor() { return tabBackgroundColor_selected; }

    public TabArray setSelectedInactiveTabBackgroundColor(int color) {
        tabBackgroundColor_selectedInactive = color;
        return this;
    }

    public int getSelectedInactiveTabBackgroundColor() { return tabBackgroundColor_selectedInactive; }

    public TabArray setTitleSize(int size) {
        titleSize = size;
        return this;
    }

    public int getTitleSize() { return titleSize; }

    public TabArray setTabTitleColor(int color) {
        tabTitleColor = color;
        return this;
    }

    public int getTabTitleColor() { return tabTitleColor; }

    public TabArray setSelectedTabTitleColor(int color) {
        tabTitleColor_selected = color;
        return this;
    }

    public int getSelectedTabTitleColor() { return tabTitleColor_selected; }

    public TabArray setSelectedInactiveTabTitleColor(int color) {
        tabTitleColor_selectedInactive = color;
        return this;
    }

    public int getSelectedInactiveTabTitleColor() { return tabTitleColor_selectedInactive; }

    public TabArray setTabIconColor(int color) {
        tabIconColor = color;
        return this;
    }

    public int getTabIconColor() { return tabIconColor; }

    public TabArray setSelectedIconColor(int color) {
        tabIconColor_selected = color;
        return this;
    }

    public int getSelectedTabIconColor() { return tabIconColor_selected; }

    public TabArray setSelectedInactiveTabIconColor(int color) {
        tabIconColor_selectedInactive = color;
        return this;
    }

    public int getSelectedInactiveTabIconColor() { return tabIconColor_selectedInactive; }



    /**
     * Sets tab item list text color.
     *
     * @param color the color
     * @return the TabArray
     */
    public TabArray setTabListItemTextColor(int color) {
        tabListItemTextColor = color;
        return this;
    }

    /**
     * Sets tab item list text size.
     *
     * @param size the size
     * @return the TabArray
     */
    public TabArray setTabListItemTextSize(int size) {
        tabListItemTextSize = size;
        return this;
    }

}
