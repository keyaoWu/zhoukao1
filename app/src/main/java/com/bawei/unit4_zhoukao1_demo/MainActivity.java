package com.bawei.unit4_zhoukao1_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bawei.unit4_zhoukao1_demo.adapter.MyAdapter;
import com.bawei.unit4_zhoukao1_demo.bean.Bean;
import com.bawei.unit4_zhoukao1_demo.persenter.PerSenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contacts.IView {
    private String url ="http://blog.zhaoliang5156.cn/api/news/lawyer.json\n";
    private XBanner xBanner;
    private GridView gridView;
    private Contacts.Persenter mPersenter;
    private List<Bean.ListdataBean> mList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPersenter = new PerSenter();
        mPersenter.onActtach(this);
        mPersenter.startRequest(url);
        myAdapter = new MyAdapter(MainActivity.this, mList);
        gridView.setAdapter(myAdapter);
    }

    private void initView() {
        xBanner = (XBanner) findViewById(R.id.xb);
        gridView = (GridView) findViewById(R.id.grid);
    }

    @Override
    public void onSuccess(String json) {
    //解析
        final List<Bean.BannerdataBean> bannerdata = new Gson().fromJson(json, Bean.class).getBannerdata();
        xBanner.setBannerData(bannerdata);
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(MainActivity.this).load(bannerdata.get(position).getImageUrl()).into((ImageView) view);
            }
        });
        List<Bean.ListdataBean> listdata = new Gson().fromJson(json, Bean.class).getListdata();
        mList.addAll(listdata);
        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFaild(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPersenter!=null){
            mPersenter.onDeattch();
            mPersenter = null;
        }
    }
}
