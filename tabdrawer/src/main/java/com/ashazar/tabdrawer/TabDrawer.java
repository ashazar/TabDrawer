package com.ashazar.tabdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashazar.tabdrawer.model.Tab;
import com.ashazar.tabdrawer.model.TabDrawerData;
import com.ashazar.tabdrawer.model.TabListItem;

/**
 * TabDrawer
 * Navigation Tab Drawer for Android
 * Alternative to Navigation Drawer (Hamburger Menu)
 *
 * @author Serdar Hazar (ashazar) https://github.com/ashazar
 * @version 1.1.0  Created by Serdar Hazar on 26/04/16.
 */
public class TabDrawer implements View.OnClickListener, GridView.OnItemClickListener {
    /**
     * Context and the Activity of TabDrawer is being called.
     */
    private Context context;
    private Activity activity;


    /**
     * Main TabDrawerLayout
     */
    private TabDrawerLayout tabDrawerLayout;

    /**
     * Main container for Tabs.
     * One of the 2 child views of TabDrawerLayout
     */
    private LinearLayout tabContainer;

    /**
     * Main container of the Tabs' item lists.
     * One of the 2 child views of TabDrawerLayout
     */
    private LinearLayout tabListContainer;

    /**
     * Position of the TabBar.
     */
    private final int TAB_BAR_POSITION_TOP = 0;
    private final int TAB_BAR_POSITION_BOTTOM = 1;
    private final int TAB_BAR_POSITION_LEFT = 2;
    private final int TAB_BAR_POSITION_RIGHT = 3;
    private int tabBarPosition;

    /**
     * size in px
     * size = height for Bottom & Top; tabBarHeight and tabListContainerHeight
     * size = width  for Left & Right; tabBarWidth and tabListContainerWidth
     */
    private int tabBarSize = 0;
    private int tabListContainerSize = 0;


    /**
     * TabDrawerData and Selected Tab & Item positions
     */
    private TabDrawerData tabDrawerData;
    private int tabCount;
    private int tempSelectedTabPos = -1;
    private static int currentSelectedTabPos = -1;
    private static int currentSelectedTabItemPos = -1;
    private static int previousSelectedTabItemPos = -1;
    private static int previousSelectedTabWithListPos = -1;


    /**
     * TabDrawer's status
     */
    private boolean drawerOpen = false;


    /**
     * Instantiates a new Tab drawer.
     *
     * @param context           the context
     * @param activity          the activity
     * @param tabDrawerLayoutId Layout Resource Id of TabDrawerLayout
     * @param tabDrawerData     the tab array (List for tabs and their item lists)
     */
    protected TabDrawer(Context context, Activity activity, int tabDrawerLayoutId, TabDrawerData tabDrawerData) {
        this.context = context;
        this.activity = activity;

        View rootView = activity.findViewById(android.R.id.content);
        tabDrawerLayout = (TabDrawerLayout) rootView.findViewById(tabDrawerLayoutId);

        this.tabDrawerData = tabDrawerData;
        tabCount = tabDrawerData.getTabCount();
    }

