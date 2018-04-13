package net.iclassmate.teacherspace.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xydbj on 2016/1/27.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    public MainFragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        int count = 0;
        if(fragments!=null){
            count = fragments.size();
        }
        return count;
    }
}
