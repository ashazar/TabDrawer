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
    private int tabBackgroundColor = 0;
    private int tabBackgroundColor_selected = 0;
    private int tabBackgroundColor_selectedInactive = 0;
    private Typeface tabTitleFont = null;
    private int tabTitleSize = 12;
    private int tabTitleColor = 0;
    private int tabTitleColor_selected = 0;
    private int tabTitleColor_selectedInactive = 0;
    private int tabIconColor = 0;
    private int tabIconColor_selected = 0;
    private int tabIconColor_selectedInactive = 0;
    private boolean animateScaleTabIconWhenSelected = true;
    private float tabIconScaleValueWhenSelected = 1.2f;
    private boolean boldTabTitleWhenSelected = true;
    private boolean resetTabViewSettings = false;

    private boolean hasAtLeastOneDrawerForList = false;
    private int customDrawerLayoutResourceId = 0;
    private int customDrawerGridViewId = 0;
    private int drawerListNumColumns = 0;
    private int customDrawerListItemLayoutResourceId = 0;
    private int tabListItemTitleColor = 0;
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
        // If selected tab title color not set for general, use tabTitleColor
        if (tabTitleColor_selected == 0) {
            tabTitleColor_selected = tabTitleColor;
        }

        // If inactive selected tab title color not set for general, use tabTitleColor
        if (tabTitleColor_selectedInactive == 0) {
            tabTitleColor_selectedInactive = tabTitleColor;
        }


        /*  Before adding the Tab,
            set general Tab properties (custom Tab layout, background color, title color&size, colors when tab is selected, etc.)
            set TabListItem's text color and text size, if not set before.
         */
        if (tab.getCustomTabLayoutResourceId() == 0  &&  !tab.willUseDefaultLayout()) {
            tab.setCustomTabLayoutResourceId(tabLayoutResourceId);
        }

        if (tab.getBackgroundColor() == 0) {
            tab.setBackgroundColor(tabBackgroundColor);
        }

        if (tab.getSelectedBackgroundColor() == 0) {
            tab.setSelectedBackgroundColor(tabBackgroundColor_selected);
        }

        if (tab.getInactiveSelectedBackgroundColor() == 0) {
            tab.setInactiveSelectedBackgroundColor(tabBackgroundColor_selectedInactive);
        }

        if (tab.getTitleFont() == null) {
            tab.setTitleFont(tabTitleFont);
        }

        if (tab.getTitleSize() == 0) {
            tab.setTitleSize(tabTitleSize);
        }

        if (tab.getTitleColor() == 0) {
            tab.setTitleColor(tabTitleColor);
        }

        if (tab.getSelectedTitleColor() == 0) {
            tab.setSelectedTitleColor(tabTitleColor_selected);
        }

        if (tab.getInactiveSelectedTitleColor() == 0) {
            tab.setInactiveSelectedTitleColor(tabTitleColor_selectedInactive);
        }

        if (tab.getIconColor() == 0) {
            tab.setIconColor(tabIconColor);
        }

        if (tab.getSelectedIconColor() == 0) {
            tab.setSelectedIconColor(tabIconColor_selected);
        }

        if (tab.getInactiveSelectedIconColor() == 0) {
            tab.setInactiveSelectedIconColor(tabIconColor_selectedInactive);
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

            if (tab.getListItemTitleColor() == 0) {
                tab.setListItemTitleColor(tabListItemTitleColor);
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
     * Sets background color for all tabs.
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabBackgroundColor(int color) {
        tabBackgroundColor = color;
        return this;
    }

    /**
     * Sets background color for selected tab.
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setSelectedTabBackgroundColor(int color) {
        tabBackgroundColor_selected = color;
        return this;
    }

    /**
     * Sets background color for inactive selected tab
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setInactiveSelectedTabBackgroundColor(int color) {
        tabBackgroundColor_selectedInactive = color;
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
     * Sets title color for all tabs.
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabTitleColor(int color) {
        tabTitleColor = color;
        return this;
    }

    /**
     * Sets title color for the selected tab.
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setSelectedTabTitleColor(int color) {
        tabTitleColor_selected = color;
        return this;
    }

    /**
     * Sets title color of the inactive selected tab .
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setInactiveSelectedTabTitleColor(int color) {
        tabTitleColor_selectedInactive = color;
        return this;
    }

    /**
     * Sets tab icon color. (Tint ImageView)
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabIconColor(int color) {
        tabIconColor = color;
        return this;
    }

    /**
     * Sets selected tab icon color.
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setSelectedTabIconColor(int color) {
        tabIconColor_selected = color;
        return this;
    }

    /**
     * Sets inactive selected tab icon color.
     * (active tab, but temporarily inactive because another tab is clicked and the drawer opened).
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setInactiveSelectedTabIconColor(int color) {
        tabIconColor_selectedInactive = color;
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
     * Sets tab item list text color.
     *
     * @param color the color
     * @return the TabDrawerData
     */
    public TabDrawerData setTabListItemTitleColor(int color) {
        tabListItemTitleColor = color;
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
