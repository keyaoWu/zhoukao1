package com.bawei.unit4_zhoukao1_demo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.unit4_zhoukao1_demo.NetUntil;
import com.bawei.unit4_zhoukao1_demo.R;
import com.bawei.unit4_zhoukao1_demo.bean.Bean;

import java.util.List;

/**
 * 功能：MyAdapter类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：19:15
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bean.ListdataBean> mlist;

    public MyAdapter(Context mContext, List<Bean.ListdataBean> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.mimageView = view.findViewById(R.id.image);
            viewHolder.mname = view.findViewById(R.id.name);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mname.setText(mlist.get(i).getName()+"");
        NetUntil.getInstance().doGetPhoto(mlist.get(i).getAvatar(), new NetUntil.myCallBack() {
            @Override
            public void onDoGetSuccess(String json) {

            }

            @Override
            public void onDoGetSuccess(Bitmap bitmap) {
             viewHolder.mimageView.setImageBitmap(bitmap);
            }

            @Override
            public void onError(String error) {

            }

            @Override
            public void onBitmapError(String error) {

            }
        });
        return view;
    }
    class ViewHolder{
        ImageView mimageView;
        TextView mname;
    }
}
