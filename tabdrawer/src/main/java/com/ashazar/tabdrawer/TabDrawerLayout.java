package com.ashazar.tabdrawer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * TabDrawerLayout, extended from LinearLayout
 * This layout will be the main layout for TabDrawer.
 * <p>
 * Created by Serdar Hazar on 11/20/16.
 */
public class TabDrawerLayout extends LinearLayout {
    private int tabBarPosition;
    private int size_tabBar;
    private int size_Total;

    // TAB
    private int defaultSelectedTab;

    private int tabPadding;
    private int tabPaddingLeft;
    private int tabPaddingRight;
    private int tabPaddingTop;
    private int tabPaddingBottom;

    // TAB Detail List
    private int defaultSelectedTabItem;

    private int drawer_padding;
    private int drawer_paddingLeft;
    private int drawer_paddingRight;
    private int drawer_paddingTop;
    private int drawer_paddingBottom;

    private int list_padding;
    private int list_paddingLeft;
    private int list_paddingRight;
    private int list_paddingTop;
    private int list_paddingBottom;


    /**
     * Instantiates a new TabDrawerLayout.
     *
     * @param context the context
     */
    public TabDrawerLayout(Context context) { super(context); }

    /**
     * Instantiates a new TabDrawerLayout.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public TabDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context, attrs);
    }

    /**
     * Instantiates a new TabDrawerLayout.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     */
    public TabDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context, attrs);
    }

    /**
     * Instantiates a new TabDrawerLayout.
     *
     * @param context      the context
     * @param attrs        the attrs
     * @param defStyleAttr the def style attr
     * @param defStyleRes  the def style res
     */
    @TargetApi(21)
    public TabDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context, attrs);
    }


    /**
     * Gets/Sets the custom attributes (tab:xyz="") of TabDrawer.
     * Developer needs to add below namespace definition in root layout, in order to use these attributes.
     *
     * xmlns:tab="http://schemas.android.com/apk/res-auto
     *
     * @param context context
     * @param attrs attributes
     */
    private void Init(Context context, AttributeSet attrs) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TabDrawerLayout);

        defaultSelectedTab = arr.getInteger(R.styleable.TabDrawerLayout_defaultSelectedTab, 1);
        defaultSelectedTabItem = arr.getInteger(R.styleable.TabDrawerLayout_defaultSelectedTabItem, 1);

        tabBarPosition = arr.getInteger(R.styleable.TabDrawerLayout_tabBarPosition, 0);
        size_tabBar = (int) arr.getDimension(R.styleable.TabDrawerLayout_size_tabBar, 120);
        size_Total = (int) arr.getDimension(R.styleable.TabDrawerLayout_size_Total, getLayoutSize_tabBar() * 2);

        // TAB
        tabPadding = (int) arr.getDimension(R.styleable.TabDrawerLayout_padding, 0);
        tabPaddingLeft = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingLeft, 0);
        tabPaddingRight = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingRight, 0);
        tabPaddingTop = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingTop, 0);
        tabPaddingBottom = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingBottom, 0);

        // TAB Detail List
        drawer_padding = (int) arr.getDimension(R.styleable.TabDrawerLayout_drawer_padding, 0);
        drawer_paddingLeft = (int) arr.getDimension(R.styleable.TabDrawerLayout_drawer_paddingLeft, 0);
        drawer_paddingRight = (int) arr.getDimension(R.styleable.TabDrawerLayout_drawer_paddingRight, 0);
        drawer_paddingTop = (int) arr.getDimension(R.styleable.TabDrawerLayout_drawer_paddingTop, 0);
        drawer_paddingBottom = (int) arr.getDimension(R.styleable.TabDrawerLayout_drawer_paddingBottom, 0);

        list_padding = (int) arr.getDimension(R.styleable.TabDrawerLayout_list_padding, 0);
        list_paddingLeft = (int) arr.getDimension(R.styleable.TabDrawerLayout_list_paddingLeft, 0);
        list_paddingRight = (int) arr.getDimension(R.styleable.TabDrawerLayout_list_paddingRight, 0);
        list_paddingTop = (int) arr.getDimension(R.styleable.TabDrawerLayout_list_paddingTop, 0);
        list_paddingBottom = (int) arr.getDimension(R.styleable.TabDrawerLayout_list_paddingBottom, 0);

        arr.recycle();

        if (tabBarPosition <= 1)
            this.setOrientation(VERTICAL);
        else
            this.setOrientation(HORIZONTAL);
    }


    /**
     * Gets default selected tab.
     *
     * @return the default selected tab
     */
    public int getDefaultSelectedTab() { return defaultSelectedTab - 1; }

    /**
     * Gets default selected tab item.
     *
     * @return the default selected tab item
     */
    public int getDefaultSelectedTabItem() { return defaultSelectedTabItem - 1; }

    /**
     * Gets tab bar position.
     *
     * @return the tab bar position
     */
    public int getTabBarPosition() { return tabBarPosition; }

    /**
     * Gets layout size tab bar.
     * size = Height for Top/Bottom TabDrawer; size = Width for Left/Right TabDrawer
     *
     * @return the layout width tab bar
     */
    public int getLayoutSize_tabBar() { return size_tabBar; }

    /**
     * Gets layout size total.
     * size = Height for Top/Bottom TabDrawer; size = Width for Left/Right TabDrawer
     *
     * @return the layout width total
     */
    public int getLayoutSize_Total() { return size_Total; }

    /**
     * Gets layout size list container.
     * size = Height for Top/Bottom TabDrawer; size = Width for Left/Right TabDrawer
     *
     * @return the layout width list container
     */
    public int getLayoutSize_ListContainer() { return getLayoutSize_Total() - getLayoutSize_tabBar(); }

    /**
     * Gets tab padding.
     *
     * @return the tab padding
     */
    public int getTabPadding() { return tabPadding; }

    /**
     * Gets tab padding left.
     *
     * @return the tab padding left
     */
    public int getTabPaddingLeft() { return (tabPaddingLeft != 0) ? tabPaddingLeft : tabPadding; }

    /**
     * Gets tab padding right.
     *
     * @return the tab padding right
     */
    public int getTabPaddingRight() { return (tabPaddingRight != 0) ? tabPaddingRight : tabPadding; }

    /**
     * Gets tab padding top.
     *
     * @return the tab padding top
     */
    public int getTabPaddingTop() { return (tabPaddingTop != 0) ? tabPaddingTop : tabPadding; }

    /**
     * Gets tab padding bottom.
     *
     * @return the tab padding bottom
     */
    public int getTabPaddingBottom() { return (tabPaddingBottom != 0) ? tabPaddingBottom : tabPadding; }

    /**
     * Gets drawer padding.
     *
     * @return the drawer padding
     */
    public int getDrawerPadding() { return drawer_padding; }

    /**
     * Gets drawer padding left.
     *
     * @return the drawer padding left
     */
    public int getDrawerPaddingLeft() { return (drawer_paddingLeft != 0) ? drawer_paddingLeft : drawer_padding; }

    /**
     * Gets drawer padding right.
     *
     * @return the drawer padding right
     */
    public int getDrawerPaddingRight() { return (drawer_paddingRight != 0) ? drawer_paddingRight : drawer_padding; }

    /**
     * Gets drawer padding top.
     *
     * @return the drawer padding top
     */
    public int getDrawerPaddingTop() { return (drawer_paddingTop != 0) ? drawer_paddingTop : drawer_padding; }

    /**
     * Gets drawer padding bottom.
     *
     * @return the drawer padding bottom
     */
    public int getDrawerPaddingBottom() { return (drawer_paddingBottom != 0) ? drawer_paddingBottom : drawer_padding; }

    /**
     * Gets tab list padding.
     *
     * @return the tab list padding
     */
    public int getTabListPadding() { return list_padding; }

    /**
     * Gets tab list padding left.
     *
     * @return the tab list padding left
     */
    public int getTabListPaddingLeft() { return (list_paddingLeft != 0) ? list_paddingLeft : list_padding; }

    /**
     * Gets tab list padding right.
     *
     * @return the tab list padding right
     */
    public int getTabListPaddingRight() { return (list_paddingRight != 0) ? list_paddingRight : list_padding; }

    /**
     * Gets tab list padding top.
     *
     * @return the tab list padding top
     */
    public int getTabListPaddingTop() { return (list_paddingTop != 0) ? list_paddingTop : list_padding; }

    /**
     * Gets tab list padding bottom.
     *
     * @return the tab list padding bottom
     */
    public int getTabListPaddingBottom() { return (list_paddingBottom != 0) ? list_paddingBottom : list_padding; }
}
