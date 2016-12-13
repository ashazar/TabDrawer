package com.ashazar.tabdrawerexample;

import android.os.Bundle;

public class NewActivity extends BaseActivity {
    public static final String POSITION = "Position";

    public static final String POSITION_TOP = "Top";
    public static final String POSITION_BOTTOM = "Bottom";
    public static final String POSITION_LEFT = "Left";
    public static final String POSITION_RIGHT = "Right";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String tabBarPosition = getIntent().getStringExtra(POSITION);

        int layoutId = 0;
        boolean additional = false;

        switch (tabBarPosition) {
            case POSITION_BOTTOM:
                layoutId = R.layout.with_bottom_tabdrawer;
                break;

            case POSITION_TOP:
                layoutId = R.layout.with_top_tabdrawer;
                break;

            case POSITION_LEFT:
                layoutId = R.layout.with_left_tabdrawer;
                additional = true;
                break;

            case POSITION_RIGHT:
                layoutId = R.layout.with_right_tabdrawer;
                additional = true;
                break;
        }

        setContentView(layoutId);

        prepareTabDrawer(additional);
    }


}
