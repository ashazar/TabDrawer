# TabDrawer
Android Navigation Tab Bar with Drawer - Alternative to Navigation Drawer *(Hamburger Menu)*


| ![Demo](https://github.com/ashazar/TabDrawer/raw/master/images/tabdrawer_demo.gif) | ![Demo_TabBar](https://github.com/ashazar/TabDrawer/raw/master/images/tabdrawer_demo_tabbar.gif) | ![Demo_Custom_TabDrawer](https://github.com/ashazar/TabDrawer/raw/master/images/tabdrawer_demo_custom.gif) |
|----------|----------------------|--------------------|
| **Demo** | **Standard Tab Bar** | **Custom Layouts** |


TabDrawer is an Open Source library for Android apps; combining the Navigation Tab Bar and a much user-friendly alternative to Navigation Drawer (Hamburger Menu)

You can easily add a fully customized Navigation Tab Bar (Bottom/Top/Left/Right), and a drawer for each tab that contains lists for navigating to different sections of the app.

---


### Adding TabDrawer Library
**Gradle**  *(through JCenter)*

Simply add `compile 'com.ashazar.tabdrawer:tabdrawer:1.1.0'` in *dependencies* in your app's `build.gradle` file
```
dependencies {
    compile 'com.ashazar.tabdrawer:tabdrawer:1.1.0'
}
```



### Using TabDrawer
##### Layout
* Set your root layout as `RelativeLayout`.
* Add `xmlns:tab="http://schemas.android.com/apk/res-auto"` namespace definition in RelativeLayout, in order to use TabDrawerLayout's own attributes.
```
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tab="http://schemas.android.com/apk/res-auto"
    ...
```
* Place TabDrawerLayout as last child of root RelativeLayout. *(Attribute explanations table for TabDrawerLayout is below the instructions)*
```
    <com.ashazar.tabdrawer.TabDrawerLayout
        android:id="@+id/tabDrawer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        tab:tabBarPosition="bottom"
        tab:size_tabBar="48dp"
        tab:size_Total="240dp"
        tab:defaultSelectedTab="1"
        tab:padding="3dp"
        tab:drawer_padding="2dp"
        tab:list_padding="10dp"
        />
```



##### In Your Code
* Prepare **TabDrawerData**, object that holds all Tabs and Tabs' list items, as well as their properties (color, background color, etc.).
*(Method explanations table for TabDrawerData, Tab and TabListItem is below the instructions)*

 * **Tabs can be:**  **(a)** Icon only, **(b)** Text only, or **(c)** Icon and Text *(as in the sample app.)*
 * **Tabs can:**  **(a)** have item lists *(TabListItem)*, or  **(b)** Tab only *(act as a normal tab in a standard Tab Bar; no drawer opens, will be selected immediately, when clicked)*
 * **Tab list items can be:**  **(a)** Text only, or  **(b)** Icon and Text *(as in the sample app.)*
```
TabDrawerData tabDrawerData = new TabDrawerData()
                .setTabIconColors(
                        Color.parseColor("#3199ff"),
                        Color.parseColor("#ffffff")
                )
                .setTabTitleSize(12)
                .setTabTitleColors(
                        ContextCompat.getColor(context, R.color.tabTitle),
                        ContextCompat.getColor(context, R.color.tabTitle_selected),
                        Color.parseColor("#CCCCCC")
                )
                .setTabBackgroundColors(
                        ContextCompat.getColor(context, R.color.tabBackground),
                        ContextCompat.getColor(context, R.color.tabBackground_selected)
                )
                .setTabListItemTitleColors(Color.parseColor("#ffffff"))
                .setTabListItemTitleSize(16)

                .addTab( new Tab()
                        .setTitle("Demo")
                )

                .addTab( new Tab()
                        .setTitle("Queue")
                        .setIconImage(R.drawable.n_queue)
                        .setDrawerListColumnNumber(2)
                        .addTabListItem( new TabListItem("Add to Queue", R.drawable.ic_add_box_white_24dp ) )
                        .addTabListItem( new TabListItem("Archive", R.drawable.ic_archive_white_24dp) )
                );

```


* Prepare TabDrawer and Initialize
 * TabDrawer takes following arguments: Context, Activity, TabDrawerLayout's Id, and TabDrawerData
 * You need to override `onTabDrawerClicked()` to get clicked Tab's and item's positions.
 * `onTabDrawerClicked()` only passes the clicks you need. So you don't have to do checks for open/close drawer; or whether the Tab or item already selected.
 * Call `initialize()`
```
TabDrawer tabDrawer = new TabDrawer(context, activity, R.id.tabDrawer, tabArray) {
            @Override
            public void onTabDrawerClicked(int tabPosition, int itemPosition) {
                super.onTabDrawerClicked(tabPosition, itemPosition);
            }
        };

        tabDrawer.initialize();
   ```

If you want more customization, you can override below methods to modify the views as you want.
For Tabs:
* `setUnselectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition)`
* `setSelectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition)`
* `setInactiveSelectedTabView(RelativeLayout tabLayout, ImageView iconView, TextView titleView, int tabPosition)`

For List Items in Drawer:
* `setUnselectedListItemView(int tabPosition, int itemPosition, View view, ImageView iconView, TextView titleView)`
* `setSelectedListItemView(int tabPosition, int itemPosition, View view, ImageView iconView, TextView titleView)`

You can override `onBackPressed()` to close Drawer when pressed 'Back'.
```
@Override
public void onBackPressed() {
    if (tabDrawer.isDrawerOpen())
        tabDrawer.closeDrawer();
    else
        super.onBackPressed();
}
 ```

---

##### Attributes for TabDrawerLayout

| Attribute                    | Mandatory | Explanation                                                                                             |
|------------------------------|-----------|---------------------------------------------------------------------------------------------------------|
| **NameSpace: android**       |           |                                                                                                         |
| **layout_width**             | yes       | "match_parent" for Top/Bottom TabDrawer; "wrap_content" for Left/Right TabDrawer                        |
| **layout_height**            | yes       | "wrap_content" for Top/Bottom TabDrawer; "match_parent" for Left/Right TabDrawer                        |
|                              |           |                                                                                                         |
| **NameSpace: tab**           |           |                                                                                                         |
| **topBarPosition**           | yes       | top / bottom / left / right                                                                             |
| **size_tabBar**              | yes       | Size (in 'dp') of the TabBar only.                                                                      |
|                              |           | Height for Top / Bottom TabDrawer; Width for Left / Right TabDrawer                                     |
| **size_Total**               | yes       | Size (in 'dp') of the TabBar & Drawer (when opened)                                                     |
|                              |           | Height for Top / Bottom TabDrawer; Width for Left / Right TabDrawer                                     |
| **defaultSelectedTab**       | no        | Initial highlighted Tab number. *(default: 1)* (integer)                                                |
|                              |           | **1** for first Tab. *(not 0)*                                                                          |
| **padding**                  | no        | Padding of the Tab itself. (in 'dp')                                                                    |
|                              |           | Can also use  **paddingTop**, **paddingBottom**,  **paddingLeft**, **paddingRight**                     |
| **drawer_padding**           | no        | Padding for the Drawer (in 'dp')                                                                        |
|                              |           | Can also use  **list_paddingTop**, **list_paddingBottom**,  **list_paddingLeft**, **list_paddingRight** |
| **list_padding**             | no        | Padding for the Drawer's GridView (in 'dp')                                                             |
|                              |           | Can also use  **list_paddingTop**, **list_paddingBottom**,  **list_paddingLeft**, **list_paddingRight** |


##### Methods forTabDrawerData, Tab, TabListItem

| Method                                       | Explanation                                                                            | Argument Type       |
|----------------------------------------------|----------------------------------------------------------------------------------------|---------------------|
| **TabDrawerData**                            | OBJECT                                                                                 |                     |
| `.setTabBackgroundColors()`                  | Sets the background colors of all Tabs *(default, selected, inactiveSelected)*         | int *(,int) (,int)* |
| `.setTabTitleColors()`                       | Sets title colors of all Tabs *(default, selected, inactiveSelected)*                  | int *(,int) (,int)* |
| `.setTabTitleSize()`                         | Sets title size for all Tabs                                                           | int (sp)            |
| `.setTabTitleFont()`                         | Sets Typeface of the title for all Tabs                                                | TypeFace            |
| `.setTabIconColors()`                        | Sets tab icon colors of all Tabs *(default, selected, inactiveSelected)*               | int *(,int) (,int)* |
| `.setAnimateScaleTabIconWhenSelected()`      | Sets if to animate and scale up the icon, when tab is selected *(default: true)*       | boolean             |
| `.setTabIconScaleValueWhenSelected()`        | Sets the scale value of selected tab's icon. *(default: 1.2f)*                         | float               |
| `.setBoldTabTitleWhenSelected()`             | Sets if to make the title bold, when tab is selected *(default: true)*                 | boolean             |
| `.setCustomTabLayoutResourceId()`            | Sets the Custom Layout Resource Id of all tabs                                         | int                 |
| `.dontUseDefaultTabViewSettings()`           | Sets if the developer wants to reset and override the default Tab view settings        | *void*              |
| *below methods are for drawer and lists*     |                                                                                        |                     |
| `.setCustomDrawerLayoutResourceId()`         | Sets the custom drawer layout *(RelativeLayout)* resource Id for all tabs              | int                 |
| `.setDrawerListColumnNumber()`               | Sets the number of columns in GridView in the drawer (default: 1)                      | int                 |
| `.setCustomDrawerGridViewId()`               | Sets the id for the *custom GridView* inside the *custom drawer layout*                | int                 |
| `.setCustomDrawerListItemLayoutResourceId()` | Sets the resource id for the custom item layout for GridView inside the drawer         | int                 |
| `.setTabListItemTitleColors()`               | Sets tab list item's title color of all Tabs *(default, selected)*                     | int *(,int)*        |
| `.setTabListItemTitleSize()`                 | Sets tab item list text size                                                           | int (sp)            |
| `.setTabListItemTitleFont()`                 | Sets Typeface of the Tab List item's Title                                             | TypeFace            |
| `.dontUseDefaultTabListAdapterViewSettings()`| Sets if the developer wants to reset and override the default List item view settings  | *void*              |
| `.addTab()`                                  | Add a new Tab                                                                          | Tab                 |
|                                              |                                                                                        |                     |
| **Tab**                                      | OBJECT                                                                                 |                     |
| `.setTitle()`                                | Set Tab's title text                                                                   | String              |
| `.setIconImage()`                            | Set Tab's icons (drawableIds)  *(default, selected, inactiveSelected)*                 | int *(,int) (,int)* |
| `.setCustomTabLayoutResourceId()`            | Sets the Custom Layout Resource Id of tab                                              | int                 |
| `.forceDefaultLayout()`                      | Force using default layout, if a custom layout set in TabDrawerData                    | *void*              |
| `.setBackgroundColors()`                     | Sets the background colors of the tab *(default, selected, inactiveSelected)*          | int *(,int) (,int)* |
| `.setTabTitleColors()`                       | Sets title colors of the Tab *(default, selected, inactiveSelected)*                   | int *(,int) (,int)* |
| `.setTitleSize()`                            | Sets title size of the tab                                                             | int (sp)            |
| `.setTitleFont()`                            | Sets Typeface of the title of the tab                                                  | TypeFace            |
| `.setIconColors()`                           | Sets tab icon colors of the Tab *(default, selected, inactiveSelected)*                | int *(,int) (,int)* |
| `.setAnimateScaleIconWhenSelected()`         | Sets if to animate and scale up the icon, when tab is selected *(default: true)*       | boolean             |
| `.setIconScaleValueWhenSelected()`           | Sets the scale value of selected tab's icon. *(default: 1.2f)*                         | float               |
| `.setBoldTitleWhenSelected()`                | Sets if to make the title bold, when tab is selected *(default: true)*                 | boolean             |
| `.dontUseDefaultTabViewSettings()`           | Sets if the developer wants to reset and override the default Tab view settings        | *void*              |
| *below methods are for drawer and lists*     |                                                                                        |                     |
| `.addTabListItem()`                          | Add list item to that tab's drawer                                                     | *void*              |
| `.setCustomDrawerLayoutResourceId()`         | Sets the custom drawer layout *(RelativeLayout)* resource Id for the tab               | int                 |
| `.setDrawerListColumnNumber()`               | Sets the number of columns in GridView in the drawer *(default: 1)*                    | int                 |
| `.setCustomDrawerGridViewId()`               | Sets the id for the custom GridView inside the custom drawer layout                    | int                 |
| `.setCustomDrawerListItemLayoutResourceId()` | Sets the resource id for the custom item layout for GridView inside the drawer         | int                 |
| `.forceDefaultDrawerLayout()`                | Force using default drawer layout, if a custom layout set in TabDrawerData             | *void*              |
| `.setListItemTitleColors()`                  | Sets tab list item's title colors *(default, selected, inactiveSelected)*              | int *(,int)*        |
| `.setListItemTitleSize()`                    | Sets tab item list text size.                                                          | int (sp)            |
| `.setListItemTitleFont()`                    | Sets Typeface of the List item's Title                                                 | TypeFace            |
| `.dontUseDefaultListAdapterViewSettings()`   | Sets if the developer wants to reset and override the default List item view settings  | *void*              |
|                                              |                                                                                        |                     |
| **TabListItem**                              | OBJECT                                                                                 |                     |
|                                              | Instantiates with                                                                      |                     |
|                                              | `TabListItem(Title)`,  `TabListItem(Icon)` or `TabListItem(Title, Icon)`               |                     |



TabDrawer idea inspired by Scott Jensen's article [Designing an Alternative to the Hamburger Menu](http://scottjensen.design/2016/04/designing-an-alternative-to-the-hamburger-menu)


### License
> MIT License
> 
> Copyright (c) 2016 Serdar Hazar
> 
> Permission is hereby granted, free of charge, to any person obtaining a copy
> of this software and associated documentation files (the "Software"), to deal
> in the Software without restriction, including without limitation the rights
> to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
> copies of the Software, and to permit persons to whom the Software is
> furnished to do so, subject to the following conditions:
> 
> The above copyright notice and this permission notice shall be included in all
> copies or substantial portions of the Software.
> 
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
> IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
> FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
> AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
> LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
> OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
> SOFTWARE.
> 
