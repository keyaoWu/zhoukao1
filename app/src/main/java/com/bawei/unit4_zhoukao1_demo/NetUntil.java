package com.bawei.unit4_zhoukao1_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 功能：NetUntil类
 * 作者：武柯耀
 * 当前日期：2019/11/4
 * 当前时间：14:43
 */
public class NetUntil {
    private NetUntil() {

    }

    private static class NetUntilSing{
        static NetUntil netUntil = new NetUntil();
    }

    public static NetUntil getInstance() {
        return NetUntilSing.netUntil;
    }

    //流传字符串
    public String io2String(InputStream inputStream){
        int len =-1;
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String json = "";
        try {
            while ((len = inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            byte[] bytes1 = outputStream.toByteArray();
             json = new String(bytes1);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }


    //流转Bitmap
    public Bitmap io2Bitmap(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }


    //doGet请求json
    public void doGet(final String httpUrl, final myCallBack myCallBack){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String json ="";
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(httpUrl);
                     httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200){
                         inputStream = httpURLConnection.getInputStream();
                         json = io2String(inputStream);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                myCallBack.onDoGetSuccess(s);
            }
        }.execute();
    }


    //doGetPhoto请求Bitmap
    public  void  doGetPhoto(final String httpUrl, final myCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap bitmap =null;
                HttpURLConnection httpURLConnection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(httpUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200){
                        inputStream = httpURLConnection.getInputStream();
                         bitmap = io2Bitmap(inputStream);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
               myCallBack.onDoGetSuccess(bitmap);
            }
        }.execute();
    }
    //封装接口
    public interface  myCallBack{
        void onDoGetSuccess(String json);
        void onDoGetSuccess(Bitmap bitmap);
        void onError(String error);
        void onBitmapError(String error);
    }
}
