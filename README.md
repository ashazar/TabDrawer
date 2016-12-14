# TabDrawer
Navigation Tab Drawer for Android - Alternative to Hamburger Menu (Navigation Drawer)

![Demo](https://github.com/ashazar/TabDrawer/raw/master/images/demo.gif)

![Bottom_TabDrawer_1](https://github.com/ashazar/TabDrawer/raw/master/images/bottom_image1.png)
![Bottom_TabDrawer_2](https://github.com/ashazar/TabDrawer/raw/master/images/bottom_image2.png)
![Bottom_TabDrawer_3](https://github.com/ashazar/TabDrawer/raw/master/images/bottom_image3.png)

![Left_TabDrawer_3](https://github.com/ashazar/TabDrawer/raw/master/images/left_image3.png)


After seeing the design in Scott Jensen's article titled *Designing an Alternative to the Hamburger Menu*, loved the idea.
*http://scottjensen.design/2016/04/designing-an-alternative-to-the-hamburger-menu*


### Adding TabDrawer Library
**PS:** *Including library as a Gradle dependency coming soon*

* Download the repository.
* Click `File -> New -> Import Module` on Android Studio and select `tabDrawer` library folder. Set the module name as "**tabDrawer**". Android Studio will automatically import the module and add required lines to `settings.gradle` file.
* Then update your dependencies in your app's `build.gradle` file:
```
dependencies {
    compile project(':tabDrawer')
}
```

### Using TabDrawer
##### Layout
* Set your root layout as `RelativeLayout`. *( Haven't tested in others yet... )*
* Add `xmlns:tab="http://schemas.android.com/apk/res-auto"` namespace definition in RelativeLayout, in order to use TabDrawerLayout's own attributes.
```
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tab="http://schemas.android.com/apk/res-auto"
    ...
```
* Place TabDrawerLayout as last child of root RelativeLayout.
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
        tab:list_padding="16dp"
        tab:titleSize="6sp"
        tab:titleColor="@color/tabTitle"
        tab:titleColor_selected="@color/tabTitle_selected"
        tab:backgroundColor="@color/tabBackground"
        tab:backgroundColor_selected="@color/tabBackground_selected"
        />
```

| Attribute                 | Explanation                                         |
|---------------------------|-----------------------------------------------------|
| **NameSpace: android**    |                                                     |
| **layout_width**          | "match_parent" for Top/Bottom TabDrawer; "wrap_content" for Left/Right TabDrawer |
| **layout_height**         | "wrap_content" for Top/Bottom TabDrawer; "match_parent" for Left/Right TabDrawer |
|                           |                                                     |
| **NameSpace: tab**        |                                                     |
| **topBarPosition**        | top / bottom / left / right                         |
| **size_tabBar**           | Size (in 'dp') of the TabBar only.                  |
|                           | Height for Top / Bottom TabDrawer                   |
|                           | Width for Left / Right TabDrawer                    |
| **size_Total**            | Size (in 'dp') of the TabBar & Drawer (when opened) |
|                           | Height for Top / Bottom TabDrawer; Width for Left / Right TabDrawer |
| **defaultSelectedTab**    | Default highlighted Tab number. (integer)         |
|                           | Human-readable ;-)  1 for first Tab. (not 0)      |
| **padding**               | Padding of the Tab itself. (in 'dp')              |
|                           | Can also use  **paddingTop**, **paddingBottom**,  **paddingLeft**, **paddingRight** |
| **list_padding**          | Padding for the Drawer's ListView (in 'dp')       |
|                           | Can also use  **list_paddingTop**, **list_paddingBottom**,  **list_paddingLeft**, **list_paddingRight** |
| **titleSize**             | Size of the Tab's title text. (in 'sp')           |
| **titleColor**            | Color of the Tab's title text (color)            |
| **titleColor_selected**   | Color of the Tab's title text, when Tab is selected  |
| **backgroundColor**       | Tab's background color |
| **backgroundColor_selected** | Tab's background color, when Tab is selected |


##### In Your Code
* Prepare **TabArray**, object that holds all Tabs and Tabs' list items.

 * Tabs can be Icon only, Text only, or Icon and Text (as in the sample app.)
 * Tabs can have item lists (TabDetailItem), or just Tab only (Standard tab; no drawer opens, will be selected immediately when clicked)
 * Tab list items can be Text only, or Icon and Text (as in the sample app.)
```
TabArray tabArray = new TabArray()
                .setTabListItemTextColor(Color.parseColor("#ffffff"))
                .setTabListItemTextSize(16)

                .addTab( new Tab()
                        .setTitle("Demo")
                        .setDrawableId(R.drawable.n_activity)
                        .setSelectedDrawableId(R.drawable.s_activity)
                        .addTabListItem( new TabListItem("Bottom/Left TabDrawer") )
                        .addTabListItem( new TabListItem("Bottom TabDrawer", R.drawable.ic_action_collapse) )
                )

                .addTab( new Tab()
                        .setTitle("Queue")
                        .setDrawableId(R.drawable.n_queue)
                        .setSelectedDrawableId(R.drawable.s_queue)
                        .setTabListItemTextColor(Color.parseColor("#ff0000"))
                        .setTabListItemTextSize(22)
                        .addTabListItem( new TabListItem("Add to Queue", R.drawable.ic_add_box_white_24dp ) )
                        .addTabListItem( new TabListItem("Archive", R.drawable.ic_archive_white_24dp) )
                );
```
| Method | Explanation|
|--------|------------|
| **TabArray** | OBJECT |
| **.setTabListItemTextColor()** | Set the color of the text of all list items in Drawer |
| **.setTabListItemTextSize()**  | Set the size of the text of all list items in Drawer  |
| **.addTab()**  | Add a new Tab  |
| | |
| **Tab** | OBJECT |
| **.setTitle()** | Set Tab's title text |
| **.setDrawableId()** | Set Tab's icon |
| **.setSelectedDrawableId()** | Set selected/highlighted Tab's icon |
| **.setTabListItemTextColor()** | Set the color of the text of that tab's list items in Drawer |
| **.setTabListItemTextSize()**  | Set the size of the text of that tab's list items in Drawer  |
| **.addTabListItem()** | Add list item to that tab's drawer |
| | |
| **TabListItem** | OBJECT |
| | Instantiates with `new TabListItem(Title)` or `new TabListItem(Title, Icon)`  |



### TODO
- [ ] Different background colors for each Tab, and Drawer.
- [ ] 2 or 3 Column lists in Drawer.
- [ ] Custom Tab layout
- [ ] Custom Drawer layout (?)
- [ ] Custom ListView item layout resource (for Adapter)


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
