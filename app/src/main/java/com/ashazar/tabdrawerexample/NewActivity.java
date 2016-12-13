package com.ashazar.tabdrawerexample;

import android.os.Bundle;

public class NewActivity extends BaseActivity {
    public static final String POSITION = "Position";

    public static final String POSITION_TOP = "Top";
    public static final String POSITION_LEFT = "Left";
    public static final String POSITION_RIGHT = "Right";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String tabBarPosition = getIntent().getStringExtra(POSITION);

        if (tabBarPosition.equals(POSITION_TOP))
            setContentView(R.layout.with_top_tabdrawer);
        else if (tabBarPosition.equals(POSITION_LEFT))
            setContentView(R.layout.with_left_tabdrawer);
        else if (tabBarPosition.equals(POSITION_RIGHT))
            setContentView(R.layout.with_right_tabdrawer);

        prepareTabDrawer();
    }


}
