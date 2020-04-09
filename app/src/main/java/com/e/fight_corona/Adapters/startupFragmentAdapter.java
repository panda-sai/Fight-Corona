package com.e.fight_corona.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.e.fight_corona.R;
import com.e.fight_corona.startFragment;
import com.e.fight_corona.startup;

public class startupFragmentAdapter extends FragmentPagerAdapter
{

    public startupFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:return new startFragment(R.drawable.confirmstatusdoctor,"Confirm your health status from certified doctors");
            case 1:return new startFragment(R.drawable.fakenews,"Get Fake News Verified");
            case 2:return new startFragment(R.drawable.newsonthego,"Get On the go Current Updates on COVID-19 ");
            case 3:return new startFragment(R.drawable.makedontation,"Contribute whatever money you can to others");
            case 4:return new startFragment(R.drawable.feedhunger,"Feed the Hungry by donating surplus");
            case 5:return new startFragment(R.drawable.hourlynotification,"Stay Hygienic by a hourly Notification reminding you of hand wash ");

        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
