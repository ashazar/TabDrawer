package com.ashazar.tabdrawer.model;

import java.util.ArrayList;

/**
 * Tab model for TabDrawer
 * <p>
 * Created by Serdar Hazar on 26/04/16.
 */
public class Tab {
    private String title = null;
    private int drawableId = 0;
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
     * Sets Tab Image when it's selected.
     *
     * @param selectedDrawableId Drawable Resource Id for Tab icon when it's selected
     * @return the Tab
     */
    public Tab setSelectedDrawableId(int selectedDrawableId) {
        this.drawableId_selected = selectedDrawableId;
        return this;
    }

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
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Gets drawable id.
     *
     * @return the drawable id
     */
    public int getDrawableId() { return drawableId; }

    /**
     * Gets drawable id selected.
     *
     * @return the drawable id selected
     */
    public int getDrawableId_selected() { return drawableId_selected; }

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
     * Gets item list text color.
     *
     * @return the item list text color
     */
    int getListItemTextColor() { return tabListItemTextColor; }

    /**
     * Gets item list text size.
     *
     * @return the item list text size
     */
    int getListItemTextSize() { return tabListItemTextSize; }
}