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
    private ArrayList<Integer> iconImage = null;

    private int tabLayoutResourceId = 0;
    private boolean useDefaultLayout = false; // will be used to force using default (internal) tab layout
    private ArrayList<Integer> backgroundColor = null;
    private Typeface titleFont = null;
    private int titleSize = 0;
    private ArrayList<Integer> titleColor = null;
    private ArrayList<Integer> iconColor = null;
    private boolean animateScaleIconWhenSelected = true;
    private float iconScaleValueWhenSelected = 1.2f;
    private boolean boldTitleWhenSelected = true;
    private boolean resetTabViewSettings = false;
    private int isResetTabViewSettingsSet = -1;

    private ArrayList<Integer> tabListItemTitleColor = null;
    private int tabListItemTitleSize = 0;
    private Typeface tabListItemTitleFont = null;
    private int customDrawerLayoutResourceId = 0;
    private boolean useDefaultDrawerLayout = false; // will be used to force using default (internal) drawer layout
    private int customDrawerGridViewId = 0;
    private int drawerListNumColumns = 0;
    private int customDrawerListItemLayoutResourceId = 0;
    private boolean resetListAdapterViewSettings = false;
    private int isResetListAdapterViewSettingsSet = -1;
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
     * @param drawableIds Drawable Resource Ids for Tab icon
     *                    normal, when tab is selected, when tab is inactiveSelected
     *
     * @return the Tab
     */
    public Tab setIconImage(int...drawableIds) {
        int size = drawableIds.length - 1;
        int lastItem = 0;
        iconImage = new ArrayList<>();
        for (int i = 0; i <=2; i++) {
            if (i <= size) {
                iconImage.add(i, drawableIds[i]);
                lastItem = drawableIds[i];
            }
            else {
                iconImage.add(i, lastItem);
            }
        }
        return this;
    }

    /**
     * Gets drawable id of the tab icon.
     *
     * @return the drawable id
     */
    public int getIconImage() { return iconImage.get(0); }

    /**
     * Gets drawable id of the tab icon when tab is selected.
     *
     * @return the drawable id selected
     */
    public int getSelectedIconImage() { return iconImage.get(1); }

    /**
     * Gets drawable id of the tab icon when tab is selected but inactive.
     *
     * @return the drawable id selected
     */
    public int getInactiveSelectedIconImage() { return iconImage.get(2); }

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
     * If a common tab layout resource id set in TabDrawerData, and don't want to use it for this tab,
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
    boolean willUseDefaultLayout() { return useDefaultLayout; }

    /**
     * Sets background colors of the Tab.
     *
     * inactiveSelected:
     *      active tab, but temporarily inactive
     *      because another tab is clicked and the drawer opened.
     *
     * @param color normal (unselected) tab's background color
     * @param selectedColor selected tab's background color
     * @param inactiveSelectedColor inactive selected tab's background color
     * @return the Tab
     */
    public Tab setBackgroundColors(int color, int selectedColor, int inactiveSelectedColor) {
        backgroundColor = new ArrayList<>();
        backgroundColor.add(0, color);
        backgroundColor.add(1, selectedColor);
        backgroundColor.add(2, inactiveSelectedColor);

        return this;
    }

    /**
     * Used by TabDrawerData only
     * @return backgroundColors
     */
    ArrayList<Integer> getBackgroundColors() { return backgroundColor; }

    /**
     * Gets background color.
     *
     * @return the tab background color
     */
    public int getBackgroundColor() { return backgroundColor.get(0); }

    /**
     * Gets background color for the selected tab.
     *
     * @return the selected tab background color
     */
    public int getSelectedBackgroundColor() { return backgroundColor.get(1); }

    /**
     * Gets background color of the inactive selected tab .
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @return the inactive selected tab background color
     */
    public int getInactiveSelectedBackgroundColor() { return backgroundColor.get(2); }

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
     * Sets title colors of the Tab
     *
     * inactiveSelected:
     *      active tab, but temporarily inactive
     *      because another tab is clicked and the drawer opened.
     *
     * @param color normal (unselected) tab's title color
     * @param selectedColor selected tab's title color
     * @param inactiveSelectedColor inactive selected tab's title color
     * @return the Tab
     */
    public Tab setTitleColors(int color, int selectedColor, int inactiveSelectedColor) {
        titleColor = new ArrayList<>();
        titleColor.add(0, color);
        titleColor.add(1, selectedColor);
        titleColor.add(2, inactiveSelectedColor);

        return this;
    }

    /**
     * Used by TabDrawerData only
     * @return TitleColors
     */
    ArrayList<Integer> getTitleColors() { return titleColor; }

    /**
     * Gets tab title color.
     *
     * @return the tab title color
     */
    public int getTitleColor() { return titleColor.get(0); }

    /**
     * Gets selected tab title color.
     *
     * @return the selected tab title color
     */
    public int getSelectedTitleColor() { return titleColor.get(1); }

    /**
     * Gets inactive selected tab title color.
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @return the inactive selected tab title color
     */
    public int getInactiveSelectedTitleColor() { return titleColor.get(2); }

    /**
     * Sets tab icon color. (Tint ImageView)
     *
     * inactiveSelected:
     *      active tab, but temporarily inactive
     *      because another tab is clicked and the drawer opened.
     *
     * @param color normal (unselected) tab's icon color
     * @param selectedColor selected tab's icon color
     * @param inactiveSelectedColor inactive selected tab's icon color
     * @return the Tab
     */
    public Tab setIconColors(int color, int selectedColor, int inactiveSelectedColor) {
        iconColor = new ArrayList<>();
        iconColor.add(0, color);
        iconColor.add(1, selectedColor);
        iconColor.add(2, inactiveSelectedColor);

        return this;
    }

    /**
     * Used by TabDrawerData only
     * @return IconColors
     */
    ArrayList<Integer> getIconColors() { return iconColor; }

    /**
     * Gets tab icon color. (Tint ImageView)
     *
     * @return the tab icon color
     */
    public int getIconColor() { return iconColor.get(0); }

    /**
     * Gets selected tab icon color.
     *
     * @return the selected tab icon color
     */
    public int getSelectedIconColor() { return iconColor.get(1); }

    /**
     * Gets inactive selected tab icon color.
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @return the inactive selected tab icon color
     */
    public int getInactiveSelectedIconColor() { return iconColor.get(2); }

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
        if (list == null) {
            list = new ArrayList<>();
        }

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
     * Sets the number of columns in GridView in the drawer
     *
     * @param num Number of columns in GridView
     * @return the Tab
     */
    public Tab setDrawerListColumnNumber(int num) {
        drawerListNumColumns = num;
        return this;
    }

    /**
     * Gets the number of columns in GridView in the drawer
     *
     * @return number of columns
     */
    public int getDrawerListColumnNumber() { return drawerListNumColumns; }

    /**
     * Sets the id for the custom GridView inside the custom drawer layout
     * Works only with Custom Drawer layout is set with setCustomDrawerLayoutResourceId()
     *
     * @param viewId GridView id
     * @return the Tab
     */
    public Tab setCustomDrawerGridViewId(int viewId) {
        customDrawerGridViewId = viewId;
        return this;
    }

    /**
     * Gets the id for the custom GridView inside the custom drawer layout
     * Works only with Custom Drawer layout is set with setCustomDrawerLayoutResourceId()
     *
     * @return int id of GridView
     */
    public int getCustomDrawerGridViewId() { return customDrawerGridViewId; }


    /**
     * Sets the resource id for the custom item layout to be used in GridView inside the drawer
     *
     * @param resourceId Custom Layout Resource Id
     * @return the Tab
     */
    public Tab setCustomDrawerListItemLayoutResourceId(int resourceId) {
        customDrawerListItemLayoutResourceId = resourceId;
        return this;
    }

    /**
     * Gets the layout resource id for the custom item layout in GridView inside the drawer
     *
     * @return int layout resource id
     */
    public int getCustomDrawerListItemLayoutResourceId() { return customDrawerListItemLayoutResourceId; }

    /**
     * Sets the custom drawer layout (RelativeLayout) resource Id.
     *
     * @param resourceId Custom Drawer layout resource Id
     *
     * @return the Tab
     */
    public Tab setCustomDrawerLayoutResourceId(int resourceId) {
        customDrawerLayoutResourceId = resourceId;
        useDefaultDrawerLayout = false;
        return this;
    }

    /**
     * Gets the custom drawer layout resource id
     *
     * @return resource id
     */
    public int getCustomDrawerLayoutResourceId() { return customDrawerLayoutResourceId; }

    /**
     * If a common tab drawer layout resource id set in TabDrawerData, and don't want to use it for this tab,
     * instead of defining another custom drawer layout,
     * force this tab to use internal default drawer layout.
     *
     * @return the Tab
     */
    public Tab forceDefaultDrawerLayout() {
        useDefaultDrawerLayout = true;
        customDrawerLayoutResourceId = 0;
        return this;
    }

    /**
     * Get if the tab is forced ( forceDefaultLayout() ) to use default layout
     *
     * @return boolean
     */
    boolean willUseDefaultDrawerLayout() { return useDefaultDrawerLayout; }

    /**
     * Sets if the developer wants to reset the default Tab view settings.
     * (Unselected, Selected, InactiveSelected)
     * If developer doesn't want to use the standard internal defined view settings,
     * and wants to  use his/her own.
     *
     * If this method is called; developer has to override
     * setUnselectedTabView(), setSelectedTabView() and setInactiveSelectedTabView()
     * to define his/her custom views.
     *
     * Default: when the tab is selected;
     *  Tab's   Background color is set to Selected Background Color,
     *          Title becomes Bold, title color is set to Selected Title Color
     *          Icon (image) scales up with animation
     *          Drawer background color is set to Selected Background Color.
     *
     * @return the Tab
     */
    public Tab dontUseDefaultTabViewSettings() {
        resetTabViewSettings = true;
        isResetTabViewSettingsSet = 1;
        return this;
    }

    /**
     * Gets the Tab view settings status.
     *
     * @return true, if developer doesn't want to use standard defined settings,
     *         and wants to use his/her own.
     */
    public boolean getCustomTabViewSettingsStatus() { return resetTabViewSettings; }

    /**
     * Gets if dontUseDefaultTabViewSettings() is called by the Tab itself.
     * Only TabDrawerData uses this method to determine if it needs to be overriden by TabDrawerData.
     *
     * @return true, if dontUseDefaultTabViewSettings() is called for this tab
     */
    boolean isResetTabViewSettingsSet() { return (isResetTabViewSettingsSet == 1); }

    /**
     * Sets tab list item's title color.
     *
     * @param color normal (unselected) tab's list item's title color
     * @param selectedColor selected tab's list item's title color
     * @return the Tab
     */
    public Tab setListItemTitleColors(int color, int selectedColor) {
        tabListItemTitleColor = new ArrayList<>();
        tabListItemTitleColor.add(0, color);
        tabListItemTitleColor.add(1, selectedColor);

        return this;
    }

    /**
     * Used by TabDrawerData only
     * @return ListItemTitleColors
     */
    ArrayList<Integer> getListItemTitleColors() { return tabListItemTitleColor; }

    /**
     * Gets item list title color.
     *
     * @return the item list title color
     */
    public int getListItemTitleColor() { return tabListItemTitleColor.get(0); }

    /**
     * Gets item list text color when it's selected.
     *
     * @return selected item list's title color
     */
    public int getSelectedListItemTitleColor() { return tabListItemTitleColor.get(1); }

    /**
     * Sets tab item list text size.
     *
     * @param size the size
     * @return the Tab
     */
    public Tab setListItemTitleSize(int size) {
        tabListItemTitleSize = size;
        return this;
    }

    /**
     * Gets item list text size.
     *
     * @return the item list text size
     */
    public int getListItemTitleSize() { return tabListItemTitleSize; }

    /**
     * Sets Typeface of the List item's Title
     *
     * @param font Typeface of List item's title
     * @return the Tab
     */
    public Tab setListItemTitleFont(Typeface font) {
        tabListItemTitleFont = font;
        return this;
    }

    /**
     * Gets the typeface of the List item's Title.
     *
     * @return the typeface of title
     */
    public Typeface getListItemTitleFont() { return tabListItemTitleFont; }

    /**
     * Sets if the developer wants to reset the default ListAdapterViewSettings.
     * If developer doesn't want to use the standard internal defined view settings,
     * and wants to  use his/her own.
     *
     * If this method is called; developer has to override
     * setUnselectedListItemView() and setSelectedListItemView()
     * to define his/her custom views.
     *
     * Default: when the list item is selected;
     * Increase the title's text size, make it bold; increase the icon size
     *
     * @return the Tab
     */
    public Tab dontUseDefaultListAdapterViewSettings() {
        resetListAdapterViewSettings = true;
        isResetListAdapterViewSettingsSet = 1;
        return this;
    }

    /**
     * Gets the ListAdapterViewSettings status.
     *
     * @return true, if developer doesn't want to use standard defined settings,
     *         and wants to use his/her own.
     */
    public boolean getCustomListAdapterViewSettingsStatus() { return resetListAdapterViewSettings; }

    /**
     * Gets if dontUseDefaultListAdapterViewSettings() is called by the Tab itself.
     * Only TabDrawerData uses this method to determine if it needs to be overriden by TabDrawerData.
     *
     * @return true, if dontUseDefaultListAdapterViewSettings() is called for this tab
     */
    boolean isResetListAdapterViewSettingsSet() { return (isResetListAdapterViewSettingsSet == 1); }
}