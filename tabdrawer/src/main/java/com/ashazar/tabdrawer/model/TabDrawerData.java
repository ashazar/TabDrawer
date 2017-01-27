package com.ashazar.tabdrawer.model;

import android.graphics.Typeface;

import java.util.ArrayList;

/**
 * TabDrawerData model (Everything -all tabs and their list items- included) for TabDrawer
 * <p>
 * Created by Serdar Hazar on 25/11/16.
 */
public class TabDrawerData {
    private ArrayList<Tab> tabArray = null;
    
    private int tabLayoutResourceId = 0;
    private ArrayList<Integer> tabBackgroundColor = null;
    private Typeface tabTitleFont = null;
    private int tabTitleSize = 12;
    private ArrayList<Integer> tabTitleColor = null;
    private ArrayList<Integer> tabIconColor = null;
    private boolean animateScaleTabIconWhenSelected = true;
    private float tabIconScaleValueWhenSelected = 1.2f;
    private boolean boldTabTitleWhenSelected = true;
    private boolean resetTabViewSettings = false;

    private boolean hasAtLeastOneDrawerForList = false;
    private int tabWithDrawerCount = 0;
    private int customDrawerLayoutResourceId = 0;
    private int customDrawerGridViewId = 0;
    private int drawerListNumColumns = 0;
    private int customDrawerListItemLayoutResourceId = 0;
    private ArrayList<Integer> tabListItemTitleColor = null;
    private int tabListItemTitleSize = 16;
    private Typeface tabListItemTitleFont = null;
    private boolean resetTabListAdapterViewSettings = false;

    /**
     * Instantiates a new Tab array.
     */
    public TabDrawerData() { tabArray = new ArrayList<>(); }

