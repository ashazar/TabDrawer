package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Tab model for TabDrawer
 * <p>
 * Created by Serdar Hazar on 26/04/16.
 */
public class Tab {
    /**
     * Common variables for both Custom Layout and standard library layout
     * title and drawableId
     */
    private String title = null;
    private int drawableId = 0;

    private int tabLayoutResourceId = 0;
    private int backgroundColor = 0;
    private int backgroundColor_selected = 0;
    private int backgroundColor_selectedInactive = 0;
    private int titleSize = 12;
    private int titleColor = 0;
    private int titleColor_selected = 0;
    private int titleColor_selectedInactive = 0;
    private int iconColor = 0;
    private int iconColor_selected = 0;
    private int iconColor_selectedInactive = 0;
    private int drawableId_selected = 0;
    private int tabListItemTextColor = 0;
    private int tabListItemTextSize = 0;
    private ArrayList<TabListItem> list = null;

    /**
     * Instantiates a new Tab.
     */
    public Tab() { }



    /**
     * Sets title text for Tab
     *
     * @param title Title
     * @return the Tab
     */
    public Tab setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Sets Tab image
     *
     * @param drawableId Drawable Resource Id for Tab icon
     * @return the Tab
     */
    public Tab setDrawableId(int drawableId) {
        this.drawableId = drawableId;
        return this;
    }

    /**
     * Gets drawable id.
     *
     * @return the drawable id
     */
    public int getDrawableId() { return drawableId; }

    /**
     * Sets Tab Image when it's selected.
     *
     * @param selectedDrawableId Drawable Resource Id for Tab icon when it's selected
     * @return the Tab
     */
    public Tab setSelectedDrawableId(int selectedDrawableId) {
        drawableId_selected = selectedDrawableId;
        return this;
    }

    /**
     * Gets drawable id selected.
     *
     * @return the drawable id selected
     */
    public int getDrawableId_selected() { return drawableId_selected; }

    /**
     * Sets the Custom Layout Resource Id of tab
     *
     * @param resourceId Resource Layout Id of the tabs
     * @return the Tab
     */
    public Tab setCustomTabLayoutResourceId(int resourceId) {
        tabLayoutResourceId = resourceId;
        return this;
    }

    /**
     * Get the Custom Layout Resource Id for the tab
     *
     * @return int Tab Layout Resource Id
     */
    public int getCustomTabLayoutResourceId() { return tabLayoutResourceId; }

    public Tab setBackgroundColor(int color) {
        backgroundColor = color;
        return this;
    }

    public int getBackgroundColor() { return backgroundColor; }

    public Tab setSelectedBackgroundColor(int color) {
        backgroundColor_selected = color;
        return this;
    }

    public int getSelectedBackgroundColor() { return backgroundColor_selected; }

    public Tab setSelectedInactiveBackgroundColor(int color) {
        backgroundColor_selectedInactive = color;
        return this;
    }

    public int getSelectedInactiveBackgroundColor() { return backgroundColor_selectedInactive; }

    public Tab setTitleSize(int size) {
        titleSize = size;
        return this;
    }

    public int getTitleSize() { return titleSize; }

    public Tab setTitleColor(int color) {
        titleColor = color;
        return this;
    }

    public int getTitleColor() { return titleColor; }

    public Tab setSelectedTitleColor(int color) {
        titleColor_selected = color;
        return this;
    }

    public int getSelectedTitleColor() { return titleColor_selected; }

    public Tab setSelectedInactiveTitleColor(int color) {
        titleColor_selectedInactive = color;
        return this;
    }

    public int getSelectedInactiveTitleColor() { return titleColor_selectedInactive; }

    public Tab setIconColor(int color) {
        iconColor = color;
        return this;
    }

    public int getIconColor() { return iconColor; }

    public Tab setSelectedIconColor(int color) {
        iconColor_selected = color;
        return this;
    }

    public int getSelectedIconColor() { return iconColor_selected; }

    public Tab setSelectedInactiveIconColor(int color) {
        iconColor_selectedInactive = color;
        return this;
    }

    public int getSelectedInactiveIconColor() { return iconColor_selectedInactive; }

    /**
     * Add Item to Tab's item list.
     *
     * @param item the item
     * @return the Tab
     */
    public Tab addTabListItem(TabListItem item) {
        if (list == null)
            list = new ArrayList<>();

        item.setTextColor(getListItemTextColor());
        item.setTextSize(getListItemTextSize());
        list.add(item);

        return this;
    }

    /**
     * Gets tab item list.
     *
     * @return the tab item list
     */
    public ArrayList<TabListItem> getTabItemList() { return list; }

    /**
     * Has items boolean.
     * Returns false if there are no items (Drawer won't open)
     *
     * @return the boolean
     */
    public boolean hasItems() {
        return !(list == null  ||  list.size() == 0);
    }

    /**
     * Sets tab item list text color.
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setTabListItemTextColor(int color) {
        tabListItemTextColor = color;
        return this;
    }

    /**
     * Gets item list text color.
     *
     * @return the item list text color
     */
    int getListItemTextColor() { return tabListItemTextColor; }

    /**
     * Sets tab item list text size.
     *
     * @param size the size
     * @return the Tab
     */
    public Tab setTabListItemTextSize(int size) {
        tabListItemTextSize = size;
        return this;
    }

    /**
     * Gets item list text size.
     *
     * @return the item list text size
     */
    int getListItemTextSize() { return tabListItemTextSize; }
}