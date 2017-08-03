package com.example.xsy.hands_on.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.xsy.hands_on.CampusFragment;
import com.example.xsy.hands_on.FindFragment;
import com.example.xsy.hands_on.MainActivity;
import com.example.xsy.hands_on.MessageFragment;
import com.example.xsy.hands_on.MyFragment;

/**
 * Created by XSY on 2017/7/29.
 */

public class MenuFragmentPagerAdapter extends FragmentPagerAdapter {
    private FindFragment findFragment = null;
    private CampusFragment campusFragment = null;
    private MessageFragment messageFragment = null;
    private MyFragment myFragment = null;
    private final int PAGER_COUNT = 4;


    public MenuFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        findFragment = new FindFragment();
        campusFragment = new CampusFragment();
        messageFragment = new MessageFragment();
        myFragment = new MyFragment();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MainActivity.Page_One:
                fragment = findFragment;
                break;
            case MainActivity.Page_Two:
                fragment = campusFragment;
                break;
            case MainActivity.Page_Three:
                fragment = messageFragment;
                break;
            case MainActivity.Page_Four:
                fragment = myFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {

        return PAGER_COUNT;
    }
}
