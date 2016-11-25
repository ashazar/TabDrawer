package com.ashazar.tabdrawer;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
public class TabDrawer implements View.OnClickListener {
    private Context context;

    private TabDrawerLayout tabDrawerLayout;
    private LinearLayout tabContainer;
    private LinearLayout tabListContainer;

    // in px
    private int tabBarHeight = 0;
    private int tabListContainerHeight = 0;

    //private ArrayList<Tab> tabs;
    private TabArray tabArray;
    private int tabCount;
    private int clickedTabPos = -1;
    private int clickedTabDetailPos = -1;
    private int currentSelectedTabPos = 0;

    private boolean toggle = false;
    private boolean isDrawerOpen = false;

    protected TabDrawer(Context context, Activity activity, int tabDrawerLayoutId, TabArray tabArray) {
        this.context = context;

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

        int defaultSelectedTab = tabDrawerLayout.getDefaultSelectedTab() - 1;
        if (tabDrawerLayout.getDefaultSelectedTab() > tabCount) defaultSelectedTab = 0;

        setSelectedTab(defaultSelectedTab);

        tabDrawerLayout.setTranslationY(tabListContainerHeight);
        toggle = false;

        tabDrawerLayout.setOnClickListener(this);
    }

    private void prepareTabContainer() {
        tabContainer = new LinearLayout(context);
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, tabBarHeight));
        tabContainer.setTag("tabContainer");

        tabDrawerLayout.addView(tabContainer);
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


    private void addTabs() {
        for (int i = 0; i < tabCount; i++) {
            tabContainer.addView(prepareTab(i));
        }
    }

    // TODO: Prepare an XML Tab Layout, and give the customization to developer.
    private LinearLayout prepareTab(int pos) {
        Tab tab = tabArray.getTab(pos);
        //int posi = pos + 1;

        LinearLayout tabLayout = new LinearLayout(context);
        tabLayout.setOrientation(LinearLayout.VERTICAL);
        tabLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        tabLayout.setPadding(tabDrawerLayout.getTabPaddingLeft(), tabDrawerLayout.getTabPaddingTop(), tabDrawerLayout.getTabPaddingRight(), tabDrawerLayout.getTabPaddingBottom());
        tabLayout.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor());
        if (tab.getDrawableId() != 0  &&  tab.getTitle() != null) tabLayout.setWeightSum(10);

        tabLayout.setId(1000 + pos);
        tabLayout.setTag("TAB_" + String.valueOf(pos));
        tabLayout.setClickable(true);

        if (tab.getDrawableId() != 0) {
            ImageView icon = new ImageView(context);
            icon.setImageResource(tab.getDrawableId());

            if (tab.getTitle() == null)
                icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            else
                icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 7));

            icon.setId(1100 + pos);
            tabLayout.addView(icon);
        }


        if (tab.getTitle() != null) {
            TextView title = new TextView(context);
            if (tab.getDrawableId() != 0)
                title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
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
        tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tabListContainerHeight));
        tabListContainer.setBackgroundColor(tabDrawerLayout.getTabBackgroundColor_selected());
        tabListContainer.setPadding(tabDrawerLayout.getTabListPaddingLeft(), tabDrawerLayout.getTabListPaddingTop(), tabDrawerLayout.getTabListPaddingRight(), tabDrawerLayout.getTabListPaddingBottom());
        tabListContainer.setTag("tabListContainer");

        for (int i = 0; i < tabCount; i++) {
            ListView listView = prepareListView(i);
            if (i == 0)
                listView.setVisibility(View.GONE);
            else
                listView.setVisibility(View.VISIBLE);

            tabListContainer.addView(listView);
        }

        tabDrawerLayout.addView(tabListContainer);
    }

    private ListView prepareListView(int pos) {
        ListView listView = new ListView(context);
        listView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tabListContainerHeight));
        listView.setDividerHeight(0);
        listView.setDivider(null);
        listView.setTag("LISTVIEW_" + pos);

        //ArrayList<TabDetail> list = tabs.get(pos).getTabItemList();
        ArrayList<TabDetail> list = tabArray.getTab(pos).getTabItemList();
        Log.d("ASH--", "TAB " + pos + " - " + tabArray.getTab(pos).getTabItemList());
        if (list == null) return listView;

        ArrayList<String> items = new ArrayList<>();
        int listSize = list.size();

        Log.d("ASH--", "prepareListView - pos: " + pos);
        for (int i = 0; i < listSize; i++) {
            items.add(list.get(i).getTitle());
            Log.d("ASH--", list.get(i).getTitle());
        }
        Log.d("ASH--", "");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.tab_detail_item, items);
        listView.setAdapter(adapter);

        return listView;
    }

    private void updateTabListContainer() {
        Tab selectedTab = tabArray.getTab(currentSelectedTabPos);

        if (selectedTab.getTabItemList() == null) {
            if (isDrawerOpen) showTabListContainer(false);
            return;
        }

        for (int i = 0; i < tabCount; i++) {
            ListView listView = (ListView) tabListContainer.getChildAt(i);

            if (i == currentSelectedTabPos) {
                listView.setVisibility(View.GONE);
            }
            else {
                listView.setVisibility(View.VISIBLE);
            }
        }
    }

    public boolean isDrawerOpen() {
        return isDrawerOpen;
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
            isDrawerOpen = true;
        }
        else {
            position = tabListContainerHeight;
            isDrawerOpen = false;
        }

        tabDrawerLayout.animate().translationY(position);
    }


    @Override
    public void onClick(View v) {
        //int clickedId = v.getId();
        String tag = (String) v.getTag();

        if (tag == null) {
            showTabListContainer(false);

            return;
        }
        clickedTabPos = Integer.parseInt(tag.replace("TAB_", ""));


        if (clickedTabPos == currentSelectedTabPos) {
            showTabListContainer(!isDrawerOpen);
            return;
        }
        setSelectedTab(clickedTabPos);
        showTabListContainer(true);

        onTabClicked(clickedTabPos);

    }

    public void onTabClicked(int pos) {

    }
}
