package com.example.xsy.hands_on.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xsy.hands_on.R;

/**
 * Created by XSY on 2017/7/31.
 */

public  class MyAdapter extends BaseAdapter {

    String[] pro = {"模拟上手派学生端","易拉宝平面设计","OA办公系统首页设计优化","微信小程序UI设计","容器集群管理基础实战"};
    String[] category = {"移动   前端","平面设计","UI · 前端 · 平面设计","UI · ","云计算/大数据"};
    String[] num = {"1~2人|14天","1~10人|2天","2~10人|11天","1~3人|9天","10~15人|34天"};
    String[] name = {"赵云龙","张青云","高鹏","李文平","陈维龙"};
    String[] company = {"大连希然科技有限公司·开发",
            "上海红星美凯龙品牌管理有限公司·人事专员",
            "讯视达网络技术有限公司·技术经理",
            "郑州乐天堂软件开发有限公司·研发总监",
            "元合科技·运维"};
    String[] label = {"Android","PS","JavaScript","CDR","Docker"};
    int[] images_pro = {R.mipmap.bg_jichu,R.mipmap.bg_chuji,R.mipmap.bg_zhongji,R.mipmap.bg_gaoji,R.mipmap.bg_chuji};
    int[] images_level = {R.mipmap.jichu_03,R.mipmap.chuji_03,R.mipmap.zhongji_03,R.mipmap.gaoji_03,R.mipmap.chuji_03};
    int[] images_head = {R.drawable.head,R.drawable.head,R.drawable.head,R.drawable.head,R.drawable.head};

    private Context context;
    public MyAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item,null);
            viewHolder =new ViewHolder();
            viewHolder.imageView_pro = (ImageView) convertView.findViewById(R.id.ima_jichu);
            viewHolder.imageView_level = (ImageView) convertView.findViewById(R.id.imageView_jichu);
            viewHolder.imageView_head = (ImageView) convertView.findViewById(R.id.imageView_head);
            viewHolder.textView_pro = (TextView) convertView.findViewById(R.id.textView_moni);
            viewHolder.textView_category = (TextView) convertView.findViewById(R.id.textView_category);
            viewHolder.textView_num = (TextView) convertView.findViewById(R.id.textView_num);
            viewHolder.textView_name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.textView_company = (TextView) convertView.findViewById(R.id.company);
            viewHolder.textView_label = (TextView) convertView.findViewById(R.id.key);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView_pro.setImageResource(images_pro[position]);
        viewHolder.imageView_level.setImageResource(images_level[position]);
        viewHolder.imageView_head.setImageResource(images_head[position]);
        viewHolder.textView_pro.setText(pro[position]);
        viewHolder.textView_category.setText(category[position]);
        viewHolder.textView_num.setText(num[position]);
        viewHolder.textView_name.setText(name[position]);
        viewHolder.textView_company.setText(company[position]);
        viewHolder.textView_label.setText(label[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView_pro,imageView_level,imageView_head;
        TextView textView_pro,textView_category,textView_num,textView_name,textView_company,textView_label;
    }
}
