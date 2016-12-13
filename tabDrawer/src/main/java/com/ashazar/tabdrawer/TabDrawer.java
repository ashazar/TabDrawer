package com.ashazar.tabdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashazar.tabdrawer.model.Tab;
import com.ashazar.tabdrawer.model.TabArray;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabDrawer implements View.OnClickListener, ListView.OnItemClickListener {
    private Context context;
    private Activity activity;

    private TabDrawerLayout tabDrawerLayout;
    private LinearLayout tabContainer;
    private LinearLayout tabListContainer;

    private final float INACTIVE_SELECTED_TAB_ALPHA_VALUE = 0.85f;

    private final int TAB_BAR_POSITION_TOP = 0;
    private final int TAB_BAR_POSITION_BOTTOM = 1;
    private final int TAB_BAR_POSITION_LEFT = 2;
    private final int TAB_BAR_POSITION_RIGHT = 3;
    private int tabBarPosition;

    // size = height for Bottom & Top; size = width for Left & Right  (size in px)
    private int tabBarSize = 0;
    private int tabListContainerSize = 0;

    private TabArray tabArray;
    private int tabCount;
    private static int currentSelectedTabPos = -1;
    private static int currentSelectedTabItemPos = -1;
    private int tempSelectedTabPos = -1;
    private static int previousSelectedTabItemPos = -1;
    private static int previousSelectedTabWithListPos = -1;

    private boolean drawerOpen = false;


    protected TabDrawer(Context context, Activity activity, int tabDrawerLayoutId, TabArray tabArray) {
        this.context = context;
        this.activity = activity;

        View rootView = activity.findViewById(android.R.id.content);
        tabDrawerLayout = (TabDrawerLayout) rootView.findViewById(tabDrawerLayoutId);

        this.tabArray = tabArray;
        tabCount = tabArray.size();
    }

    // TODO: Currently, Root View is RelativeLayout. Haven't checked others yet...
    public void initialize() {
        tabBarPosition = tabDrawerLayout.getTabBarPosition();
        if (tabBarPositionTopOrBottom()) {
            tabBarSize = tabDrawerLayout.getLayoutHeight_tabBar();
            tabListContainerSize = tabDrawerLayout.getLayoutHeight_ListContainer();
        }
        else {
            tabBarSize = tabDrawerLayout.getLayoutWidth_tabBar();
            tabListContainerSize = tabDrawerLayout.getLayoutWidth_ListContainer();
        }

        tabDrawerLayout.removeAllViews();
        if (tabBarPositionBottomOrRight()) {
            prepareTabContainer();
            prepareTabListContainer();
            if (tabBarPosition == TAB_BAR_POSITION_BOTTOM)
                tabDrawerLayout.setTranslationY(tabListContainerSize);
            else
                tabDrawerLayout.setTranslationX(tabListContainerSize);
        }
        else {
            prepareTabListContainer();
            prepareTabContainer();
            if (tabBarPosition == TAB_BAR_POSITION_TOP)
                tabDrawerLayout.setTranslationY(0 - tabListContainerSize);
            else
                tabDrawerLayout.setTranslationX(0 - tabListContainerSize);
        }

        if (currentSelectedTabPos == -1) {
            currentSelectedTabPos = (tabDrawerLayout.getDefaultSelectedTab() > (tabCount-1)) ? 0 : tabDrawerLayout.getDefaultSelectedTab();
        }
        if (currentSelectedTabItemPos == -1) {
            currentSelectedTabItemPos = (tabArray.getTab(currentSelectedTabPos).hasItems()
                                        &&  (tabDrawerLayout.getDefaultSelectedTabItem() <= tabArray.getTab(currentSelectedTabPos).getTabItemList().size()-1))
                                        ? tabDrawerLayout.getDefaultSelectedTabItem() : 0;

            if (tabArray.getTab(currentSelectedTabPos).hasItems())
                previousSelectedTabWithListPos = currentSelectedTabPos;
        }

        refreshTabBar(currentSelectedTabPos);
        refreshTabLists(currentSelectedTabPos, currentSelectedTabItemPos);

        tabDrawerLayout.setOnClickListener(this);
    }

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

    // TODO: Prepare an XML Tab Layout, and give the customization to developer.
    private LinearLayout prepareTab(int pos) {
        Tab tab = tabArray.getTab(pos);

        LinearLayout tabLayout = new LinearLayout(context);
        tabLayout.setOrientation(LinearLayout.VERTICAL);

        if (tabBarPositionTopOrBottom())
            tabLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        else
            tabLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

        tabLayout.setPadding(tabDrawerLayout.getTabPaddingLeft(), tabDrawerLayout.getTabPaddingTop(), tabDrawerLayout.getTabPaddingRight(), tabDrawerLayout.getTabPaddingBottom());
        tabLayout.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor());

        if (tab.getDrawableId() != 0  &&  tab.getTitle() != null) tabLayout.setWeightSum(10);

        tabLayout.setId(1000 + pos);
        tabLayout.setClickable(true);

        if (tab.getDrawableId() != 0) {
            ImageView icon = new ImageView(context);
            icon.setImageResource(tab.getDrawableId());

            if (tab.getTitle() == null)
                icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            else
                icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 6));

            icon.setId(1100 + pos);
            tabLayout.addView(icon);
        }

        if (tab.getTitle() != null) {
            TextView title = new TextView(context);

            if (tab.getDrawableId() != 0)
                title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 4));
            else
                title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            title.setText(tab.getTitle());
            title.setTextColor(tabDrawerLayout.getTabTitleColor());
            title.setTextSize(tabDrawerLayout.getTabTitleSize());

            title.setId(1200 + pos);
            tabLayout.addView(title);
        }

        tabLayout.setOnClickListener(this);

        return tabLayout;
    }

    private void prepareTabListContainer() {
        tabListContainer = new LinearLayout(context);

        if (tabBarPositionTopOrBottom()) {
            tabListContainer.setOrientation(LinearLayout.HORIZONTAL);
            tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth() * tabCount, tabListContainerSize));
        }
        else {
            tabListContainer.setOrientation(LinearLayout.VERTICAL);
            tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(tabListContainerSize, getScreenHeight() * tabCount));
        }
        tabListContainer.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor_selected());
        //tabListContainer.setPadding(tabDrawerLayout.getTabListPaddingLeft(), tabDrawerLayout.getTabListPaddingTop(), tabDrawerLayout.getTabListPaddingRight(), tabDrawerLayout.getTabListPaddingBottom());

        for (int i = 0; i < tabCount; i++) {
            RelativeLayout layout = prepareItemListContainerView(i);
            tabListContainer.addView(layout);
        }

        tabDrawerLayout.addView(tabListContainer);
    }

    private RelativeLayout prepareItemListContainerView(int tabPos) {
        RelativeLayout container = new RelativeLayout(context);
        if (tabBarPositionTopOrBottom())
            container.setLayoutParams(new RelativeLayout.LayoutParams(getScreenWidth(), RelativeLayout.LayoutParams.MATCH_PARENT));
        else
            container.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, getScreenHeight()));

        if (!tabArray.getTab(tabPos).hasItems()) return container;

        ListView listView = new ListView(context);
        listView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        listView.setPadding(tabDrawerLayout.getTabListPaddingLeft(), tabDrawerLayout.getTabListPaddingTop(), tabDrawerLayout.getTabListPaddingRight(), tabDrawerLayout.getTabListPaddingBottom());
        listView.setDividerHeight(0);
        listView.setDivider(null);
        listView.setId(10000 + tabPos);


        TabListAdapter adapter = new TabListAdapter(context, tabArray.getTab(tabPos).getTabItemList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        container.addView(listView);
        return container;
    }


    private void refreshTabBar(int tabPos) {
        for (int i = 0; i < tabCount; i++) {
            boolean hasIcon = false;
            boolean hasSelectedIcon = false;
            boolean hasTitle = false;
            ImageView icon = null;
            TextView title = null;

            Tab tab = tabArray.getTab(i);
            if (tab.getDrawableId() != 0) hasIcon = true;
            if (tab.getDrawableId_selected() != 0) hasSelectedIcon = true;
            if (tab.getTitle() != null) hasTitle = true;

            LinearLayout tabLayout = (LinearLayout) tabContainer.getChildAt(i);
            if (hasIcon)
                icon = (ImageView) tabLayout.findViewById(1100 + i);

            if (hasTitle)
                title = (TextView) tabLayout.findViewById(1200 + i);

            if (i == tabPos) {
                tabLayout.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor_selected());
                if (hasIcon  &&  hasSelectedIcon) icon.setImageResource(tab.getDrawableId_selected());
                if (hasTitle) title.setTextColor(tabDrawerLayout.getTabTitleColor_selected());
                tabLayout.setAlpha(1);
            }
            else if (i == currentSelectedTabPos) {
                tabLayout.setAlpha(INACTIVE_SELECTED_TAB_ALPHA_VALUE);
            }
            else {
                tabLayout.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor());
                if (hasIcon) icon.setImageResource(tab.getDrawableId());
                if (hasTitle) title.setTextColor(tabDrawerLayout.getTabTitleColor());
                tabLayout.setAlpha(1);
            }
        }
    }

    private void updateTabListContainer(int tabPos) {
        if (tabBarPositionTopOrBottom()) {
            if (drawerOpen)
                tabListContainer.animate().translationX(0 - (getScreenWidth() * tabPos));
            else
                tabListContainer.setTranslationX(0 - (getScreenWidth() * tabPos));
        }
        else {
            if (drawerOpen)
                tabListContainer.animate().translationY(0 - (getScreenHeight() * tabPos));
            else
                tabListContainer.setTranslationY(0 - (getScreenHeight() * tabPos));
        }
    }

    private void refreshTabLists(int tabPos, int tabItemPos) {
        TabListAdapter adapter;

        if (previousSelectedTabItemPos > -1  &&  previousSelectedTabWithListPos > -1) {
            tabArray.getTab(previousSelectedTabWithListPos).getTabItemList().get(previousSelectedTabItemPos).setSelected(false);
            ListView prevListView = (ListView) tabListContainer.findViewById(10000 + previousSelectedTabWithListPos);

            adapter = new TabListAdapter(context, tabArray.getTab(previousSelectedTabWithListPos).getTabItemList());
            prevListView.setAdapter(adapter);
        }


        tabArray.getTab(tabPos).getTabItemList().get(tabItemPos).setSelected(true);
        ListView listView = (ListView) tabListContainer.findViewById(10000 + tabPos);

        adapter = new TabListAdapter(context, tabArray.getTab(tabPos).getTabItemList());
        listView.setAdapter(adapter);


        previousSelectedTabWithListPos = tabPos;
        previousSelectedTabItemPos = tabItemPos;

        currentSelectedTabPos = tabPos;
        currentSelectedTabItemPos = tabItemPos;
    }

    private Point getScreenSize() {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }

    private int getScreenWidth() { return getScreenSize().x; }
    private int getScreenHeight() { return getScreenSize().y; }

    public boolean isDrawerOpen() { return drawerOpen; }

    private boolean tabBarPositionTopOrBottom() { return tabBarPosition == TAB_BAR_POSITION_TOP  ||  tabBarPosition == TAB_BAR_POSITION_BOTTOM; }

    private boolean tabBarPositionBottomOrRight() { return tabBarPosition == TAB_BAR_POSITION_BOTTOM  ||  tabBarPosition == TAB_BAR_POSITION_RIGHT; }

    private void openDrawer() {
        drawerOpen = true;
        if (tabBarPositionTopOrBottom())
            tabDrawerLayout.animate().translationY(0);
        else
            tabDrawerLayout.animate().translationX(0);
    }

    public void closeDrawer() {
        if (!isDrawerOpen()) return;

        int size = 0;
        if (tabBarPositionBottomOrRight())
            size = tabListContainerSize;
        else
            size = 0 - tabListContainerSize;

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


    @Override
    public void onClick(View v) {
        int clickedId = v.getId();
        int clickedTabPos;

        if (clickedId >= 1000 && clickedId < (1000 + tabCount)) {
            clickedTabPos = clickedId - 1000;
        } else {
            refreshTabBar(tempSelectedTabPos);
            if (isDrawerOpen()) closeDrawer();
            return;
        }

        if (!tabArray.getTab(clickedTabPos).hasItems()) {
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

        if (!isDrawerOpen()) openDrawer();
    }

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

    public void onTabDrawerClicked(int tabPosition, int itemPosition) { }
}
