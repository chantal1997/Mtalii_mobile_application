package com.example.denni.mtalii;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.denni.mtalii.Database.Database;
import com.example.denni.mtalii.Model.Bookings;
import com.example.denni.mtalii.Model.Site;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SiteDetails extends AppCompatActivity {

    TextView site_name,site_fee,site_description;
    ImageView site_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String siteId="";

    FirebaseDatabase database;
    DatabaseReference site;
    Site currentSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_details);

        database = FirebaseDatabase.getInstance();
        site = database.getReference("Site");

        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart= (FloatingActionButton)findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Bookings(
                        siteId, currentSite.getName(),
                        numberButton.getNumber(),
                        currentSite.getPrice(),
                        currentSite.getDiscount()
                ));
                Toast.makeText(SiteDetails.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        site_description = (TextView)findViewById(R.id.site_description);
        site_name =(TextView)findViewById(R.id.site_name);
        site_fee =(TextView)findViewById(R.id.site_fee);
        site_image =(ImageView)findViewById(R.id.site_image);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if (getIntent()!=null)
            siteId = getIntent().getStringExtra("SiteId");
        if (!siteId.isEmpty() ) {

            getDetailSite(siteId);

        }
    }

    private void getDetailSite(String siteId) {
        site.child(siteId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentSite= dataSnapshot.getValue(Site.class);

                Picasso.with(getBaseContext()).load(currentSite.getImage()).into(site_image);
                 collapsingToolbarLayout.setTitle(currentSite.getName());
                 site_fee.setText(currentSite.getPrice());
                 site_name.setText(currentSite.getName());
                 site_description.setText(currentSite.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
