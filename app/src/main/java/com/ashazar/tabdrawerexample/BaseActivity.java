package com.ashazar.tabdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.R.drawable;

import com.ashazar.tabdrawer.TabDrawer;
import com.ashazar.tabdrawer.model.Tab;
import com.ashazar.tabdrawer.model.TabArray;
import com.ashazar.tabdrawer.model.TabDetail;

public class BaseActivity extends AppCompatActivity {
    Context context;
    Activity activity;

    TabDrawer tabDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        activity = this;
    }


    public void prepareTabDrawer() {
        final TabArray tabArray = new TabArray()
                .setTabItemListTextColor(Color.parseColor("#ffffff"))
                .setTabItemListTextSize(16)

                .addTab( new Tab()
                        .setTitle("Demo")
                        .setDrawableId(R.drawable.n_activity)
                        .setSelectedDrawableId(R.drawable.s_activity)
                        //.addTabDetailItem( new TabDetail("Dialog Map", drawable.ic_dialog_map) )
                        //.addTabDetailItem( new TabDetail("Dialog Alert", drawable.ic_dialog_alert) )
                        .addTabDetailItem( new TabDetail("Bottom TabDrawer", drawable.ic_dialog_alert) )
                        .addTabDetailItem( new TabDetail("Top TabDrawer", drawable.ic_dialog_alert) )
                        .addTabDetailItem( new TabDetail("Left TabDrawer", drawable.ic_dialog_alert) )
                        .addTabDetailItem( new TabDetail("Right TabDrawer", drawable.ic_dialog_alert) )
                )

                .addTab( new Tab()
                        .setTitle("Queue")
                        .setDrawableId(R.drawable.n_queue)
                        .setSelectedDrawableId(R.drawable.s_queue)
                        .addTabDetailItem( new TabDetail("Dialog Dialer", drawable.ic_dialog_dialer ) )
                        .addTabDetailItem( new TabDetail("Dialog E-mail", drawable.ic_dialog_email) )
                        .addTabDetailItem( new TabDetail("Dialog Info", drawable.ic_dialog_info) )
                )

                .addTab( new Tab()
                        .setTitle("Chat")
                        .setDrawableId(R.drawable.n_chat)
                        .setSelectedDrawableId(R.drawable.s_chat)
                        .addTabDetailItem( new TabDetail("Previous", drawable.ic_media_previous) )
                        .addTabDetailItem( new TabDetail("Next", drawable.ic_media_next) )
                )

                .addTab( new Tab()
                        .setTitle("Reports")
                        .setDrawableId(R.drawable.n_report)
                        .setSelectedDrawableId(R.drawable.s_report)
                        .addTabDetailItem( new TabDetail("Clear All", drawable.ic_notification_clear_all) )
                        .addTabDetailItem( new TabDetail("Disk Full", drawable.ic_popup_disk_full) )
                        .addTabDetailItem( new TabDetail("Menu Zoom", drawable.ic_menu_zoom) )
                )

                .addTab( new Tab()
                        .setTitle("Settings")
                        .setDrawableId(R.drawable.n_settings)
                        .setSelectedDrawableId(R.drawable.s_settings)
                        .addTabDetailItem( new TabDetail("Menu Share", drawable.ic_menu_share) )
                        .addTabDetailItem( new TabDetail("Menu View", drawable.ic_menu_view) )
                        .addTabDetailItem( new TabDetail("Menu SlideShow", drawable.ic_menu_slideshow) )
                        .addTabDetailItem( new TabDetail("Menu Agenda", drawable.ic_menu_agenda) )
                        .addTabDetailItem( new TabDetail("Menu Camera", drawable.ic_menu_camera) )
                        .addTabDetailItem( new TabDetail("Menu Call", drawable.ic_menu_call) )
                );

        tabDrawer = new TabDrawer(context, activity, R.id.tabDrawer, tabArray) {
            @Override
            public void onTabDrawerClicked(int tabPosition, int itemPosition) {
                super.onTabDrawerClicked(tabPosition, itemPosition);

                String text = tabArray.getTab(tabPosition).getTitle()
                        + " -> "
                        + tabArray.getTab(tabPosition).getTabItemList().get(itemPosition).getTitle()
                        + " - ( " + tabPosition + ", " + itemPosition + " )";


                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();

                if (tabPosition == 0) {
                    Intent intent;

                    if (itemPosition == 0)
                        intent = new Intent(context, MainActivity.class);
                    else {
                        intent = new Intent(context, NewActivity.class);

                        if (itemPosition == 1)
                            intent.putExtra(NewActivity.POSITION, NewActivity.POSITION_TOP);
                        else if (itemPosition == 2)
                            intent.putExtra(NewActivity.POSITION, NewActivity.POSITION_LEFT);
                        else if (itemPosition == 3)
                            intent.putExtra(NewActivity.POSITION, NewActivity.POSITION_RIGHT);
                    }

                    startActivity(intent);
                }
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
