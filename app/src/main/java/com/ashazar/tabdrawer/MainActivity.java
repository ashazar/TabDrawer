package com.ashazar.tabdrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ashazar.tabdrawer.model.Tab;
import com.ashazar.tabdrawer.model.TabDetail;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;
    Activity activity;

    TabDrawer tabDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        activity = this;

        if (savedInstanceState == null) {
            prepareTabDrawer();
        }
    }

    private void prepareTabDrawer() {
        ArrayList<Tab> tabArray = new ArrayList<>();
        ArrayList<TabDetail> tabDetailArray = new ArrayList<>();
        Tab tab;
        TabDetail tabDetail;

        tabDetail = new TabDetail("item 1");
        tabDetailArray.add(tabDetail);

        tabDetail = new TabDetail("item 2");
        tabDetailArray.add(tabDetail);


        tab = new Tab("Activity", R.drawable.n_activity, R.drawable.s_activity);
        tabArray.add(tab);

        tab = new Tab("Queue", R.drawable.n_queue, R.drawable.s_queue);
        tab.addList(tabDetailArray);
        tabArray.add(tab);

        tab = new Tab("Chat", R.drawable.n_chat, R.drawable.s_chat);
        tabArray.add(tab);



        tabDetail = new TabDetail("item 3", android.R.drawable.ic_menu_zoom);
        tabDetailArray.add(tabDetail);

        tabDetail = new TabDetail("item 4", android.R.drawable.ic_search_category_default);
        tabDetailArray.add(tabDetail);

        tab = new Tab("Reports", R.drawable.n_report, R.drawable.s_report);
        tab.addList(tabDetailArray);
        tabArray.add(tab);

        tab = new Tab("Settings", R.drawable.n_settings, R.drawable.s_settings);
        tabArray.add(tab);

        tabDrawer = new TabDrawer(context, activity, R.id.tabDrawer, tabArray) {
            @Override
            public void onTabClicked(int pos) {
                super.onTabClicked(pos);

                String text;
                text = "Tab " + pos;

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
           /*
            @Override
            public void onClick(View v) {
                super.onClick(v);

                String text;
                //text = clickedTabPos + " - " + clickedTabDetailPos;
                text = "Tab " + clickedTabPos;

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
            */
        };

        tabDrawer.setTabPadding(3, 3, 3, 3);
        tabDrawer.setTabTitleColor(Color.parseColor("#3199ff"));
        tabDrawer.setTabBackgroundColor(Color.parseColor("#ffffff"));
        tabDrawer.setTabBackgroundSelectedColor(Color.parseColor("#3199ff"));
        tabDrawer.setTabBackgroundSelectedPassiveColor(Color.parseColor("#cccccc"));

        tabDrawer.setTabListContainerPadding(16, 16, 16, 16);

        tabDrawer.initialize();
    }

    }
}