    /**
     * Adds a Tab to TabDrawerData.
     *
     * @param tab the Tab
     * @return the TabDrawerData
     */
    public TabDrawerData addTab(Tab tab) {
        /*  Before adding the Tab,
            set general Tab properties (custom Tab layout, background color, title color&size, colors when tab is selected, etc.)
            set TabListItem's text color and text size, if not set before.
         */
        if (tab.getCustomTabLayoutResourceId() == 0  &&  !tab.willUseDefaultLayout()) {
            tab.setCustomTabLayoutResourceId(tabLayoutResourceId);
        }

        if (tab.getBackgroundColors() == null  &&  tabBackgroundColor != null) {
            tab.setBackgroundColors(
                    tabBackgroundColor.get(0),
                    tabBackgroundColor.get(1),
                    tabBackgroundColor.get(2)
            );
        }

        if (tab.getTitleFont() == null) {
            tab.setTitleFont(tabTitleFont);
        }

        if (tab.getTitleSize() == 0) {
            tab.setTitleSize(tabTitleSize);
        }

        if (tab.getTitleColors() == null  &&  tabTitleColor != null) {
            tab.setTitleColors(
                    tabTitleColor.get(0),
                    tabTitleColor.get(1),
                    tabTitleColor.get(2)
            );
        }

        if (tab.getIconColors() == null  &&  tabIconColor != null) {
            tab.setIconColors(
                    tabIconColor.get(0),
                    tabIconColor.get(1),
                    tabIconColor.get(2)
            );
        }

        if (tab.getAnimateScaleIconWhenSelected()) {
            tab.setAnimateScaleIconWhenSelected(animateScaleTabIconWhenSelected);
        }

        if (tab.getIconScaleValueWhenSelected() == 1.2f) {
            tab.setIconScaleValueWhenSelected(tabIconScaleValueWhenSelected);
        }

        if (tab.getBoldTitleWhenSelected()) {
            tab.setBoldTitleWhenSelected(boldTabTitleWhenSelected);
        }

        /*  Before adding the Tab,
            set custom drawer layout, TabListItem's text color and text size, if not set before.
         */
        if (tab.getCustomDrawerLayoutResourceId() == 0  &&  !tab.willUseDefaultDrawerLayout()) {
            tab.setCustomDrawerLayoutResourceId(customDrawerLayoutResourceId);
        }

        if (!tab.isResetTabViewSettingsSet()  &&  resetTabViewSettings) {
            tab.dontUseDefaultTabViewSettings();
        }

        if (tab.hasItems()) {
            hasAtLeastOneDrawerForList = true;
            tabWithDrawerCount++;
        }

        if (tab.hasItems()) {
            if (!tab.isResetListAdapterViewSettingsSet()  &&  resetTabListAdapterViewSettings) {
                tab.dontUseDefaultListAdapterViewSettings();
            }

            if (tab.getDrawerListColumnNumber() == 0) {
                tab.setDrawerListColumnNumber(drawerListNumColumns);
            }

            if (tab.getCustomDrawerGridViewId() == 0) {
                tab.setCustomDrawerGridViewId(customDrawerGridViewId);
            }

            if (tab.getCustomDrawerListItemLayoutResourceId() == 0) {
                tab.setCustomDrawerListItemLayoutResourceId(customDrawerListItemLayoutResourceId);
            }

            if (tab.getListItemTitleColors() == null  &&  tabListItemTitleColor != null) {
                tab.setListItemTitleColors(
                        tabListItemTitleColor.get(0),
                        tabListItemTitleColor.get(1)
                );
            }

            if (tab.getListItemTitleSize() == 0) {
                tab.setListItemTitleSize(tabListItemTitleSize);
            }

            if (tab.getListItemTitleFont() == null) {
                tab.setListItemTitleFont(tabListItemTitleFont);
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
     * Get the tab count of TabDrawerData.
     *
     * @return the size of the array
     */
    public int getTabCount() { return tabArray.size(); }

    /**
     * Get if a tab drawer exists;
     *
     * @return boolean : true if at least one tab has drawer (list items)
     */
    public boolean hasDrawerForList() { return hasAtLeastOneDrawerForList; }

    /**
     * Gets the number of tabs that has list items (has drawer)
     *
     * @return number of the tabs with a drawer
     */
    public int getTabWithDrawerCount() { return tabWithDrawerCount; }

    /**
     * Sets the Layout Resource Id of tabs
     * All tabs will use this layout, unless a specific layout for a tab not set
     *
     * @param resourceId Resource Layout Id of the tabs
     * @return the TabDrawerData
     */
    public TabDrawerData setCustomTabLayoutResourceId(int resourceId) {
        tabLayoutResourceId = resourceId;
        return this;
    }

    /**
     * Sets background colors of all Tabs.
     *
     * inactiveSelected:
     *      active tab, but temporarily inactive
     *      because another tab is clicked and the drawer opened.
     *
     * @param color normal (unselected) tab's background color
     * @param selectedColor selected tab's background color
     * @param inactiveSelectedColor inactive selected tab's background color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabBackgroundColors(int color, int selectedColor, int inactiveSelectedColor) {
        tabBackgroundColor = new ArrayList<>();
        tabBackgroundColor.add(0, color);
        tabBackgroundColor.add(1, selectedColor);
        tabBackgroundColor.add(2, inactiveSelectedColor);

        return this;
    }

    /**
     * Sets Typeface of the Title
     *
     * @param font Typeface of Title
     * @return the TabDrawerData
     */
    public TabDrawerData setTabTitleFont(Typeface font) {
        tabTitleFont = font;
        return this;
    }

    /**
     * Sets title size for all tabs.
     *
     * @param size the size
     * @return the TabDrawerData
     */
    public TabDrawerData setTabTitleSize(int size) {
        tabTitleSize = size;
        return this;
    }

    /**
     * Sets title colors of all Tabs
     *
     * inactiveSelected:
     *      active tab, but temporarily inactive
     *      because another tab is clicked and the drawer opened.
     *
     * @param color normal (unselected) tab's title color
     * @param selectedColor selected tab's title color
     * @param inactiveSelectedColor inactive selected tab's title color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabTitleColors(int color, int selectedColor, int inactiveSelectedColor) {
        tabTitleColor = new ArrayList<>();
        tabTitleColor.add(0, color);
        tabTitleColor.add(1, selectedColor);
        tabTitleColor.add(2, inactiveSelectedColor);

        return this;
    }

    /**
     * Sets tab icon colors of all Tabs. (Tint ImageView)
     *
     * inactiveSelected:
     *      active tab, but temporarily inactive
     *      because another tab is clicked and the drawer opened.
     *
     * @param color normal (unselected) tab's icon color
     * @param selectedColor selected tab's icon color
     * @param inactiveSelectedColor inactive selected tab's icon color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabIconColors(int color, int selectedColor, int inactiveSelectedColor) {
        tabIconColor = new ArrayList<>();
        tabIconColor.add(0, color);
        tabIconColor.add(1, selectedColor);
        tabIconColor.add(2, inactiveSelectedColor);

        return this;
    }

    /**
     * Sets if to animate and scale up the icon, when tab is selected
     * Default: true
     *
     * @param scale scale
     * @return the TabDrawerData
     */
    public TabDrawerData setAnimateScaleTabIconWhenSelected(boolean scale) {
        animateScaleTabIconWhenSelected = scale;
        return this;
    }

    /**
     * Sets the scale value of selected tab's icon
     * Default: 1.2f
     *
     * @param value scale value
     * @return TabDrawerData
     */
    public TabDrawerData setTabIconScaleValueWhenSelected(float value) {
        tabIconScaleValueWhenSelected = value;
        return this;
    }

    /**
     * Sets if to make the title bold, when tab is selected
     * Default: true
     *
     * @param bold bold
     * @return the TabDrawerData
     */
    public TabDrawerData setBoldTabTitleWhenSelected(boolean bold) {
        boldTabTitleWhenSelected = bold;
        return this;
    }

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
    public TabDrawerData dontUseDefaultTabViewSettings() {
        resetTabViewSettings = true;
        return this;
    }


    /**
     * Sets the custom drawer layout (RelativeLayout) resource Id for all tabs.
     *
     * @param resourceId Custom Drawer layout resource Id
     *
     * @return the Tab
     */
    public TabDrawerData setCustomDrawerLayoutResourceId(int resourceId) {
        customDrawerLayoutResourceId = resourceId;
        return this;
    }

    /**
     * Sets the number of columns in GridView in the drawer
     *
     * @param num Number of columns in GridView
     * @return the TabDrawerData
     */
    public TabDrawerData setDrawerListColumnNumber(int num) {
        drawerListNumColumns = num;
        return this;
    }

    /**
     * Sets the id for the custom GridView inside the custom drawer layout
     * Works only with Custom Drawer layout is set with setCustomDrawerLayoutResourceId()
     *
     * @param viewId GridView id
     * @return the TabDrawerData
     */
    public TabDrawerData setCustomDrawerGridViewId(int viewId) {
        customDrawerGridViewId = viewId;
        return this;
    }

    /**
     * Sets the resource id for the custom item layout to be used in GridView inside the drawer
     *
     * @param resourceId Custom Layout Resource Id
     * @return the TabDrawerData
     */
    public TabDrawerData setCustomDrawerListItemLayoutResourceId(int resourceId) {
        customDrawerListItemLayoutResourceId = resourceId;
        return this;
    }

    /**
     * Sets tab list item's title color of all tabs.
     *
     * @param color normal (unselected) tab's list item's title color
     * @param selectedColor selected tab's list item's title color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabListItemTitleColors(int color, int selectedColor) {
        tabListItemTitleColor = new ArrayList<>();
        tabListItemTitleColor.add(0, color);
        tabListItemTitleColor.add(1, selectedColor);

        return this;
    }

    /**
     * Sets tab item list text size.
     *
     * @param size the size
     * @return the TabDrawerData
     */
    public TabDrawerData setTabListItemTitleSize(int size) {
        tabListItemTitleSize = size;
        return this;
    }

    /**
     * Sets Typeface of the Tab List item's Title
     *
     * @param font Typeface of Tab List item's title
     * @return the TabDrawerData
     */
    public TabDrawerData setTabListItemTitleFont(Typeface font) {
        tabListItemTitleFont = font;
        return this;
    }

    /**
     * Gets the typeface of the Tab List item's Title.
     *
     * @return the typeface of Tab List item's title
     */
    public Typeface getTabListItemTitleFont() { return tabListItemTitleFont; }

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
     * @return the TabDrawerData
     */
    public TabDrawerData dontUseDefaultTabListAdapterViewSettings() {
        resetTabListAdapterViewSettings = true;
        return this;
    }
}
