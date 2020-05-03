package com.sudirman.aplikasifootball.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.sudirman.aplikasifootball.R;
import com.sudirman.aplikasifootball.adapter.LeaguePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailLeagueActivity extends AppCompatActivity {

    @BindView(R.id.tvLogo)
    ImageView tvLogo;

    @BindView(R.id.tvNama)
    TextView tvNama;

    @BindView(R.id.tvKeterangan)
    TextView tvKeterangan;

    @BindView(R.id.tlPager)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_league);

        ButterKnife.bind(this);

        Bundle mBundel=getIntent().getExtras();
        tvNama.setText(mBundel.getString("nama"));
        tvKeterangan.setText(mBundel.getString("keterangan"));

        Picasso.get().load(mBundel.getString("logo")).resize(100,100)
                .into(tvLogo);

        id=mBundel.getString("id");

        //Tab Layout
        tabLayout.addTab(tabLayout.newTab().setText("Next Match"));
        tabLayout.addTab(tabLayout.newTab().setText("Last Match"));

        LeaguePagerAdapter pagerAdapter=new LeaguePagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(),id);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}