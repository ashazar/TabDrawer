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

    // in px
    private int tabBarHeight = 0;
    private int tabListContainerHeight = 0;

    private TabArray tabArray;
    private int tabCount;
    private int currentSelectedTabPos = 0;
    private int clickedTabDetailPos = -1;
    private View previousTabDetailItemView = null;

    private boolean drawerOpen = false;


    protected TabDrawer(Context context, Activity activity, int tabDrawerLayoutId, TabArray tabArray) {
        this.context = context;
        this.activity = activity;

        View rootView = activity.findViewById(android.R.id.content);
        tabDrawerLayout = (TabDrawerLayout) rootView.findViewById(tabDrawerLayoutId);

        this.tabArray = tabArray;
        tabCount = tabArray.size();
    }

    public void initialize() {
        // TODO: just in case, set default width of tabDrawerLayout to MATCH_PARENT
        // TODO: Currently, Root View is RelativeLayout. Haven't checked others yet...

        tabBarHeight = tabDrawerLayout.getLayoutHeight_tabBar();
        tabListContainerHeight = tabDrawerLayout.getLayoutHeight_ListContainer();

        prepareTabContainer();
        addTabs();
        addTabListContainer();

        int defaultSelectedTab = (tabDrawerLayout.getDefaultSelectedTab() > tabCount) ? 0 : tabDrawerLayout.getDefaultSelectedTab() - 1;
        setSelectedTab(defaultSelectedTab);

        tabDrawerLayout.setTranslationY(tabListContainerHeight);
        tabDrawerLayout.setOnClickListener(this);
    }

    private void prepareTabContainer() {
        tabContainer = new LinearLayout(context);
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, tabBarHeight));

        tabDrawerLayout.addView(tabContainer);
    }

    private void addTabs() {
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

    private void addTabListContainer() {
        tabListContainer = new LinearLayout(context);
        tabListContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth() * tabCount, tabListContainerHeight));
        tabListContainer.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor_selected());
        //tabListContainer.setPadding(tabDrawerLayout.getTabListPaddingLeft(), tabDrawerLayout.getTabListPaddingTop(), tabDrawerLayout.getTabListPaddingRight(), tabDrawerLayout.getTabListPaddingBottom());

        for (int i = 0; i < tabCount; i++) {
            ListView layout = prepareContainerView(i);
            tabListContainer.addView(layout);
        }

        tabDrawerLayout.addView(tabListContainer);
    }

    private ListView prepareContainerView(int pos) {
        ListView listView = new ListView(context);
        listView.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth(), LinearLayout.LayoutParams.MATCH_PARENT));
        listView.setPadding(tabDrawerLayout.getTabListPaddingLeft(), tabDrawerLayout.getTabListPaddingTop(), tabDrawerLayout.getTabListPaddingRight(), tabDrawerLayout.getTabListPaddingBottom());
        listView.setDividerHeight(0);
        listView.setDivider(null);

        ArrayList<TabDetail> list = tabArray.getTab(pos).getTabItemList();
        if (list == null  ||  list.size() == 0)
            return listView;

        ArrayList<String> items = new ArrayList<>();
        int listSize = list.size();

        for (int i = 0; i < listSize; i++) {
            items.add(list.get(i).getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.tab_detail_item, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

        return listView;
    }

    private void setSelectedTab(int pos) {
        currentSelectedTabPos = pos;

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

            if (i == pos) {
                tabLayout.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor_selected());
                if (hasIcon  &&  hasSelectedIcon) icon.setImageResource(tab.getDrawableId_selected());
                if (hasTitle) title.setTextColor(tabDrawerLayout.getTabTitleColor_selected());
            }
            else {
                tabLayout.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor());
                if (hasIcon) icon.setImageResource(tab.getDrawableId());
                if (hasTitle) title.setTextColor(tabDrawerLayout.getTabTitleColor());
            }
        }

        updateTabListContainer();
    }

    private void updateTabListContainer() {
        Tab selectedTab = tabArray.getTab(currentSelectedTabPos);

        if (selectedTab.getTabItemList() == null) {
            if (drawerOpen) showTabListContainer(false);
            return;
        }

        if (drawerOpen)
            tabListContainer.animate().translationX(0 - (getScreenWidth() * currentSelectedTabPos));
        else
            tabListContainer.setTranslationX(0 - (getScreenWidth() * currentSelectedTabPos));

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

    public void openDrawer() {
        showTabListContainer(true);
    }
    public void closeDrawer() {
        showTabListContainer(false);
    }

    private void showTabListContainer(boolean show) {
        int position;

        if (show) {
            position = 0;
            drawerOpen = true;
        }
        else {
            position = tabListContainerHeight;
            drawerOpen = false;
        }

        tabDrawerLayout.animate().translationY(position);
    }


    @Override
    public void onClick(View v) {
        int clickedId = v.getId();
        int clickedTabPos;

        if (clickedId >= 1000  &&  clickedId < (1000 + tabCount)) {
            clickedTabPos = clickedId - 1000;
        }
        else {
            showTabListContainer(false);
            return;
        }

        if (clickedTabPos == currentSelectedTabPos) {
            if (tabArray.getTab(clickedTabPos).hasItems())
                showTabListContainer(!isDrawerOpen());

            return;
        }
        setSelectedTab(clickedTabPos);

        if (tabArray.getTab(clickedTabPos).hasItems())
            showTabListContainer(true);
        else
            onTabClicked(clickedTabPos, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (previousTabDetailItemView != null) {
            previousTabDetailItemView.setTranslationX(0);
        }

        showTabListContainer(false);

        view.setTranslationX(20);

        previousTabDetailItemView = view;
        clickedTabDetailPos = i;

        onTabClicked(currentSelectedTabPos, i);
    }

    public void onTabClicked(int tabPos, int itemPos) { }
}
