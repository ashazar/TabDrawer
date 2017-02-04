package com.ashazar.tabdrawer.model;

/**
 * TabListItem model (Item of Tab's itemList) for TabDrawer
 * <p>
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabListItem {
    private String title = null;
    private int drawableId = -1;
    private boolean isSelected;


    /**
     * Instantiates a new TabListItem.
     *
     * @param itemTitle title text
     */
    public TabListItem(String itemTitle) {
        title = itemTitle;
    }

    /**
     * Instantiates a new TabListItem.
     *
     * @param iconDrawableId Drawable Resource Id of the icon
     */
    public TabListItem(int iconDrawableId) {
        drawableId = iconDrawableId;
    }

    /**
     * Instantiates a new TabListItem.
     *
     * @param itemTitle      title text
     * @param iconDrawableId Drawable Resource Id of the icon
     */
    public TabListItem(String itemTitle, int iconDrawableId) {
        title = itemTitle;
        drawableId = iconDrawableId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return (title != null) ? title : ""; }

    /**
     * Gets drawable id.
     *
     * @return the drawable id
     */
    public int getDrawableId() { return drawableId; }

    /**
     * Sets selected status.
     *
     * @param status the status
     */
    public void setSelected(boolean status) { this.isSelected = status; }

    /**
     * If item is selected.
     *
     * @return the boolean
     */
    public boolean isSelected() { return isSelected; }
}
