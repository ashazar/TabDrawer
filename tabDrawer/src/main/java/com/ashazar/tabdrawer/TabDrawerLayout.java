package com.ashazar.tabdrawer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Serdar Hazar on 11/20/16.
 */

public class TabDrawerLayout extends LinearLayout {
    private int height_tabBar;
    private int height_Total;

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
    private int list_padding;
    private int list_paddingLeft;
    private int list_paddingRight;
    private int list_paddingTop;
    private int list_paddingBottom;


    public TabDrawerLayout(Context context) { super(context); }
    public TabDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context, attrs);
    }
    public TabDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context, attrs);
    }
    @TargetApi(21)
    public TabDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context, attrs);
    }


    private void Init(Context context, AttributeSet attrs) {
        this.setOrientation(VERTICAL);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TabDrawerLayout);

        defaultSelectedTab = arr.getInteger(R.styleable.TabDrawerLayout_defaultSelectedTab, 1);
        customTabItemLayoutResId = arr.getInteger(R.styleable.TabDrawerLayout_custom_tabItemLayout, 0);

        height_tabBar = (int) arr.getDimension(R.styleable.TabDrawerLayout_height_tabBar, 120);
        height_Total = (int) arr.getDimension(R.styleable.TabDrawerLayout_height_Total, getLayoutHeight_tabBar() * 2);

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
    }


    public int getDefaultSelectedTab() { return defaultSelectedTab; }
    public int getCustomTabItemLayoutResId() { return customTabItemLayoutResId; }

    public int getLayoutHeight_tabBar() { return height_tabBar; }
    public int getLayoutHeight_Total() { return height_Total; }
    public int getLayoutHeight_ListContainer() { return getLayoutHeight_Total() - getLayoutHeight_tabBar(); }

    public int getTabPadding() { return tabPadding; }
    public int getTabPaddingLeft() { return (tabPaddingLeft != 0) ? tabPaddingLeft : tabPadding; }
    public int getTabPaddingRight() { return (tabPaddingRight != 0) ? tabPaddingRight : tabPadding; }
    public int getTabPaddingTop() { return (tabPaddingTop != 0) ? tabPaddingTop : tabPadding; }
    public int getTabPaddingBottom() { return (tabPaddingBottom != 0) ? tabPaddingBottom : tabPadding; }

    public int getTabListPadding() { return list_padding; }
    public int getTabListPaddingLeft() { return (list_paddingLeft != 0) ? list_paddingLeft : list_padding; }
    public int getTabListPaddingRight() { return (list_paddingRight != 0) ? list_paddingRight : list_padding; }
    public int getTabListPaddingTop() { return (list_paddingTop != 0) ? list_paddingTop : list_padding; }
    public int getTabListPaddingBottom() { return (list_paddingBottom != 0) ? list_paddingBottom : list_padding; }

    public int getTabTitleSize() { return tabTitleSize; }
    public int getTabTitleColor() { return tabTitleColor; }
    public int getTabTitleColor_selected() { return tabTitleColor_selected; }
    public int getTabBackgroundColor() { return tabBackgroundColor; }
    public int getTabBackgroundColor_selected() { return tabBackgroundColor_selected; }

}
