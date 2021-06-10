package com.uddesh.tiffinserviceappforadmin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uddesh.tiffinserviceappforadmin.Fragment.HomeFragment;
import com.uddesh.tiffinserviceappforadmin.Fragment.UsersListFragment;

public class HomepagePagerAdapter extends FragmentStateAdapter {
    public HomepagePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position)
        {
            case 0:
                fragment = HomeFragment.newInstance();
                break;
            case 1:
                fragment = UsersListFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
