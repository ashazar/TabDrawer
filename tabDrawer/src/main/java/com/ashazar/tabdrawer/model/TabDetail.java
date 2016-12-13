package com.ashazar.tabdrawer.model;

/**
 * TabDetail model (Item of Tab's itemList) for TabDrawer
 * <p>
 * Created by Serdar Hazar on 26/04/16.
 */
public class TabDetail {
    private String title;
    private int drawableId = -1;
    private boolean isSelected;
    private int textColor = 0;
    private int textSize = 16;

    /**
     * Instantiates a new TabDetail.
     */
    public TabDetail() { }

    /**
     * Instantiates a new TabDetail.
     *
     * @param itemTitle title text
     */
    public TabDetail(String itemTitle) {
        title = itemTitle;
    }

    /**
     * Instantiates a new TabDetail.
     *
     * @param itemTitle      title text
     * @param iconDrawableId Drawable Resource Id of the icon
     */
    public TabDetail(String itemTitle, int iconDrawableId) {
        title = itemTitle;
        drawableId = iconDrawableId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets drawable id.
     *
     * @return the drawable id
     */
    public int getDrawableId() { return drawableId; }

    /**
     * Sets drawable id.
     *
     * @param drawableId the drawable id
     */
    public void setDrawableId(int drawableId) { this.drawableId = drawableId; }

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

    /**
     * Sets text color.
     *
     * @param color the color
     */
    public void setTextColor(int color) { textColor = color; }

    /**
     * Gets text color.
     *
     * @return the text color
     */
    public int getTextColor() { return textColor; }

    /**
     * Sets text size.
     *
     * @param size the size
     */
    public void setTextSize(int size) { textSize = size; }

    /**
     * Gets text size.
     *
     * @return the text size
     */
    public int getTextSize() { return textSize; }
}
