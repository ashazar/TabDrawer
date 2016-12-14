package com.ashazar.tabdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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

    private TabArray prepareTabArray() {
        TabArray tabArray = new TabArray()
                .setTabItemListTextColor(Color.parseColor("#ffffff"))
                .setTabItemListTextSize(16)

                .addTab( new Tab()
                        .setTitle("Demo")
                        .setDrawableId(R.drawable.n_activity)
                        .setSelectedDrawableId(R.drawable.s_activity)
                        .addTabDetailItem( new TabDetail("Bottom/Left TabDrawer", R.drawable.ic_home_white_24dp) )
                        .addTabDetailItem( new TabDetail("Bottom TabDrawer", R.drawable.ic_action_collapse) )
                        .addTabDetailItem( new TabDetail("Top TabDrawer", R.drawable.ic_action_expand) )
                        .addTabDetailItem( new TabDetail("Left TabDrawer", R.drawable.ic_action_next_item) )
                        .addTabDetailItem( new TabDetail("Right TabDrawer", R.drawable.ic_action_previous_item) )
                )

                .addTab( new Tab()
                        .setTitle("Queue")
                        .setDrawableId(R.drawable.n_queue)
                        .setSelectedDrawableId(R.drawable.s_queue)
                        .addTabDetailItem( new TabDetail("Add to Queue", R.drawable.ic_add_box_white_24dp ) )
                        .addTabDetailItem( new TabDetail("Archive", R.drawable.ic_archive_white_24dp) )
                        .addTabDetailItem( new TabDetail("Delete", R.drawable.ic_delete_forever_white_24dp) )
                )

                .addTab( new Tab()
                        .setTitle("Chat")
                        .setDrawableId(R.drawable.n_chat)
                        .setSelectedDrawableId(R.drawable.s_chat)
                        .addTabDetailItem( new TabDetail("Friends", R.drawable.ic_face_white_24dp) )
                        .addTabDetailItem( new TabDetail("Add Friend", R.drawable.ic_person_add_white_24dp) )
                        .addTabDetailItem( new TabDetail("Start Group Chat", R.drawable.ic_people_white_24dp) )
                        .addTabDetailItem( new TabDetail("Funny Moments", R.drawable.ic_sentiment_very_satisfied_white_24dp) )
                )

                .addTab( new Tab()
                        .setTitle("Reports")
                        .setDrawableId(R.drawable.n_report)
                        .setSelectedDrawableId(R.drawable.s_report)
                        .addTabDetailItem( new TabDetail("Completed Jobs", R.drawable.ic_event_available_white_24dp) )
                        .addTabDetailItem( new TabDetail("Cancelled Jobs", R.drawable.ic_event_busy_white_24dp) )
                        .addTabDetailItem( new TabDetail("Customer Feedbacks", R.drawable.ic_feedback_white_24dp) )
                        .addTabDetailItem( new TabDetail("Documents", R.drawable.ic_description_white_24dp) )
                )

                .addTab( new Tab()
                        .setTitle("Settings")
                        .setDrawableId(R.drawable.n_settings)
                        .setSelectedDrawableId(R.drawable.s_settings)
                        .addTabDetailItem( new TabDetail("General", R.drawable.ic_settings_white_24dp) )
                        .addTabDetailItem( new TabDetail("My Account", R.drawable.ic_lock_white_24dp) )
                        .addTabDetailItem( new TabDetail("Accesibility", R.drawable.ic_accessibility_white_24dp) )
                        .addTabDetailItem( new TabDetail("Notifications", R.drawable.ic_notifications_white_24dp) )
                        .addTabDetailItem( new TabDetail("Bookmarks", R.drawable.ic_collections_bookmark_white_24dp) )
                        .addTabDetailItem( new TabDetail("Shared Folders", R.drawable.ic_folder_shared_white_24dp) )
                        .addTabDetailItem( new TabDetail("Cast to TV", R.drawable.ic_cast_white_24dp) )
                        .addTabDetailItem( new TabDetail("Other Applications", R.drawable.ic_apps_white_24dp) )
                );

        return tabArray;
    }

    public void prepareTabDrawer() { prepareTabDrawer(false); }

    public void prepareTabDrawer(boolean additional) {
        TabArray tabArrayTemp = prepareTabArray();

        // Clone 3 tabs to the end to fill space when it is Left or Right TabDrawer
        if (additional) {
            TabArray tabArrayTemp2 = prepareTabArray();
            tabArrayTemp.addTab(tabArrayTemp2.getTab(3).setTitle("Add 1"));
            tabArrayTemp.addTab(tabArrayTemp2.getTab(2).setTitle("Add 2"));
            tabArrayTemp.addTab(tabArrayTemp2.getTab(1).setTitle("Add 3"));
        }

        final TabArray tabArray = tabArrayTemp;

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
                            intent.putExtra(NewActivity.POSITION, NewActivity.POSITION_BOTTOM);
                        else if (itemPosition == 2)
                            intent.putExtra(NewActivity.POSITION, NewActivity.POSITION_TOP);
                        else if (itemPosition == 3)
                            intent.putExtra(NewActivity.POSITION, NewActivity.POSITION_LEFT);
                        else if (itemPosition == 4)
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
