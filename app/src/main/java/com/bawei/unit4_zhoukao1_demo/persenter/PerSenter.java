package com.bawei.unit4_zhoukao1_demo.persenter;

import com.bawei.unit4_zhoukao1_demo.Contacts;
import com.bawei.unit4_zhoukao1_demo.model.ModelImpl;

/**
 * 功能：PerSenter类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：19:01
 */
public class PerSenter implements Contacts.Persenter {
    private Contacts.IView mView;
    private Contacts.IModel mModel;
    @Override
    public void onActtach(Contacts.IView iView) {
        this.mView = iView;
        mModel = new ModelImpl();
    }

    @Override
    public void startRequest(String url) {
     mModel.getInfo(url, new Contacts.iCallBack() {
         @Override
         public void onSuccess(String json) {
             mView.onSuccess(json);
         }

         @Override
         public void onFaild(String error) {
            mView.onFaild(error);
         }
     });
    }

    @Override
    public void onDeattch() {
     if (mModel!=null){
         mModel = null;
     }
     if (mView!= null){
         mView = null;
     }
    }
}
