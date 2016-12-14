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
import com.ashazar.tabdrawer.model.TabListItem;

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
                .setTabListItemTextColor(Color.parseColor("#ffffff"))
                .setTabListItemTextSize(16)

                .addTab( new Tab()
                        .setTitle("Demo")
                        .setDrawableId(R.drawable.n_activity)
                        .setSelectedDrawableId(R.drawable.s_activity)
                        .addTabListItem( new TabListItem("Bottom/Left TabDrawer", R.drawable.ic_home_white_24dp) )
                        .addTabListItem( new TabListItem("Bottom TabDrawer", R.drawable.ic_action_collapse) )
                        .addTabListItem( new TabListItem("Top TabDrawer", R.drawable.ic_action_expand) )
                        .addTabListItem( new TabListItem("Left TabDrawer", R.drawable.ic_action_next_item) )
                        .addTabListItem( new TabListItem("Right TabDrawer", R.drawable.ic_action_previous_item) )
                )

                .addTab( new Tab()
                        .setTitle("Queue")
                        .setDrawableId(R.drawable.n_queue)
                        .setSelectedDrawableId(R.drawable.s_queue)
                        .addTabListItem( new TabListItem("Add to Queue", R.drawable.ic_add_box_white_24dp ) )
                        .addTabListItem( new TabListItem("Archive", R.drawable.ic_archive_white_24dp) )
                        .addTabListItem( new TabListItem("Delete", R.drawable.ic_delete_forever_white_24dp) )
                )

                .addTab( new Tab()
                        .setTitle("Chat")
                        .setDrawableId(R.drawable.n_chat)
                        .setSelectedDrawableId(R.drawable.s_chat)
                        .addTabListItem( new TabListItem("Friends", R.drawable.ic_face_white_24dp) )
                        .addTabListItem( new TabListItem("Add Friend", R.drawable.ic_person_add_white_24dp) )
                        .addTabListItem( new TabListItem("Start Group Chat", R.drawable.ic_people_white_24dp) )
                        .addTabListItem( new TabListItem("Funny Moments", R.drawable.ic_sentiment_very_satisfied_white_24dp) )
                )

                .addTab( new Tab()
                        .setTitle("Reports")
                        .setDrawableId(R.drawable.n_report)
                        .setSelectedDrawableId(R.drawable.s_report)
                        .addTabListItem( new TabListItem("Completed Jobs", R.drawable.ic_event_available_white_24dp) )
                        .addTabListItem( new TabListItem("Cancelled Jobs", R.drawable.ic_event_busy_white_24dp) )
                        .addTabListItem( new TabListItem("Customer Feedbacks", R.drawable.ic_feedback_white_24dp) )
                        .addTabListItem( new TabListItem("Documents", R.drawable.ic_description_white_24dp) )
                )

                .addTab( new Tab()
                        .setTitle("Settings")
                        .setDrawableId(R.drawable.n_settings)
                        .setSelectedDrawableId(R.drawable.s_settings)
                        .addTabListItem( new TabListItem("General", R.drawable.ic_settings_white_24dp) )
                        .addTabListItem( new TabListItem("My Account", R.drawable.ic_lock_white_24dp) )
                        .addTabListItem( new TabListItem("Accesibility", R.drawable.ic_accessibility_white_24dp) )
                        .addTabListItem( new TabListItem("Notifications", R.drawable.ic_notifications_white_24dp) )
                        .addTabListItem( new TabListItem("Bookmarks", R.drawable.ic_collections_bookmark_white_24dp) )
                        .addTabListItem( new TabListItem("Shared Folders", R.drawable.ic_folder_shared_white_24dp) )
                        .addTabListItem( new TabListItem("Cast to TV", R.drawable.ic_cast_white_24dp) )
                        .addTabListItem( new TabListItem("Other Applications", R.drawable.ic_apps_white_24dp) )
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
