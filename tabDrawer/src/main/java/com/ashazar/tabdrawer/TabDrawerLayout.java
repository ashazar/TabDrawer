package com.ashazar.tabdrawer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
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
    private int height_tabBar;
    private int height_Total;
    private int width_tabBar;
    private int width_Total;

    // TAB
    private int defaultSelectedTab;
    private int customTabItemLayoutResId;

    private int tabPadding;
    private int tabPaddingLeft;
    private int tabPaddingRight;
    private int tabPaddingTop;
    private int tabPaddingBottom;

    private int tabTitleSize;
    private int tabTitleColor;
    private int tabTitleColor_selected;
    private int tabBackgroundColor;
    private int tabBackgroundColor_selected;

    // TAB Detail List
    private int defaultSelectedTabItem;

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
        customTabItemLayoutResId = arr.getInteger(R.styleable.TabDrawerLayout_custom_tabItemLayout, 0);

        tabBarPosition = arr.getInteger(R.styleable.TabDrawerLayout_tabBarPosition, 0);
        height_tabBar = (int) arr.getDimension(R.styleable.TabDrawerLayout_height_tabBar, 120);
        height_Total = (int) arr.getDimension(R.styleable.TabDrawerLayout_height_Total, getLayoutHeight_tabBar() * 2);
        width_tabBar = (int) arr.getDimension(R.styleable.TabDrawerLayout_width_tabBar, 100);
        width_Total = (int) arr.getDimension(R.styleable.TabDrawerLayout_width_Total, getLayoutWidth_tabBar() * 3);

        // TAB
        tabPadding = (int) arr.getDimension(R.styleable.TabDrawerLayout_padding, 0);
        tabPaddingLeft = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingLeft, 0);
        tabPaddingRight = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingRight, 0);
        tabPaddingTop = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingTop, 0);
        tabPaddingBottom = (int) arr.getDimension(R.styleable.TabDrawerLayout_paddingBottom, 0);

        tabTitleSize = (int) arr.getDimension(R.styleable.TabDrawerLayout_titleSize, 12);
        tabTitleColor = arr.getColor(R.styleable.TabDrawerLayout_titleColor, ContextCompat.getColor(context, R.color.default_TabTitle));
        tabTitleColor_selected = arr.getColor(R.styleable.TabDrawerLayout_titleColor_selected, ContextCompat.getColor(context, R.color.default_TabTitle_selected));
        tabBackgroundColor = arr.getColor(R.styleable.TabDrawerLayout_backgroundColor, ContextCompat.getColor(context, R.color.default_TabBackground));
        tabBackgroundColor_selected = arr.getColor(R.styleable.TabDrawerLayout_backgroundColor_selected, ContextCompat.getColor(context, R.color.default_TabBackground_selected));

        // TAB Detail List
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
     * Gets custom tab item layout res id.
     *
     * @return the custom tab item layout res id
     */
    public int getCustomTabItemLayoutResId() { return customTabItemLayoutResId; }

    /**
     * Gets tab bar position.
     *
     * @return the tab bar position
     */
    public int getTabBarPosition() { return tabBarPosition; }

    /**
     * Gets layout height tab bar.
     *
     * @return the layout height tab bar
     */
    public int getLayoutHeight_tabBar() { return height_tabBar; }

    /**
     * Gets layout height total.
     *
     * @return the layout height total
     */
    public int getLayoutHeight_Total() { return height_Total; }

    /**
     * Gets layout height list container.
     *
     * @return the layout height list container
     */
    public int getLayoutHeight_ListContainer() { return getLayoutHeight_Total() - getLayoutHeight_tabBar(); }

    /**
     * Gets layout width tab bar.
     *
     * @return the layout width tab bar
     */
    public int getLayoutWidth_tabBar() { return width_tabBar; }

    /**
     * Gets layout width total.
     *
     * @return the layout width total
     */
    public int getLayoutWidth_Total() { return width_Total; }

    /**
     * Gets layout width list container.
     *
     * @return the layout width list container
     */
    public int getLayoutWidth_ListContainer() { return getLayoutWidth_Total() - getLayoutWidth_tabBar(); }

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

    /**
     * Gets tab title size.
     *
     * @return the tab title size
     */
    public int getTabTitleSize() { return tabTitleSize; }

    /**
     * Gets tab title color.
     *
     * @return the tab title color
     */
    public int getTabTitleColor() { return tabTitleColor; }

    /**
     * Gets tab title color selected.
     *
     * @return the tab title color selected
     */
    public int getTabTitleColor_selected() { return tabTitleColor_selected; }

    /**
     * Gets tab background color.
     *
     * @return the tab background color
     */
    public int getTabBackgroundColor() { return tabBackgroundColor; }

    /**
     * Gets tab background color selected.
     *
     * @return the tab background color selected
     */
    public int getTabBackgroundColor_selected() { return tabBackgroundColor_selected; }

}
