package com.example.rampagegg.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rampagegg.ui.heroes.HeroesFragment;
import com.example.rampagegg.ui.matches.MatchesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "TabAdapter";
    final Context mContext;
    private final int mTotalTabs;


    public PagerAdapter(@NonNull FragmentManager fm, int behavior, Context mContext, int mTotalTabs) {
        super(fm, behavior);
        this.mContext = mContext;
        this.mTotalTabs = mTotalTabs;
    }

//    public PagerAdapter(@NonNull FragmentManager fm, Context mContext, int mTotalTabs) {
//        super(fm);
//        this.mContext = mContext;
//        this.mTotalTabs = mTotalTabs;
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MatchesFragment matchesFragment = new MatchesFragment();
                return matchesFragment;
            case 1:
                HeroesFragment heroesFragment = new HeroesFragment();
                return heroesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
