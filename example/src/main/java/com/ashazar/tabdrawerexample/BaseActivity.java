package com.ashazar.tabdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
        return new TabArray()
                .setCustomTabLayoutResourceId(R.layout.item_tab)
                .setTabBackgroundColor(Color.parseColor("#111111"))
                .setSelectedTabBackgroundColor(Color.parseColor("#333333"))
                .setInactiveSelectedTabBackgroundColor(Color.parseColor("#000000"))
                .setTabTitleColor(Color.parseColor("#ffffff"))
                .setTabTitleSize(12)

                .setTabListItemTextColor(Color.parseColor("#ffffff"))
                .setTabListItemTextSize(16)

                .addTab( new Tab()
                        .setTitle("Demo")
                        .setTitleSize(14)
                        .setTitleColor(Color.parseColor("#ffffff"))
                        .setSelectedTitleColor(Color.parseColor("#ff0000"))
                        .setInactiveSelectedTitleColor(Color.parseColor("#990000"))
                        .setDrawableId(R.drawable.n_activity)
                        .setIconColor(Color.parseColor("#ffffff"))
                        .setSelectedIconColor(Color.parseColor("#ff0000"))
                        .setInactiveSelectedIconColor(Color.parseColor("#990000"))
                        .setCustomDrawerLayoutResourceId(R.layout.drawerlayout_with_listview)
                        .setCustomDrawerListViewId(R.id.drawer_listview)
                        .addTabListItem( new TabListItem("Bottom/Left TabDrawer", R.drawable.ic_home_white_24dp) )
                        .addTabListItem( new TabListItem("Bottom TabDrawer", R.drawable.ic_action_collapse) )
                        .addTabListItem( new TabListItem("Top TabDrawer", R.drawable.ic_action_expand) )
                        .addTabListItem( new TabListItem("Left TabDrawer", R.drawable.ic_action_next_item) )
                        .addTabListItem( new TabListItem("Right TabDrawer", R.drawable.ic_action_previous_item) )
                )

                .addTab( new Tab()
                        .setCustomTabLayoutResourceId(R.layout.item_tab2)
                        .setTitle("Queue")
                        .setTitleColor(Color.parseColor("#333333"))
                        .setSelectedTitleColor(Color.parseColor("#ffffff"))
                        .setDrawableId(R.drawable.n_queue)
                        .setCustomDrawerListItemLayoutResourceId(R.layout.list_item)
                        .addTabListItem( new TabListItem("Add to Queue", R.drawable.ic_add_box_white_24dp ) )
                        .addTabListItem( new TabListItem("Archive", R.drawable.ic_archive_white_24dp) )
                        .addTabListItem( new TabListItem("Delete", R.drawable.ic_delete_forever_white_24dp) )
                )

                .addTab( new Tab()
                        .setCustomTabLayoutResourceId(R.layout.item_tab3)
                        .setBackgroundColor(Color.parseColor("#ff99ff"))
                        .setSelectedBackgroundColor(Color.parseColor("#99ff99"))
                        .setInactiveSelectedBackgroundColor(Color.parseColor("#55ff00"))
                        .setTitle("Chat")
                        .setDrawableId(R.drawable.n_chat)
                        .addTabListItem( new TabListItem("Friends", R.drawable.ic_face_white_24dp) )
                        .addTabListItem( new TabListItem("Add Friend", R.drawable.ic_person_add_white_24dp) )
                        .addTabListItem( new TabListItem("Start Group Chat", R.drawable.ic_people_white_24dp) )
                        .addTabListItem( new TabListItem("Funny Moments", R.drawable.ic_sentiment_very_satisfied_white_24dp) )
                )

                .addTab( new Tab()
                        .setCustomTabLayoutResourceId(R.layout.item_tab4)
                        .setBackgroundColor(Color.parseColor("#003366"))
                        .setSelectedBackgroundColor(Color.parseColor("#336699"))
                        .setInactiveSelectedBackgroundColor(Color.parseColor("#6699ff"))
                        .setTitle("Reports")
                        .setTitleSize(10)
                        .setDrawableId(R.drawable.n_report)
                        .addTabListItem( new TabListItem("Completed Jobs", R.drawable.ic_event_available_white_24dp) )
                        .addTabListItem( new TabListItem("Cancelled Jobs", R.drawable.ic_event_busy_white_24dp) )
                        .addTabListItem( new TabListItem("Customer Feedbacks", R.drawable.ic_feedback_white_24dp) )
                        .addTabListItem( new TabListItem("Documents", R.drawable.ic_description_white_24dp) )
                )

                .addTab( new Tab()
                        .forceDefaultLayout()
                        .setBackgroundColor(Color.parseColor("#990066"))
                        .setSelectedBackgroundColor(Color.parseColor("#660099"))
                        .setInactiveSelectedBackgroundColor(Color.parseColor("#6633ff"))
                        .setTitle(".more.")
                        .setCustomDrawerLayoutResourceId(R.layout.drawerlayout)
                        .addTabListItem( new TabListItem("General Settings", R.drawable.ic_settings_white_24dp) )
                        .addTabListItem( new TabListItem("My Account", R.drawable.ic_lock_white_24dp) )
                        .addTabListItem( new TabListItem("Accesibility", R.drawable.ic_accessibility_white_24dp) )
                        .addTabListItem( new TabListItem("Notifications", R.drawable.ic_notifications_white_24dp) )
                        .addTabListItem( new TabListItem("Bookmarks", R.drawable.ic_collections_bookmark_white_24dp) )
                        .addTabListItem( new TabListItem("Shared Folders", R.drawable.ic_folder_shared_white_24dp) )
                        .addTabListItem( new TabListItem("Cast to TV", R.drawable.ic_cast_white_24dp) )
                        .addTabListItem( new TabListItem("Other Applications", R.drawable.ic_apps_white_24dp) )
                );
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

                String text = tabArray.getTab(tabPosition).getTitle();

                if (tabArray.getTab(tabPosition).hasItems()) {
                    text += " -> "
                            + tabArray.getTab(tabPosition).getTabItemList().get(itemPosition).getTitle()
                            + " - ( " + tabPosition + ", " + itemPosition + " )";
                }
                else
                    text += " - ( " + tabPosition + " )";


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

            @Override
            public void setUnselectedTabView(LinearLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
                super.setUnselectedTabView(tabLayout, iconView, titleView, tabPosition);

                if (tabPosition == 1)
                    tabLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg1));
            }

            @Override
            public void setSelectedTabView(LinearLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition, RelativeLayout drawerLayout) {
                super.setSelectedTabView(tabLayout, iconView, titleView, tabPosition, drawerLayout);

                if (tabPosition == 1) {
                    tabLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg2));
                    drawerLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg1));
                }
            }

            @Override
            public void setInactiveSelectedTabView(LinearLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition) {
                super.setInactiveSelectedTabView(tabLayout, iconView, titleView, tabPosition);

                if (tabPosition == 1)
                    tabLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_bg1));
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
