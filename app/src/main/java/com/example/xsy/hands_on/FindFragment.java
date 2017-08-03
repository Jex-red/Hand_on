package com.example.xsy.hands_on;


import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.example.xsy.hands_on.adapter.MyAdapter;
import com.example.xsy.hands_on.adapter.MyPagerAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener, View.OnTouchListener, AdapterView.OnItemClickListener {
    private ViewFlipper viewFlipper;
    private ViewPager viewPager;
    private RadioGroup rg_choose;
    private RadioButton rb_pro, rb_tea;
    private ImageView image_cursor;
    private ArrayList<View> views;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private ListView listView_pro, listView_tea;
    private RelativeLayout dotLayout;
    private ImageView[] images;
    private int currentItem = 0;
    private int num = 3;//图片总数
    private int currentIndex = 0;//圆点编号
    private static final int AUTO = 7;


    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);

        viewFlipper.setOnTouchListener(this);

        //圆点
        dotLayout = (RelativeLayout) view.findViewById(R.id.dot_choose);
        images = new ImageView[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = (ImageView) dotLayout.getChildAt(i);
            images[i].setEnabled(false);

        }
        images[0].setEnabled(true);
        sendMes();
        //viewFlipper.startFlipping();//自动轮播

       /* for (int i = 0; i < images.length; i++) {
            if (i == viewFlipper.getDisplayedChild()) {
                images[i].setEnabled(true);
            } else {
                images[i].setEnabled(false);
            }
        }*/


        //初始化viewPager
        viewPager = (ViewPager) view.findViewById(R.id.tab_choose);
        rg_choose = (RadioGroup) view.findViewById(R.id.rg_choose);
        rb_pro = (RadioButton) view.findViewById(R.id.rb_pro);
        rb_tea = (RadioButton) view.findViewById(R.id.rb_tea);
        image_cursor = (ImageView) view.findViewById(R.id.image_cursor);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenW = dm.widthPixels;
        offset = screenW / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        image_cursor.setImageMatrix(matrix);
        //往ViewPager填充View
        views = new ArrayList<View>();
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View v1 = layoutInflater.inflate(R.layout.project, null);
        View v2 = layoutInflater.inflate(R.layout.teacher, null);
        views.add(v1);
        views.add(v2);
        viewPager.setAdapter(new MyPagerAdapter(views));
        viewPager.setCurrentItem(0);
        rg_choose.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        rb_pro.setChecked(true);
        listView_pro = (ListView) v1.findViewById(R.id.listView_pro);
        MyAdapter myAdapter = new MyAdapter(getActivity());
        listView_pro.setAdapter(myAdapter);
        listView_pro.setFocusable(false);
        listView_pro.setOnItemClickListener(this);
        return view;
    }

    public void ViewChange() {

        images[viewFlipper.getDisplayedChild()].setEnabled(true);//当前显示页面圆点变黄
        //Log.d("SYS","----------"+viewFlipper.getDisplayedChild());
        images[currentItem].setEnabled(false);//之前页面圆点变灰
        currentItem = viewFlipper.getDisplayedChild();
    }


    float startX, endX;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            startX = event.getX();
        }
        if (action == MotionEvent.ACTION_UP) {
            endX = event.getX();
            if (startX > endX) {
                myHandler.removeMessages(AUTO);
                viewFlipper.setInAnimation(getActivity(), R.anim.right_in);
                viewFlipper.setOutAnimation(getActivity(), R.anim.right_out);
                viewFlipper.showNext();
                ViewChange();
                sendMes();


            } else if (endX > startX) {
                myHandler.removeMessages(AUTO);
                viewFlipper.setInAnimation(getActivity(), R.anim.left_in);
                viewFlipper.setOutAnimation(getActivity(), R.anim.left_out);
                viewFlipper.showPrevious();
                ViewChange();
                sendMes();
            }
        }
        return true;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = null;
        switch (position) {
            case 0:
                animation = new TranslateAnimation(offset, 0, 0, 0);
                break;
            case 1:
                animation = new TranslateAnimation(0, offset, 0, 0);
                break;
        }
        currIndex = position;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        image_cursor.startAnimation(animation);//开始动画


    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    rb_pro.setChecked(true);
                    break;
                case 1:
                    rb_tea.setChecked(true);
                    break;
            }
        }


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_pro:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_tea:
                viewPager.setCurrentItem(1);
                break;
        }

    }
    public void skip(){
        Intent intent =new Intent(getActivity(),SkipNoDataActivity.class);
        startActivity(intent);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                skip();
                break;
            case 1:
                String data = "传输数据";
                Intent intent = new Intent(getActivity(),SkipDataActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
                break;
            case 2:
                skip();
                break;
            case 3:
                skip();
                break;
            case 4:
                skip();
                break;
        }
    }
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case AUTO:
                    viewFlipper.showNext();
                    int current = viewFlipper.getDisplayedChild();
                    if (current==0){
                        images[num-1].setEnabled(false);
                    }else {
                        images[current-1].setEnabled(false);
                    }
                    images[current].setEnabled(true);
                    sendMes();
                    break;
            }
        }
    };
    public void sendMes(){
        Message message = new Message();
        message.what = AUTO;
        myHandler.sendMessageDelayed(message,3000);
    }
}
