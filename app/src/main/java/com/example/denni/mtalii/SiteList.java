package com.example.denni.mtalii;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.denni.mtalii.Interface.ItemClickListener;
import com.example.denni.mtalii.Model.Site;
import com.example.denni.mtalii.ViewHolder.SiteViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SiteList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference sitelist;

    String MenuId="";
    FirebaseRecyclerAdapter<Site,SiteViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list);

        database = FirebaseDatabase.getInstance();
        sitelist = database.getReference("Site");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_site);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //getting intent
        if(getIntent()!= null)
        MenuId = getIntent().getStringExtra("MenuId");
        if (!MenuId.isEmpty() && MenuId !=null) {
            loadListSite(MenuId);
        }

    }

    private void loadListSite(String menuId) {
        adapter = new FirebaseRecyclerAdapter<Site, SiteViewHolder>(Site.class,
                R.layout.siteitem,
                SiteViewHolder.class,
                sitelist.orderByChild("MenuId").equalTo(menuId)) {
            @Override
            protected void populateViewHolder(SiteViewHolder viewHolder, Site model, int position) {
               viewHolder.site_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.site_image);

                final Site local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent siteDetails = new Intent(SiteList.this,SiteDetails.class);
                        siteDetails.putExtra("SiteId",adapter.getRef(position).getKey());
                        startActivity(siteDetails);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }


}
