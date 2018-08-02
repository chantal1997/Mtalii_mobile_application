package com.example.denni.mtalii.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denni.mtalii.Interface.ItemClickListener;
import com.example.denni.mtalii.R;

/**
 * Created by denni on 12/12/2017.
 */

public class SiteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView site_name;
    public ImageView site_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SiteViewHolder(View itemView) {
        super(itemView);

        site_name = (TextView)itemView.findViewById(R.id.site_name);
        site_image = (ImageView)itemView.findViewById(R.id.site_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