    /**
     * Initialize TabDrawer.
     * <p>
     * TabBar and TabListContainer prepared here.
     * Initial selected Tab and TabList item marked as selected.
     */
    public void initialize() {
        // TODO: Currently, Root View is RelativeLayout. Haven't checked others yet...
        tabBarPosition = tabDrawerLayout.getTabBarPosition();
        tabBarSize = tabDrawerLayout.getLayoutSize_tabBar();
        tabListContainerSize = tabDrawerLayout.getLayoutSize_ListContainer();

        tabDrawerLayout.removeAllViews();
        if (tabBarPositionBottomOrRight()) {
            prepareTabContainer();
            prepareTabListContainer();

            if (tabDrawerData.hasDrawerForList()) {
                if (tabBarPosition == TAB_BAR_POSITION_BOTTOM) {
                    tabDrawerLayout.setTranslationY(tabListContainerSize);
                }
                else {
                    tabDrawerLayout.setTranslationX(tabListContainerSize);
                }
            }
        }
        else {
            prepareTabListContainer();
            prepareTabContainer();

            if (tabDrawerData.hasDrawerForList()) {
                if (tabBarPosition == TAB_BAR_POSITION_TOP) {
                    tabDrawerLayout.setTranslationY(0 - tabListContainerSize);
                }
                else {
                    tabDrawerLayout.setTranslationX(0 - tabListContainerSize);
                }
            }
        }

        if (currentSelectedTabPos == -1) {
            currentSelectedTabPos = (tabDrawerLayout.getDefaultSelectedTab() > (tabCount-1)) ? 0 : tabDrawerLayout.getDefaultSelectedTab();
        }
        if (currentSelectedTabItemPos == -1) {
            currentSelectedTabItemPos = (tabDrawerData.getTab(currentSelectedTabPos).hasItems()
                                        &&  (tabDrawerLayout.getDefaultSelectedTabItem() <= tabDrawerData.getTab(currentSelectedTabPos).getTabItemList().size()-1))
                                        ? tabDrawerLayout.getDefaultSelectedTabItem() : 0;

            if (tabDrawerData.getTab(currentSelectedTabPos).hasItems()) {
                previousSelectedTabWithListPos = currentSelectedTabPos;
            }
        }

        refreshTabBar(currentSelectedTabPos);
        refreshTabLists(currentSelectedTabPos, currentSelectedTabItemPos);

        tabDrawerLayout.setOnClickListener(this);
    }

