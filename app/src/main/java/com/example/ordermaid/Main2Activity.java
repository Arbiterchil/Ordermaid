package com.example.ordermaid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class Main2Activity extends AppCompatActivity {

    TabLayout tabs;
    AppBarLayout apps;
    ViewPager views;
    ImageView askme,dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(this, "When Done Picking Click the Anime Icon for Totals", Toast.LENGTH_LONG).show();


        tabs = findViewById(R.id.tabsad);
        apps = findViewById(R.id.appbars);
        views = findViewById(R.id.viewpager);
        Viewpage viewpage = new Viewpage(getSupportFragmentManager());
        viewpage.Addfrag(new Burgers()," Burgers");
        viewpage.Addfrag(new Beverages()," Beverages");
        viewpage.Addfrag(new BCombomeal()," Combo Meals");
        views.setAdapter(viewpage);
        tabs.setupWithViewPager(views);


        dis = findViewById(R.id.dis);

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Ni = new Intent(Main2Activity.this,TotalOrder.class);
                startActivity(Ni);

            }
        });


    }
}
