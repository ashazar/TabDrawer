# TabDrawer
Navigation Tab Drawer for Android - Alternative to Hamburger Menu (Navigation Drawer)

![Demo](https://github.com/ashazar/TabDrawer/raw/development/images/demo.gif)

![Bottom_TabDrawer_1](https://github.com/ashazar/TabDrawer/raw/development/images/bottom_image1.png)
![Bottom_TabDrawer_2](https://github.com/ashazar/TabDrawer/raw/development/images/bottom_image2.png)
![Bottom_TabDrawer_3](https://github.com/ashazar/TabDrawer/raw/development/images/bottom_image3.png)

![Left_TabDrawer_3](https://github.com/ashazar/TabDrawer/raw/development/images/left_image3.png)


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
        tab:height_tabBar="48dp"
        tab:height_Total="240dp"
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

| Attribute              | Type                | Explanation                      |NameSpace|
|------------------------|---------------------|----------------------------------|---------|
|                        |                     |                                  |         |


### TODO