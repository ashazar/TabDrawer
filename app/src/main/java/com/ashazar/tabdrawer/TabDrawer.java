package com.ashazar.tabdrawer;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashazar.tabdrawer.model.Tab;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabDrawer implements View.OnClickListener {
    Context context;
    Activity activity;

    View rootView;
    LinearLayout tabDrawerContainer;
    LinearLayout tabContainer;
    LinearLayout tabListContainer;


    // in px
    int tabContainerHeight = 0;
    int tabHeight = 0;
    int tabDetailHeight = 0;

    int tabPaddingLeft = 0;
    int tabPaddingTop = 0;
    int tabPaddingRight = 0;
    int tabPaddingBottom = 0;

    int tabTitleColor = -1;
    int tabTitleSize = -1;
    int tabBackgroundColor = -1;
    int tabBackgroundSelectedColor = -1;
    int tabBackgroundSelectedPassiveColor = -1;


    int tabListContainerHeight = 0;


    ArrayList<Tab> tabs;
    int tabCount;
    int clickedTabPos = -1;
    int clickedTabDetailPos = -1;
    int currentSelectedTabPos = 0;

    boolean toggle = false;
    boolean isDrawerOpen = false;

    public TabDrawer(Context context, Activity activity, int tabDrawerContainerId, ArrayList<Tab> tabArrayList) {
        this.context = context;
        this.activity = activity;

        rootView = activity.findViewById(android.R.id.content);
        tabDrawerContainer = (LinearLayout) rootView.findViewById(tabDrawerContainerId);

        tabs = tabArrayList;
        tabCount = tabs.size();
    }

    public void initialize() {
        // TODO: just in case, set default width of tabDrawerContainer to MATCH_PARENT
        // TODO: Currently, Root View is RelativeLayout. Haven't checked others yet...


        tabHeight = tabDrawerContainer.getMinimumHeight();
        tabListContainerHeight = tabDrawerContainer.getLayoutParams().height - tabHeight;

        prepareTabContainer();
        addTabs();
        setSelectedTab(0);

        addTabListContainer();

        //showTabListContainer(false);
        tabDrawerContainer.setTranslationY(tabListContainerHeight); toggle = false;

        tabDrawerContainer.setOnClickListener(this);
    }

    private void prepareTabContainer() {
        tabContainer = new LinearLayout(context);
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, tabHeight));
        tabContainer.setTag("tabContainer");

        tabDrawerContainer.addView(tabContainer);
    }

    public void setTabPadding(int left, int top, int right, int bottom) {  // params in dp
        tabPaddingLeft = dpTOpx(left);
        tabPaddingTop = dpTOpx(top);
        tabPaddingRight = dpTOpx(right);
        tabPaddingBottom = dpTOpx(bottom);
    }

    public void setTabTitleColor(int color) { tabTitleColor = color; }

    public void setTabTitleSize(int size) { tabTitleSize = size; }

    public void setTabBackgroundColor(int color) { tabBackgroundColor = color; }
    public void setTabBackgroundSelectedColor(int color) { tabBackgroundSelectedColor = color; }
    public void setTabBackgroundSelectedPassiveColor(int color) { tabBackgroundSelectedPassiveColor = color; }

    private void setSelectedTab(int pos) {
        currentSelectedTabPos = pos;

        for (int i = 0; i < tabCount; i++) {
            boolean hasIcon = false;
            boolean hasSelectedIcon = false;
            boolean hasTitle = false;
            ImageView icon = null;
            TextView title = null;

            Tab tab = tabs.get(i);
            if (tab.drawableId != 0) hasIcon = true;
            if (tab.selectedDrawableId != 0) hasSelectedIcon = true;
            if (tab.title != null) hasTitle = true;

            LinearLayout tabLayout = (LinearLayout) tabContainer.getChildAt(i);
            if (hasIcon)
                icon = (ImageView) tabLayout.findViewById(1100 + i);

            if (hasTitle)
                title = (TextView) tabLayout.findViewById(1200 + i);

            if (i == pos) {
                tabLayout.setBackgroundColor(tabBackgroundSelectedColor);
                if (hasIcon  &&  hasSelectedIcon) icon.setImageResource(tab.selectedDrawableId);
                if (hasTitle) title.setTextColor(tabBackgroundColor);
            }
            else {
                tabLayout.setBackgroundColor(tabBackgroundColor);
                if (hasIcon) icon.setImageResource(tab.drawableId);
                if (hasTitle) title.setTextColor(tabBackgroundSelectedColor);
            }
        }
    }


    private void addTabs() {
        for (int i = 0; i < tabCount; i++) {
            Tab tab = tabs.get(i);

            tabContainer.addView(prepareTab(tab));
        }
    }

    // TODO: Prepare an XML Tab Layout, and give the customization to developer.
    private LinearLayout prepareTab(Tab tab) {
        int pos = tab.pos;
        int posi = pos + 1;

        LinearLayout tabLayout = new LinearLayout(context);
        tabLayout.setOrientation(LinearLayout.VERTICAL);
        tabLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        tabLayout.setPadding(tabPaddingLeft, tabPaddingTop, tabPaddingRight, tabPaddingBottom);
        if (tabBackgroundColor != -1) tabLayout.setBackgroundColor(tabBackgroundColor);
        if (tab.drawableId != 0  &&  tab.title != null) tabLayout.setWeightSum(10);

        tabLayout.setId(1000 + pos);
        tabLayout.setTag("TAB_" + String.valueOf(pos));
        tabLayout.setClickable(true);

        if (tab.drawableId != 0) {
            ImageView icon = new ImageView(context);
            icon.setImageResource(tab.drawableId);

            if (tab.title == null)
                icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            else
                icon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 7));

            icon.setId(1100 + pos);
            tabLayout.addView(icon);
        }


        if (tab.title != null) {
            TextView title = new TextView(context);
            if (tab.drawableId != 0)
                title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3));
            else
                title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            title.setText(tab.title);
            if (tabTitleColor != -1) title.setTextColor(tabTitleColor);
            if (tabTitleSize != -1) title.setTextSize(tabTitleSize);

            title.setId(1200 + pos);
            tabLayout.addView(title);
        }

        tabLayout.setOnClickListener(this);

        return tabLayout;
    }


    private void addTabListContainer() {
        tabListContainer = new LinearLayout(context);
        tabListContainer.setOrientation(LinearLayout.VERTICAL);
        tabListContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, tabListContainerHeight));
        tabListContainer.setBackgroundColor(tabBackgroundSelectedColor);
        tabListContainer.setTag("tabListContainer");


        tabDrawerContainer.addView(tabListContainer);
    }

    public boolean isDrawerOpen() {
        return isDrawerOpen;
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

        tabDrawerContainer.animate().translationY(position);
    }


    private int dpTOpx(int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        //int clickedId = v.getId();
        String tag = (String) v.getTag();

        if (tag == null) {
            toggle = false;
            showTabListContainer(toggle);

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
