package com.example.xsy.hands_on;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xsy.hands_on.adapter.MenuFragmentPagerAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg_menu;
    private RadioButton rb_find, rb_campus, rb_message, rb_my;
    private TextView textView_title;
    private ViewPager viewPager;
    private MenuFragmentPagerAdapter menuFragmentPagerAdapter;
    public static final int Page_One = 0;
    public static final int Page_Two = 1;
    public static final int Page_Three = 2;
    public static final int Page_Four = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuFragmentPagerAdapter = new MenuFragmentPagerAdapter(getSupportFragmentManager());
        initViews();
        rb_find.setChecked(true);
    }




    private void initViews() {
        textView_title = (TextView) findViewById(R.id.textView_title);
        rg_menu = (RadioGroup) findViewById(R.id.tab_menu);
        rb_find = (RadioButton) findViewById(R.id.tab_menu_find);
        rb_campus = (RadioButton) findViewById(R.id.tab_menu_campus);
        rb_message = (RadioButton) findViewById(R.id.tab_menu_message);
        rb_my = (RadioButton) findViewById(R.id.tab_menu_my);
        rg_menu.setOnCheckedChangeListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(menuFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tab_menu_find:
                viewPager.setCurrentItem(Page_One);
                break;
            case R.id.tab_menu_campus:
                viewPager.setCurrentItem(Page_Two);
                break;
            case R.id.tab_menu_message:
                viewPager.setCurrentItem(Page_Three);
                break;
            case R.id.tab_menu_my:
                viewPager.setCurrentItem(Page_Four);
                break;
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case Page_One:
                    rb_find.setChecked(true);
                    textView_title.setText("寻找项目");
                    break;
                case Page_Two:
                    rb_campus.setChecked(true);
                    textView_title.setText("校园直通车");
                    break;
                case Page_Three:
                    rb_message.setChecked(true);
                    textView_title.setText("消息");
                    break;
                case Page_Four:
                    rb_my.setChecked(true);
                    textView_title.setText("我的");
                    break;
            }
        }
    }
    public interface MyTouchListener {
        public boolean onTouchEvent(MotionEvent event);
    }

    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }


}
