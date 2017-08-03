package com.example.xsy.hands_on.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by XSY on 2017/7/31.
 */

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<View> views;

    public MyPagerAdapter(){

    }

    public MyPagerAdapter(ArrayList<View> views) {
        super();
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
