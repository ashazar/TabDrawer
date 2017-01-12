package com.ashazar.tabdrawer.model;

import android.graphics.Typeface;

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
    private boolean useDefaultLayout = true;
    private int backgroundColor = 0;
    private int backgroundColor_selected = 0;
    private int backgroundColor_selectedInactive = 0;
    private Typeface titleFont = null;
    private int titleSize = 0;
    private int titleColor = 0;
    private int titleColor_selected = 0;
    private int titleColor_selectedInactive = 0;
    private int iconColor = 0;
    private int iconColor_selected = 0;
    private int iconColor_selectedInactive = 0;
    private int drawableId_selected = 0;
    private int tabListItemTextColor = 0;
    private int tabListItemTextSize = 0;
    private boolean animateScaleIconWhenSelected = true;
    private float iconScaleValueWhenSelected = 1.2f;
    private boolean boldTitleWhenSelected = true;
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
        useDefaultLayout = false;
        return this;
    }

    /**
     * Get the Custom Layout Resource Id for the tab
     *
     * @return int Tab Layout Resource Id
     */
    public int getCustomTabLayoutResourceId() { return tabLayoutResourceId; }

    /**
     * If a common tab layout resource id set in TabArray, and don't want to use it for this tab,
     * instead of defining another custom tab layout,
     * force this tab to use internal default layout.
     *
     * @return the Tab
     */
    public Tab forceDefaultLayout() {
        useDefaultLayout = true;
        tabLayoutResourceId = 0;
        return this;
    }

    /**
     * Get if the tab is forced ( forceDefaultLayout() ) to use default layout
     *
     * @return boolean
     */
    boolean willUseDefaultLayout() {
        return useDefaultLayout;
    }

    /**
     * Sets background color.
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setBackgroundColor(int color) {
        backgroundColor = color;
        return this;
    }

    /**
     * Gets background color.
     *
     * @return the tab background color
     */
    public int getBackgroundColor() { return backgroundColor; }

    /**
     * Sets background color for selected tab.
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setSelectedBackgroundColor(int color) {
        backgroundColor_selected = color;
        return this;
    }

    /**
     * Gets background color for the selected tab.
     *
     * @return the selected tab background color
     */
    public int getSelectedBackgroundColor() { return backgroundColor_selected; }

    /**
     * Sets background color for inactive selected tab
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setInactiveSelectedBackgroundColor(int color) {
        backgroundColor_selectedInactive = color;
        return this;
    }

    /**
     * Gets background color of the inactive selected tab .
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @return the inactive selected tab background color
     */
    public int getInactiveSelectedBackgroundColor() { return backgroundColor_selectedInactive; }

    /**
     * Sets Typeface of the Title
     *
     * @param font Typeface of Title
     * @return the Tab
     */
    public Tab setTitleFont(Typeface font) {
        titleFont = font;
        return this;
    }

    /**
     * Gets the typeface of Title.
     *
     * @return the typeface of title
     */
    public Typeface getTitleFont() { return titleFont; }

    /**
     * Sets title size.
     *
     * @param size the size
     * @return the Tab
     */
    public Tab setTitleSize(int size) {
        titleSize = size;
        return this;
    }

    /**
     * Gets tab title size.
     *
     * @return the tab title size
     */
    public int getTitleSize() { return titleSize; }

    /**
     * Sets title color for all tabs.
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setTitleColor(int color) {
        titleColor = color;
        return this;
    }

    /**
     * Gets tab title color.
     *
     * @return the tab title color
     */
    public int getTitleColor() { return titleColor; }

    /**
     * Sets title color for the selected tab.
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setSelectedTitleColor(int color) {
        titleColor_selected = color;
        return this;
    }

    /**
     * Gets selected tab title color.
     *
     * @return the selected tab title color
     */
    public int getSelectedTitleColor() { return titleColor_selected; }

    /**
     * Sets title color of the inactive selected tab .
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setInactiveSelectedTitleColor(int color) {
        titleColor_selectedInactive = color;
        return this;
    }

    /**
     * Gets inactive selected tab title color.
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @return the inactive selected tab title color
     */
    public int getInactiveSelectedTitleColor() { return titleColor_selectedInactive; }

    /**
     * Sets tab icon color. (Tint ImageView)
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setIconColor(int color) {
        iconColor = color;
        return this;
    }

    /**
     * Gets tab icon color. (Tint ImageView)
     *
     * @return the tab icon color
     */
    public int getIconColor() { return iconColor; }

    /**
     * Sets selected tab icon color.
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setSelectedIconColor(int color) {
        iconColor_selected = color;
        return this;
    }

    /**
     * Gets selected tab icon color.
     *
     * @return the selected tab icon color
     */
    public int getSelectedIconColor() { return iconColor_selected; }

    /**
     * Sets inactive selected tab icon color.
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @param color the color
     * @return the Tab
     */
    public Tab setInactiveSelectedIconColor(int color) {
        iconColor_selectedInactive = color;
        return this;
    }

    /**
     * Gets inactive selected tab icon color.
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @return the inactive selected tab icon color
     */
    public int getInactiveSelectedIconColor() { return iconColor_selectedInactive; }

    /**
     * Sets if to animate and scale up the icon, when tab is selected
     * Default: true
     *
     * @param scale scale
     * @return the Tab
     */
    public Tab setAnimateScaleIconWhenSelected(boolean scale) {
        animateScaleIconWhenSelected = scale;
        return this;
    }

    /**
     * Gets status of if animate & scale when the tab is selected
     * Default: true
     *
     * @return boolean
     */
    public boolean getAnimateScaleIconWhenSelected() { return animateScaleIconWhenSelected; }

    /**
     * Sets the scale value of selected tab's icon
     * Default: 1.2f
     *
     * @param value scale value
     * @return Tab
     */
    public Tab setIconScaleValueWhenSelected(float value) {
        iconScaleValueWhenSelected = value;
        return this;
    }

    /**
     * Gets the scale value of selected tab's icon
     * Default: 1.2f
     *
     * @return scale value
     */
    public float getIconScaleValueWhenSelected() { return iconScaleValueWhenSelected; }

    /**
     * Sets if to make the title bold, when tab is selected
     * Default: true
     *
     * @param bold bold
     * @return the Tab
     */

    public Tab setBoldTitleWhenSelected(boolean bold) {
        boldTitleWhenSelected = bold;
        return this;
    }

    /**
     * Gets status of bold title when the tab is selected
     * Default: true
     *
     * @return boolean
     */
    public boolean getBoldTitleWhenSelected() { return boldTitleWhenSelected; }

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