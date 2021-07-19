package com.uddesh.tiffinserviceappforadmin.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uddesh.tiffinserviceappforadmin.Adapter.HomepagePagerAdapter;
import com.uddesh.tiffinserviceappforadmin.Helpers.SharedPreferencesHelper;
import com.uddesh.tiffinserviceappforadmin.Helpers.ToastHelper;
import com.uddesh.tiffinserviceappforadmin.R;

public class HomePageActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    final int[] tabDrawables = {R.drawable.ic_home , R.drawable.ic_users_list};
    private ImageView activityHomePageImageView;
    SharedPreferencesHelper sharedPreferences;
    ToastHelper toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        grantPermission();
        initializeComponents();
        tabMediator();
    }

    private void initializeComponents()
    {
        sharedPreferences = new SharedPreferencesHelper(this);
        viewPager = findViewById(R.id.view_pager);
        FragmentStateAdapter pagerAdapter = new HomepagePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        toast = new ToastHelper(this);
        tabLayout = findViewById(R.id.tab_layout);
        activityHomePageImageView = findViewById(R.id.activityHomePageImageView);
        TextView activityHomePageTextView = findViewById(R.id.activityHomePageTextView);
        String username = "Welcome!\n"+sharedPreferences.getSharedPreferences("username");
        activityHomePageTextView.setText(username);
        activityHomePageImageView.setOnClickListener(v->openMenu());
    }

    private void tabMediator()
    {
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setIcon(tabDrawables[position])).attach();
    }

    @SuppressLint("NonConstantResourceId")
    private void openMenu() {
        PopupMenu popupMenu = new PopupMenu(this , activityHomePageImageView);
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.changeLocation:
                    Intent intent = new Intent(getApplication() , SetAddressActivity.class);
                    intent.putExtra("fromHomePage" , true);
                    startActivity(intent);
                    break;
                case R.id.logout:
                    sharedPreferences.setSharedPreferences("loggedIn" , "false");
                    toast.makeToast("logged out successfully" , Toast.LENGTH_LONG);
                    Intent intent1 = new Intent(getApplication() , MainActivity.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.updatePersonalDetails:
                    Intent intent2 = new Intent(getApplication() , PersonalDetailsActivity.class);
                    intent2.putExtra("isUpdating" , true);
                    startActivity(intent2);
            }
            return false;
        });
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(HomePageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(HomePageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
}