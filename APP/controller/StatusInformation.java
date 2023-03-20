package com.example.testapp.controller;


import android.util.Log;

import com.example.testapp.util.Alarm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kirito on 2016/9/2.
 */
public class StatusInformation {
    Alarm alarm=new Alarm();


    public static void getUrlData(String path,HttpBackListener backListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb=new StringBuilder();
                try {
                    //创建URL
                    URL url = new URL(path);

                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();

                    connection.setConnectTimeout(18000);
                    connection.setReadTimeout(18000);

                    connection.setRequestProperty("Content-Type","application/json");

                    connection.setRequestMethod("GET");

                    //连接
                    connection.connect();

                    //发起请求
                    //OutputStream outputStream=connection.getOutputStream();

                    if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        InputStream inputStream=connection.getInputStream();
                        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                        BufferedReader reader=new BufferedReader(inputStreamReader);

                        String temp;
                        while ((temp=reader.readLine())!=null){
                            sb.append(temp);
                        }
                        reader.close();
                        backListener.onSuccess(sb.toString(),connection.getResponseCode());
                    }
                    else {
                        backListener.onError(connection.getResponseMessage(),connection.getResponseCode());
                    }
                    connection.disconnect();
                    Log.i("qinl",sb.toString());



                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }



    public interface HttpBackListener{
        void onSuccess(String data,int code);
        void onError(String error,int code);
    }

    public Alarm getNewData(){

        StatusInformation.getUrlData("http://192.168.43.35:8080//alarm", new HttpBackListener() {
            @Override
            public void onSuccess(String data, int code) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    alarm.setAlarmInformation(jsonObject.getString("alarmInformation"));
                    //System.out.println("111"+alarm.getAlarmInformation());
                    //Log.i("qin",alarm.getAlarmInformation());
                    //parseStr=parseStr+jsonObject.getString("alarmInformation");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error, int code) {

            }
        });
        return alarm;
    }

    /*public void postNewData(){
        StatusInformation.postUrlData("http://10.208.85.222:8080//");
    }*/
}
