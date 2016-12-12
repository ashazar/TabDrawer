package com.ashazar.tabdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashazar.tabdrawer.model.Tab;
import com.ashazar.tabdrawer.model.TabArray;
import com.ashazar.tabdrawer.model.TabDetail;

import java.util.ArrayList;

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

    private final int TAB_BAR_POSITION_BOTTOM = 0;
    private final int TAB_BAR_POSITION_TOP = 1;
    private int tabBarPosition;

    // in px
    private int tabBarHeight = 0;
    private int tabListContainerHeight = 0;

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
        tabBarHeight = tabDrawerLayout.getLayoutHeight_tabBar();
        tabListContainerHeight = tabDrawerLayout.getLayoutHeight_ListContainer();

        tabDrawerLayout.removeAllViews();

        if (tabBarPosition == TAB_BAR_POSITION_BOTTOM) {
            prepareTabContainer();
            prepareTabListContainer();
            tabDrawerLayout.setTranslationY(tabListContainerHeight);
        }
        else if (tabBarPosition == TAB_BAR_POSITION_TOP) {
            prepareTabListContainer();
            prepareTabContainer();
            tabDrawerLayout.setTranslationY(0 - tabListContainerHeight);
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
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, tabBarHeight));

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
        tabLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
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
        tabListContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth() * tabCount, tabListContainerHeight));
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
        container.setLayoutParams(new RelativeLayout.LayoutParams(getScreenWidth(), RelativeLayout.LayoutParams.MATCH_PARENT));

        if (!tabArray.getTab(tabPos).hasItems()) return container;

        ListView listView = new ListView(context);
        listView.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth(), LinearLayout.LayoutParams.MATCH_PARENT));
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
        if (drawerOpen)
            tabListContainer.animate().translationX(0 - (getScreenWidth() * tabPos));
        else
            tabListContainer.setTranslationX(0 - (getScreenWidth() * tabPos));
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

    private int getScreenWidth() {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    public boolean isDrawerOpen() {
        return drawerOpen;
    }

    private void openDrawer() {
        drawerOpen = true;
        tabDrawerLayout.animate().translationY(0);
    }

    public void closeDrawer() {
        if (!isDrawerOpen()) return;

        int height = 0;
        if (tabBarPosition == TAB_BAR_POSITION_BOTTOM)
            height = tabListContainerHeight;
        else if (tabBarPosition == TAB_BAR_POSITION_TOP)
            height = 0 - tabListContainerHeight;

        refreshTabBar(currentSelectedTabPos);
        tabDrawerLayout.animate()
                .translationY(height)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        drawerOpen = false;
                        tempSelectedTabPos = -1;
                    }
                });
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
