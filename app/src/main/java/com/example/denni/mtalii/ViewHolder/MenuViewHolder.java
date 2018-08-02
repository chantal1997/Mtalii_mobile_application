package com.example.denni.mtalii.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denni.mtalii.Interface.ItemClickListener;
import com.example.denni.mtalii.R;

/**
 * Created by denni on 30/11/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
     txtMenuName = (TextView)itemView.findViewById(R.id.menu_name);
     imageView = (ImageView)itemView.findViewById(R.id.menu_image);

     itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
