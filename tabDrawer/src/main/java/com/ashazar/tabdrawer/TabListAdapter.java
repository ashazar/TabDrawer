package com.ashazar.tabdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashazar.tabdrawer.model.TabDetail;

import java.util.ArrayList;

/**
 * Created by Serdar Hazar on 12/7/16.
 */

class TabListAdapter extends ArrayAdapter<TabDetail> {

    TabListAdapter(Context context, ArrayList<TabDetail> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tab_detail_item, parent, false);
        }

        TabDetail item = getItem(position);
        String title = item.getTitle();
        int imgDrawableId = item.getDrawableId();
        boolean isSelected = item.isSelected();

        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_img);
        TextView titleView= (TextView) convertView.findViewById(R.id.list_item_title);

        if (imgDrawableId == -1) {
            imageView.setVisibility(View.GONE);
        }
        else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(imgDrawableId);

            if (isSelected)
                imageView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, item.getTextSize() + 10, getContext().getResources().getDisplayMetrics());
            else
                imageView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, item.getTextSize() + 8, getContext().getResources().getDisplayMetrics());

            imageView.requestLayout();

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(20, 0, 0, 0);
            titleView.setLayoutParams(layoutParams);
        }

        titleView.setText(title);
        titleView.setTextColor(item.getTextColor());
        if (isSelected) {
            titleView.setTypeface(null, Typeface.BOLD);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, item.getTextSize() + 1);
        }
        else {
            titleView.setTypeface(null, Typeface.NORMAL);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, item.getTextSize());
        }

        titleView.requestLayout();

        return convertView;
    }
}
