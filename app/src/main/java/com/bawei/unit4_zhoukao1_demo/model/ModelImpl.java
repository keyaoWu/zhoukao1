package com.bawei.unit4_zhoukao1_demo.model;

import android.graphics.Bitmap;

import com.bawei.unit4_zhoukao1_demo.Contacts;
import com.bawei.unit4_zhoukao1_demo.NetUntil;

/**
 * 功能：ModelImpl类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：18:53
 */
public class ModelImpl implements Contacts.IModel {
    @Override
    public void getInfo(String url, final Contacts.iCallBack iCallBack) {
        NetUntil.getInstance().doGet(url, new NetUntil.myCallBack() {
            @Override
            public void onDoGetSuccess(String json) {
                iCallBack.onSuccess(json);
            }

            @Override
            public void onDoGetSuccess(Bitmap bitmap) {

            }

            @Override
            public void onError(String error) {
              iCallBack.onFaild(error);
            }

            @Override
            public void onBitmapError(String error) {

            }
        });
    }

    @Override
    public void postInfo(String url, Contacts.iCallBack iCallBack) {

    }
}
