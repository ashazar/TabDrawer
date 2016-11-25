package com.ashazar.tabdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ashazar.tabdrawer.TabDrawer;
import com.ashazar.tabdrawer.model.Tab;
import com.ashazar.tabdrawer.model.TabArray;
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
        /*
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
        */

        /*
        ArrayList<Tab> tabArray = new ArrayList<>();
        ArrayList<TabDetail> tabDetailArray = new ArrayList<>();
        Tab tab;
        TabDetail tabDetail;

        tab = new Tab("Activity", R.drawable.n_activity, R.drawable.s_activity);
        tab = new Tab("Queue", R.drawable.n_queue, R.drawable.s_queue);
        tab = new Tab("Chat", R.drawable.n_chat, R.drawable.s_chat);
        tab = new Tab("Reports", R.drawable.n_report, R.drawable.s_report);
        tab = new Tab("Settings", R.drawable.n_settings, R.drawable.s_settings);
        */
        TabArray tabArray = new TabArray();
        tabArray.addTab( new Tab()
                        .setTitle("Activity")
                        .setDrawableId(R.drawable.n_activity)
                        .setSelectedDrawableId(R.drawable.s_activity)
                        .addTabDetailItem(new TabDetail("TAB 1 - item 1"))
                        .addTabDetailItem(new TabDetail("TAB 1 - item 2"))
                        )
                .addTab( new Tab()
                        .setTitle("Queue")
                        .setDrawableId(R.drawable.n_queue)
                        .setSelectedDrawableId(R.drawable.s_queue)
                        .addTabDetailItem(new TabDetail("TAB 2 - item 11"))
                        .addTabDetailItem(new TabDetail("TAB 2 - item 22"))
                        .addTabDetailItem(new TabDetail("TAB 2 - item 33"))
                        )

                .addTab( new Tab()
                        .setTitle("Chat")
                        .setDrawableId(R.drawable.n_chat)
                        .setSelectedDrawableId(R.drawable.s_chat)
                        .addTabDetailItem(new TabDetail("TAB 3 - item 111"))
                        )

                .addTab( new Tab()
                        .setTitle("Reports")
                        .setDrawableId(R.drawable.n_report)
                        .setSelectedDrawableId(R.drawable.s_report)
                        .addTabDetailItem(new TabDetail("TAB 4 - item 1111"))
                        .addTabDetailItem(new TabDetail("TAB 4 - item 2222"))
                        .addTabDetailItem(new TabDetail("TAB 4 - item 3333"))
                    )

                .addTab( new Tab()
                        .setTitle("Settings")
                        .setDrawableId(R.drawable.n_settings)
                        .setSelectedDrawableId(R.drawable.s_settings)
                        .addTabDetailItem(new TabDetail("TAB 5 - item 11111"))
                        .addTabDetailItem(new TabDetail("TAB 5 - item 22222"))
                        .addTabDetailItem(new TabDetail("TAB 5 - item 33333"))
                        .addTabDetailItem(new TabDetail("TAB 5 - item 44444"))
                );

        Log.d("--ASH--", "tabArray: " + tabArray.getTab(0).getTabItemList().get(0).getTitle());
        Log.d("--ASH--", "tabArray: " + tabArray.getTab(0).getTabItemList().get(1).getTitle());
        Log.d("--ASH--", "tabArray: " + tabArray.getTab(1).getTabItemList().get(0).getTitle());
        Log.d("--ASH--", "tabArray: " + tabArray.getTab(1).getTabItemList().get(1).getTitle());

        tabDrawer = new TabDrawer(context, activity, R.id.tabDrawer, tabArray) {
            @Override
            public void onTabClicked(int pos) {
                super.onTabClicked(pos);

                String text;
                text = "Tab " + pos;

                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        };


        tabDrawer.initialize();
    }

    @Override
    public void onBackPressed() {
        if (tabDrawer.isDrawerOpen())
            tabDrawer.closeDrawer();
        else
            super.onBackPressed();
    }
}
