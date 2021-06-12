package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uddesh.tiffinserviceappforadmin.Adapter.HomepagePagerAdapter;
import com.uddesh.tiffinserviceappforadmin.R;

public class HomePageActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private TabLayout tabLayout;
    final int[] tabDrawables = {R.drawable.ic_home , R.drawable.ic_users_list};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initializeComponents();
        tabMediator();
    }

    private void initializeComponents()
    {
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new HomepagePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = findViewById(R.id.tab_layout);
    }

    private void tabMediator()
    {
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setIcon(tabDrawables[position])).attach();
    }
}