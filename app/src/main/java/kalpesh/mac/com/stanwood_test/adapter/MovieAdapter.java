package kalpesh.mac.com.stanwood_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kalpesh.mac.com.stanwood_test.R;
import kalpesh.mac.com.stanwood_test.model.data.Item;
import kalpesh.mac.com.stanwood_test.utilities.ItemClickListener;


/**
 * Created by Cameron Stobie - Waracle tech test  on 10/14/2015.
 */


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

//    private List<Image> images;
    private int rowLayout;
    private Context mContext;
//    List<Section> sections;
    private List<Item> items;


    public MovieAdapter(List<Item> objects, int rowLayout, Context context) {
        this.items = objects;
        this.rowLayout = rowLayout;
        this.mContext = context;
        Log.i("Value", "first");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.i("Value", "second");
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(rowLayout, viewGroup, false);
        return new ViewHolder(view, i);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        Section res = sections.get(i);
//        items=res.getItems();
//        images=items.get(i).getImages();
        Log.i("Value", items.get(i).getTitle());
        Log.i("Value", "url " + items.get(i).getImages().get(0).getUrl());
//                viewHolder.cakeName.setText(items.get(i).getTitle());
//                Picasso.with(mContext)
//                        .load(items.get(i).getImages().get(0).getUrl()).resize(200,250).centerCrop().into(viewHolder.cakeImage);
        if (i > 0) {
            Glide.with(mContext).load(items.get(i).getImages().get(0).getUrl()).into(viewHolder.cakeImage);
        }

        viewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //   Toast.makeText(mContext, "#" + position + " - " + res.getTitle() + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    //  Toast.makeText(mContext, "#" + position + " - " + res.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        //   return cakesList == null ? 0 : cakesList.();
        return items.isEmpty() ? 0 : items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView cakeName;
        public ImageView cakeImage;
        public String versionName;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
//            cakeName = (TextView) itemView.findViewById(R.id.name);
            cakeImage = (ImageView)itemView.findViewById(R.id.img);
//            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }

    }
}