    /**
     * Prepare TabBar (TabContainer).
     * This LinearLayout view will be main container for Tabs. Will be one of the 2 main child views of TabDrawerLayout.
     *
     * Add each Tab to TabBar.
     */
    private void prepareTabContainer() {
        tabContainer = new LinearLayout(context);
        if (tabBarPositionTopOrBottom()) {
            tabContainer.setOrientation(LinearLayout.HORIZONTAL);
            tabContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tabBarSize));
        }
        else {
            tabContainer.setOrientation(LinearLayout.VERTICAL);
            tabContainer.setLayoutParams(new LinearLayout.LayoutParams(tabBarSize, LinearLayout.LayoutParams.MATCH_PARENT));
        }

        tabDrawerLayout.addView(tabContainer);

        // Add Tabs
        for (int i = 0; i < tabCount; i++) {
            tabContainer.addView(prepareTab(i));
        }
    }

    /**
     * Prepare view of one tab.
     *
     * @param pos Tab position. Get details (title, image, etc.) from TabDrawerData
     * @return RelativeLayout view (Tab view)
     */
    private RelativeLayout prepareTab(int pos) {
        RelativeLayout tabLayout = null;
        ImageView icon = null;
        TextView title = null;

        boolean hasCustomTabLayout = false;

        Tab tab = tabDrawerData.getTab(pos);

        if (tab.getCustomTabLayoutResourceId() != 0) {
            tabLayout = (RelativeLayout) LayoutInflater.from(context).inflate(tab.getCustomTabLayoutResourceId(), tabDrawerLayout, false);
            hasCustomTabLayout = true;
        }
        else {
            tabLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.tabdrawer_tab_item, tabDrawerLayout, false);
            tabLayout.setPadding(tabDrawerLayout.getTabPaddingLeft(), tabDrawerLayout.getTabPaddingTop(), tabDrawerLayout.getTabPaddingRight(), tabDrawerLayout.getTabPaddingBottom());
            tabLayout.setBackgroundColor(tab.getBackgroundColor());
        }
        tabLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        icon = (ImageView) tabLayout.findViewById(R.id.tab_icon);
        title = (TextView) tabLayout.findViewById(R.id.tab_title);

        if (tabBarPositionTopOrBottom()) {
            tabLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        }
        else {
            tabLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        }


        if (tab.getIconImage() != 0) {
            icon.setImageResource(tab.getIconImage());
            icon.setColorFilter(tab.getIconColor());
            icon.setId(1100 + pos);

            if (!hasCustomTabLayout) {
                if (tab.getTitle().isEmpty()) {
                    title.setVisibility(View.GONE);
                    icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
                else {
                    icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6));
                }
            }
        }

        if (!tab.getTitle().isEmpty()) {
            title.setText(tab.getTitle());
            title.setTextColor(tab.getTitleColor());
            title.setTextSize(tab.getTitleSize());
            title.setTypeface(tab.getTitleFont());

            if (!hasCustomTabLayout) {
                title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

                if (tab.getIconImage() != 0) {
                    title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 4));
                    icon.setContentDescription(tab.getTitle());
                }
                else {
                    icon.setVisibility(View.GONE);
                    title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
            }

            title.setId(1200 + pos);
        }

        tabLayout.setId(1000 + pos);
        tabLayout.setClickable(true);
        tabLayout.setOnClickListener(this);

        return tabLayout;
    }

    /**
     * Prepare TabList Container.
     * This LinearLayout view will be main container of List views. Will be one of the 2 main child views of TabDrawerLayout.
     *
     * Add each Tab's List containers to this view.
     */
    private void prepareTabListContainer() {
        if (!tabDrawerData.hasDrawerForList()) {
            return;
        }

        tabListContainer = new LinearLayout(context);

        if (tabBarPositionTopOrBottom()) {
            tabListContainer.setOrientation(LinearLayout.HORIZONTAL);
            tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth() * tabCount, tabListContainerSize));
        }
        else {
            tabListContainer.setOrientation(LinearLayout.VERTICAL);
            tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(tabListContainerSize, getScreenHeight() * tabCount));
        }

        for (int i = 0; i < tabCount; i++) {
            RelativeLayout layout = prepareItemListContainerView(i);
            tabListContainer.addView(layout);
        }

        tabDrawerLayout.addView(tabListContainer);
    }

    /**
     * Prepare view of one tab's item list container.
     *
     * @param tabPos Tab position. Get item list from TabDrawerData
     * @return RelativeLayout view (Tab's item list container). RelativeLayout is chosen for future flexibility needs.
     */
    private RelativeLayout prepareItemListContainerView(int tabPos) {
        RelativeLayout container;
        GridView gridView;

        Tab tab = tabDrawerData.getTab(tabPos);

        if (tab.getCustomDrawerLayoutResourceId() != 0) {
            container = (RelativeLayout) LayoutInflater.from(context).inflate(tab.getCustomDrawerLayoutResourceId(), tabListContainer, false);
        }
        else {
            container = new RelativeLayout(context);
            container.setBackgroundColor(tab.getSelectedBackgroundColor());
            container.setPadding(tabDrawerLayout.getDrawerPaddingLeft(), tabDrawerLayout.getDrawerPaddingTop(), tabDrawerLayout.getDrawerPaddingRight(), tabDrawerLayout.getDrawerPaddingBottom());
        }

        if (tabBarPositionTopOrBottom()) {
            container.setLayoutParams(new RelativeLayout.LayoutParams(getScreenWidth(), RelativeLayout.LayoutParams.MATCH_PARENT));
        }
        else {
            container.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, getScreenHeight()));
        }

        if (!tab.hasItems()) {
            return container;
        }

        if (tab.getCustomDrawerGridViewId() != 0  &&  tab.getCustomDrawerLayoutResourceId() != 0) {
            gridView = (GridView) container.findViewById(tab.getCustomDrawerGridViewId());
        }
        else {
            gridView = new GridView(context);
            gridView.setPadding(tabDrawerLayout.getTabListPaddingLeft(), tabDrawerLayout.getTabListPaddingTop(), tabDrawerLayout.getTabListPaddingRight(), tabDrawerLayout.getTabListPaddingBottom());
            gridView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            container.addView(gridView);
        }

        gridView.setId(10000 + tabPos);
        gridView.setOnItemClickListener(this);

        if (gridView.getNumColumns() != tab.getDrawerListColumnNumber()  &&  tab.getDrawerListColumnNumber() > 0) {
            gridView.setNumColumns(tab.getDrawerListColumnNumber());
        }

        gridView.setAdapter(setDrawerListAdapter(tabPos));

        container.requestLayout();
        return container;
    }

    /**
     * Custom Array Adapter
     *
     * @param tabPos Tab Psition
     * @return ArrayAdapter
     */
    private ArrayAdapter<TabListItem> setDrawerListAdapter(final int tabPos) {
        final Tab tab = tabDrawerData.getTab(tabPos);
        final int listItemLayoutResourceId;
        final boolean customItemLayout;

        if (tab.getCustomDrawerListItemLayoutResourceId() != 0) {
            listItemLayoutResourceId = tab.getCustomDrawerListItemLayoutResourceId();
            customItemLayout = true;
        }
        else {
            listItemLayoutResourceId = R.layout.tabdrawer_tab_list_item;
            customItemLayout = false;
        }

        return new ArrayAdapter<TabListItem>(context, listItemLayoutResourceId, tab.getTabItemList()) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(listItemLayoutResourceId, parent, false);
                }

                String title = "";
                int imgDrawableId = 0;
                ImageView imageView = null;
                TextView titleView = null;

                TabListItem item = getItem(position);
                title = item.getTitle();
                imgDrawableId = item.getDrawableId();
                boolean isSelected = item.isSelected();


                if (!customItemLayout) {
                    imageView = (ImageView) convertView.findViewById(R.id.list_item_img);
                    titleView = (TextView) convertView.findViewById(R.id.list_item_title);
                    if (imgDrawableId == -1) {
                        imageView.setVisibility(View.GONE);
                    }
                    if (title.isEmpty()) {
                        titleView.setVisibility(View.GONE);
                    }
                }
                else {
                    if (imgDrawableId != -1) {
                        imageView = (ImageView) convertView.findViewById(R.id.list_item_img);
                    }
                    if (!title.isEmpty()) {
                        titleView = (TextView) convertView.findViewById(R.id.list_item_title);
                    }
                }

                if (imageView != null  &&  imgDrawableId != -1) {
                    imageView.setImageResource(imgDrawableId);
                }
                if (titleView != null  &&  !title.isEmpty()) {
                    titleView.setText(title);
                    if (tab.getListItemTitleFont() != null) {
                        titleView.setTypeface(tab.getListItemTitleFont());
                    }

                    if (imageView != null  &&  imgDrawableId != -1) {
                        imageView.setContentDescription(title);
                    }
                }

                if (isSelected) {
                    setSelectedListItemView(tabPos, position, convertView, imageView, titleView);
                }
                else {
                    setUnselectedListItemView(tabPos, position, convertView, imageView, titleView);
                }

                if (imageView != null) { imageView.requestLayout(); }
                if (titleView != null) { titleView.requestLayout(); }

                return convertView;
            }
        };
    }

    /**
     * Refresh Tab Bar.
     * Change views according to Tab's "selected" status.
     *
     * @param tabPos Clicked Tab position. This position does not mainly represent if the Tab is really "selected". (eg. temporarily selected, when it is clicked to open the TabDrower.)
     */
    private void refreshTabBar(int tabPos) {
        for (int i = 0; i < tabCount; i++) {
            Tab tab = tabDrawerData.getTab(i);

            RelativeLayout tabLayout = (RelativeLayout) tabContainer.getChildAt(i);
            ImageView iconView = null;
            TextView titleView = null;
            RelativeLayout drawerLayout = null;

            if (tab.getIconImage() != 0) {
                iconView = (ImageView) tabLayout.findViewById(1100 + i);
            }

            if (!tab.getTitle().isEmpty()) {
                titleView = (TextView) tabLayout.findViewById(1200 + i);
            }

            if (i == tabPos) {
                setSelectedTabView(tabLayout, iconView, titleView, i);

                if (iconView != null) { iconView.requestLayout(); }
                if (titleView != null) { titleView.requestLayout(); }
                tabLayout.requestLayout();
            }
            else if (i == currentSelectedTabPos) {
                setInactiveSelectedTabView(tabLayout, iconView, titleView, i);

                if (iconView != null) { iconView.requestLayout(); }
                if (titleView != null) { titleView.requestLayout(); }
                tabLayout.requestLayout();
            }
            else {
                setUnselectedTabView(tabLayout, iconView, titleView, i);

                if (iconView != null) { iconView.requestLayout(); }
                if (titleView != null) { titleView.requestLayout(); }
                tabLayout.requestLayout();
            }
        }
    }

    /**
     * Update TabList Container.
     * <p>
     * When the TabDrawer is open, changes the content (manipulates the view) of the drawer, according to the clicked Tab.
     *
     * @param tabPos clicked Tab position
     */
    private void updateTabListContainer(int tabPos) {
        if (!tabDrawerData.hasDrawerForList()) {
            return;
        }

        if (tabBarPositionTopOrBottom()) {
            if (drawerOpen) {
                tabListContainer.animate().translationX(0 - (getScreenWidth() * tabPos));
            }
            else {
                tabListContainer.setTranslationX(0 - (getScreenWidth() * tabPos));
            }
        }
        else {
            if (drawerOpen) {
                tabListContainer.animate().translationY(0 - (getScreenHeight() * tabPos));
            }
            else {
                tabListContainer.setTranslationY(0 - (getScreenHeight() * tabPos));
            }
        }
    }

    /**
     * Refresh Tab List Items, according to their "selected" status.
     * Previously selected ones will be updated as "not selected" as well.
     *
     * @param tabPos Position of the newly clicked Tab.
     * @param tabItemPos Position of the newly clicked item.
     */
    private void refreshTabLists(int tabPos, int tabItemPos) {
        if (!tabDrawerData.hasDrawerForList()  ||  !tabDrawerData.getTab(tabPos).hasItems()) {
            return;
        }

        if (previousSelectedTabItemPos > -1  &&  previousSelectedTabWithListPos > -1) {
            tabDrawerData.getTab(previousSelectedTabWithListPos).getTabItemList().get(previousSelectedTabItemPos).setSelected(false);
            GridView prevGridView = (GridView) tabListContainer.findViewById(10000 + previousSelectedTabWithListPos);

            prevGridView.setAdapter(setDrawerListAdapter(previousSelectedTabWithListPos));
        }


        tabDrawerData.getTab(tabPos).getTabItemList().get(tabItemPos).setSelected(true);
        GridView gridView = (GridView) tabListContainer.findViewById(10000 + tabPos);
        gridView.setAdapter(setDrawerListAdapter(tabPos));

        previousSelectedTabWithListPos = tabPos;
        previousSelectedTabItemPos = tabItemPos;

        currentSelectedTabPos = tabPos;
        currentSelectedTabItemPos = tabItemPos;
    }

    /**
     * Get Screen Size
     *
     * @return Point; x and y properties will be retrieved for Width and Height.
     */
    private Point getScreenSize() {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }

    /**
     * Get Screen Width. ( From getScreensize() )
     *
     * @return int Screen Width
     */
    private int getScreenWidth() { return getScreenSize().x; }

    /**
     * Get Screen Height. ( From getScreensize() )
     *
     * @return int Screen Height
     */
    private int getScreenHeight() { return getScreenSize().y; }


    /**
     * Is drawer open boolean.
     *
     * @return the boolean
     */
    public boolean isDrawerOpen() { return drawerOpen; }

    /**
     * Is TabBarPosition Top or Bottom
     *
     * @return the boolean
     */
    private boolean tabBarPositionTopOrBottom() {
        return tabBarPosition == TAB_BAR_POSITION_TOP  ||  tabBarPosition == TAB_BAR_POSITION_BOTTOM;
    }

    /**
     * Is TabBarPosition Bottom or Right
     *
     * @return the boolean
     */
    private boolean tabBarPositionBottomOrRight() {
        return tabBarPosition == TAB_BAR_POSITION_BOTTOM  ||  tabBarPosition == TAB_BAR_POSITION_RIGHT;
    }

    /**
     * Open TabDrawer
     */
    private void openDrawer() {
        drawerOpen = true;
        if (tabBarPositionTopOrBottom()) {
            tabDrawerLayout.animate().translationY(0);
        }
        else {
            tabDrawerLayout.animate().translationX(0);
        }
    }

    /**
     * Close drawer.
     */
    public void closeDrawer() {
        if (!isDrawerOpen()) return;

        int size = 0;
        if (tabBarPositionBottomOrRight()) {
            size = tabListContainerSize;
        }
        else {
            size = 0 - tabListContainerSize;
        }

        refreshTabBar(currentSelectedTabPos);

        if (tabBarPositionTopOrBottom()) {
            tabDrawerLayout.animate()
                    .translationY(size)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            drawerOpen = false;
                            tempSelectedTabPos = -1;
                        }
                    });
        }
        else {
            tabDrawerLayout.animate()
                    .translationX(size)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            drawerOpen = false;
                            tempSelectedTabPos = -1;
                        }
                    });
        }
    }


    /**
     * Get Clicks for Tabs.
     * For internal use only.
     *
     * @param v View of the clicked item
     */
    @Override
    public void onClick(View v) {
        int clickedId = v.getId();
        int clickedTabPos;

        if (clickedId >= 1000 && clickedId < (1000 + tabCount)) {
            clickedTabPos = clickedId - 1000;
        }
        else {
            refreshTabBar(tempSelectedTabPos);
            if (isDrawerOpen()) {
                closeDrawer();
            }
            return;
        }

        if (!tabDrawerData.getTab(clickedTabPos).hasItems()) {
            if (clickedTabPos != currentSelectedTabPos) {
                currentSelectedTabPos = clickedTabPos;
                onTabDrawerClicked(currentSelectedTabPos, 0);
            }
            refreshTabBar(currentSelectedTabPos);
            closeDrawer();
            return;
        }

        if (clickedTabPos == tempSelectedTabPos) {
            closeDrawer();
            return;
        }

        tempSelectedTabPos = clickedTabPos;
        refreshTabBar(tempSelectedTabPos);
        updateTabListContainer(tempSelectedTabPos);

        if (!isDrawerOpen()) {
            openDrawer();
        }
    }

    /**
     * Get clicks for TabList items. (inside drawer)
     * For internal use only.
     *
     * @param adapterView parent
     * @param view view
     * @param i Clicked item Position
     * @param l Clicked item id
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (previousSelectedTabWithListPos == tempSelectedTabPos  &&  previousSelectedTabItemPos == i) {
            closeDrawer();
            return;
        }

        refreshTabLists(tempSelectedTabPos, i);
        closeDrawer();
        onTabDrawerClicked(currentSelectedTabPos, i);
    }


    /**
     ***** Methods for overriding *****
     */

    /**
     * On tab drawer clicked. (Tab or TabListItem)
     * This will be overridden to get clicked positions.
     * <p>
     * Only new clicks will be passed through.
     * Same position clicks (Tab or Item), or clicks to open/close TabDrawer will be ignored.
     *
     * @param tabPosition  the tab position
     * @param itemPosition the item position (This will be 0, if the selected Tab has no list items.)
     */
    public void onTabDrawerClicked(int tabPosition, int itemPosition) { }


    /**
     * Sets the views of default(unselected) tab/icon/title.
     * <p>
     * If other than color, background color, etc. needed;
     * by overriding this method, you can modify the views of entire layout.
     *
     * @param tabLayout   Tab Layout itself; RelativeLayout
     * @param iconView    Icon (Image); ImageView
     * @param titleView   Title; TextView
     * @param tabPosition Tab Position, to modify the tab you want; int
     */
    public void setUnselectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
        Tab tab = tabDrawerData.getTab(tabPosition);

        if (!tab.getCustomTabViewSettingsStatus()) {
            tabLayout.setBackgroundColor(tab.getBackgroundColor());
            if (iconView != null && tab.getIconImage() != 0) {
                iconView.setImageResource(tab.getIconImage());
                if (tab.getIconColor() != 0) {
                    iconView.setColorFilter(tab.getIconColor());
                }

                if (tab.getAnimateScaleIconWhenSelected()) {
                    iconView.animate()
                            .scaleY(1)
                            .scaleX(1);
                }
            }
            if (titleView != null && !tab.getTitle().isEmpty()) {
                if (tab.getSelectedTitleColor() != 0) {
                    titleView.setTextColor(tab.getTitleColor());
                }

                if (tab.getBoldTitleWhenSelected()) {
                    titleView.setTypeface(tab.getTitleFont(), Typeface.NORMAL);
                }
            }
        }

    }

    /**
     * Sets the views of selected tab/icon/title/drawer.
     * <p>
     * If other than color, background color, etc. needed;
     * by overriding this method, you can modify the views of entire layout.
     *
     * @param tabLayout   Tab Layout itself; RelativeLayout
     * @param iconView    Icon (Image); ImageView
     * @param titleView   Title; TextView
     * @param tabPosition Tab Position, to modify the tab you want; int
     */
    public void setSelectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
        Tab tab = tabDrawerData.getTab(tabPosition);

        if (!tab.getCustomTabViewSettingsStatus()) {
            tabLayout.setBackgroundColor(tab.getSelectedBackgroundColor());
            if (iconView != null && tab.getIconImage() != 0) {
                iconView.setImageResource(tab.getSelectedIconImage());

                if (tab.getSelectedIconColor() != 0) {
                    iconView.setColorFilter(tab.getSelectedIconColor());
                }

                if (tab.getAnimateScaleIconWhenSelected()) {
                    iconView.animate()
                            .scaleY(tab.getIconScaleValueWhenSelected())
                            .scaleX(tab.getIconScaleValueWhenSelected());
                }
            }

            if (titleView != null && !tab.getTitle().isEmpty()) {
                if (tab.getSelectedTitleColor() != 0) {
                    titleView.setTextColor(tab.getSelectedTitleColor());
                }

                if (tab.getBoldTitleWhenSelected()) {
                    titleView.setTypeface(tab.getTitleFont(), Typeface.BOLD);
                }
            }
        }
    }

    /**
     * Sets the views of inactive selected tab/icon/title.
     * Inactive Selected:
     * When the tab is selected, but another tab's drawer is open
     * this tab becomes selected but inactive at the moment.
     * <p>
     * If other than color, background color, etc. needed;
     * by overriding this method, you can modify the views of entire layout.
     *
     * @param tabLayout   Tab Layout itself; RelativeLayout
     * @param iconView    Icon (Image); ImageView
     * @param titleView   Title; TextView
     * @param tabPosition Tab Position, to modify the tab you want; int
     */
    public void setInactiveSelectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
        Tab tab = tabDrawerData.getTab(tabPosition);

        if (!tab.getCustomTabViewSettingsStatus()) {
            tabLayout.setBackgroundColor(tab.getInactiveSelectedBackgroundColor());
            if (iconView != null && tab.getIconImage() != 0) {
                iconView.setImageResource(tab.getInactiveSelectedIconImage());

                if (tab.getInactiveSelectedIconColor() != 0) {
                    iconView.setColorFilter(tab.getInactiveSelectedIconColor());
                }

                if (tab.getAnimateScaleIconWhenSelected()) {
                    iconView.animate().scaleY(1).scaleX(1);
                }
            }

            if (titleView != null && !tab.getTitle().isEmpty() && tab.getInactiveSelectedTitleColor() != 0) {
                titleView.setTextColor(tab.getInactiveSelectedTitleColor());
            }
        }
    }

    /**
     * Sets the Custom drawer list item view
     *
     * @param tabPosition  Tab Position of the drawer list
     * @param itemPosition current item position of the list (from GetView of the adapter)
     * @param view         current drawer list item's view (from GetView)
     * @param iconView     current drawer list item's icon view; ImageView (from GetView)
     * @param titleView    current drawer list item's title view; TextView (from GetView)
     */
    public void setUnselectedListItemView(int tabPosition, int itemPosition, View view, ImageView iconView, TextView titleView) {
        Tab tab = tabDrawerData.getTab(tabPosition);

        if (!tab.getCustomListAdapterViewSettingsStatus()) {
            if (iconView != null) {
                iconView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tab.getListItemTitleSize() + 8, context.getResources().getDisplayMetrics());
            }

            if (titleView != null) {
                titleView.setTextColor(tab.getListItemTitleColor());
                titleView.setTypeface(tab.getListItemTitleFont(), Typeface.NORMAL);
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tab.getListItemTitleSize());
            }
        }
    }

    /**
     * Sets the Custom drawer list item view, when it is selected
     *
     * @param tabPosition  Tab Position of the drawer list
     * @param itemPosition current item position of the list (from GetView of the adapter)
     * @param view         current drawer list item's view (from GetView)
     * @param iconView     current drawer list item's icon view; ImageView (from GetView)
     * @param titleView    current drawer list item's title view; TextView (from GetView)
     */
    public void setSelectedListItemView(int tabPosition, int itemPosition, View view, ImageView iconView, TextView titleView) {
        Tab tab = tabDrawerData.getTab(tabPosition);

        if (!tab.getCustomListAdapterViewSettingsStatus()) {
            if (iconView != null) {
                iconView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tab.getListItemTitleSize() + 10, context.getResources().getDisplayMetrics());
            }

            if (titleView != null) {
                titleView.setTextColor(tab.getSelectedListItemTitleColor());
                titleView.setTypeface(tab.getListItemTitleFont(), Typeface.BOLD);
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, tab.getListItemTitleSize() + 1);
            }
        }
    }

}
