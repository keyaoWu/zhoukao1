package com.bawei.unit4_zhoukao1_demo;

/**
 * 功能：Contacts类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：18:48
 */
public interface Contacts {
    interface  IModel{
        void getInfo(String url,iCallBack iCallBack);
        void postInfo(String url,iCallBack iCallBack);
    }

    interface IView{
        void onSuccess(String json);
        void  onFaild(String error);
    }

    interface  Persenter{
        void onActtach(IView iView);
        void  startRequest(String url);
        void onDeattch();
    }


    interface iCallBack{
       void onSuccess(String json);
       void onFaild(String error);
    }
}
