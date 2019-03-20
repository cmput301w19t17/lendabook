package com.example.android.lendabook.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by belachew on 2019-02-27.
 * Class for the sections page adapter.
 */

/**
 * Class that stores fragments for tabs
* */
public class SectionsPageAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionPagerAdapter";

    private final List<Fragment> mFragmentList = new ArrayList<>();
    
    /**
     * Adapter for sections
     *
     * @param fe
     */
    public SectionsPageAdapter(FragmentManager fe) {
        super(fe);
    }
    
    /**
     * Gets item by position in adapter.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    
    /**
     * Add fragment to adapter
     *
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }
}
