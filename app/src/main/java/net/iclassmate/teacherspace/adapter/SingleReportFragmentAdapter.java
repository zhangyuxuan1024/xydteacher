package net.iclassmate.teacherspace.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.iclassmate.teacherspace.ui.fragment.LazyFragment;

import java.util.List;

/**
 * Created by xydbj on 2016/1/30.
 */
public class SingleReportFragmentAdapter extends FragmentPagerAdapter {
    private List<LazyFragment> fragments;

    public SingleReportFragmentAdapter(FragmentManager fm, List<LazyFragment> fragments) {
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
        if (fragments != null) {
            count = fragments.size();
        }
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String ret = null;
        LazyFragment lazyFragment = fragments.get(position);
        ret = lazyFragment.getFragmentTitle();
        return ret;
    }
}
