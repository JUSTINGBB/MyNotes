package com.cumt.mynotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 用来填充ListView，只要传过来两个值，inflater ,和数据ArrayList<>;
 */

public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<Store> array;

    public MyAdapter(LayoutInflater inflater,ArrayList<Store> array){
        this.inflater=inflater;
        this.array=array;
    }
    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=inflater.inflate(R.layout.adapter_listview, null);//注意导包，别导系统包
            vh.tv1=(TextView) convertView.findViewById(R.id.textView1);
            vh.tv2=(TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(vh);
        }
        vh=(ViewHolder) convertView.getTag();
        vh.tv1.setText(array.get(position).getTitle());
        vh.tv2.setText(array.get(position).getTime());
        return convertView;
    }
    class ViewHolder{
        TextView tv1,tv2;
    }
}
