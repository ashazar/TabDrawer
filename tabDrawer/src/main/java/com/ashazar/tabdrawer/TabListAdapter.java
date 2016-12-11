package com.ashazar.tabdrawer;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        //return super.getView(position, convertView, parent);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tab_detail_item, parent, false);
        }

        TabDetail item = getItem(position);
        String title = item.getTitle();
        int imgDrawableId = item.getDrawableId();
        boolean isSelected = item.isSelected();

        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_img);
        TextView titleView= (TextView) convertView.findViewById(R.id.list_item_title);
        TextView titleOnlyView= (TextView) convertView.findViewById(R.id.list_item_title_only);

        if (imgDrawableId == -1) {
            imageView.setVisibility(View.GONE);
            titleView.setVisibility(View.GONE);

            titleOnlyView.setVisibility(View.VISIBLE);
            titleOnlyView.setText(title);
            titleOnlyView.setTextColor(item.getTextColor());
            titleOnlyView.setTextSize(item.getTextSize());

            if (isSelected)
                titleOnlyView.setTypeface(null, Typeface.BOLD);
            else
                titleOnlyView.setTypeface(null, Typeface.NORMAL);
        }
        else {
            imageView.setImageResource(imgDrawableId);
            titleView.setText(title);
            if (isSelected)
                titleView.setTypeface(null, Typeface.BOLD);
            else
                titleView.setTypeface(null, Typeface.NORMAL);
        }


        return convertView;
    }
}
