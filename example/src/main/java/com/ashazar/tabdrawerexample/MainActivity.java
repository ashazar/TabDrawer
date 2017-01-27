package com.ashazar.tabdrawerexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

//public class MainActivity extends BaseActivity {
public class MainActivity extends AppCompatActivity {
    private ArrayList<RelativeLayout> drawerLayoutList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity_main);

        prepareTabDrawer();
        */




        drawerLayoutList = new ArrayList<>();
        for (int i = 0; i < tabCount; i++) {
            RelativeLayout layout = prepareItemListContainerView(i);
            drawerLayoutList.add(layout);
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(layoutManager);

        DrawerRecyclerViewAdapter adapter = new DrawerRecyclerViewAdapter(context, drawerLayoutList);
        recyclerView.setAdapter(adapter);


        tabListContainer.addView(recyclerView);


    }

    public class DrawerRecyclerViewAdapter extends RecyclerView.Adapter<DrawerRecyclerViewAdapter.DrawerViewHolder> {
        private ArrayList<RelativeLayout> drawerList;
        private Context mContext;

        public DrawerRecyclerViewAdapter(Context context, ArrayList<RelativeLayout> drawerList) {
            this.drawerList = drawerList;
            this.mContext = context;
        }

        @Override
        public int getItemViewType(int position) {
            //return super.getItemViewType(position);
            return position;
        }

        @Override
        public DrawerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            /*
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
            DrawerViewHolder viewHolder = new DrawerViewHolder(view);
            */
            View view = drawerList.get(i);
            DrawerViewHolder viewHolder = new DrawerViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(DrawerViewHolder customViewHolder, int i) {
            RelativeLayout drawer = drawerList.get(i);

            //customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        }

        @Override
        public int getItemCount() {
            return (null != drawerList ? drawerList.size() : 0);
        }

        class DrawerViewHolder extends RecyclerView.ViewHolder {
            //protected ImageView imageView;
            //protected TextView textView;

            public DrawerViewHolder(View view) {
                super(view);
                //this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
                //this.textView = (TextView) view.findViewById(R.id.title);
            }
        }
    }

}
