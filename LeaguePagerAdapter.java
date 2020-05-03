package com.sudirman.aplikasifootball.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sudirman.aplikasifootball.fragment.LastEventFragment;
import com.sudirman.aplikasifootball.fragment.NextEventFragment;


public class LeaguePagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    String idLeague;

    public LeaguePagerAdapter(@NonNull FragmentManager fm, int tabCount,String idLeague) {
        super(fm);
        this.tabCount=tabCount;
        this.idLeague=idLeague;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return NextEventFragment.newInstance(idLeague);
            case 1:
                return LastEventFragment.newInstance(idLeague);
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}

