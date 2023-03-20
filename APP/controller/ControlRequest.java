package com.example.testapp.controller;

import com.example.testapp.util.Status;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ControlRequest {
    public static void ConRequest(Status Status) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject setStatusJSON = new JSONObject();
                    setStatusJSON.put("onOrOff", Status.getOnOrOff());
                    setStatusJSON.put("mode", Status.getMode());
                    setStatusJSON.put("number", Status.getNumber());


                    //JSONObject object = new JSONObject();
                    //object.put("user",userJSON);

                    String content = String.valueOf(setStatusJSON);
                    //HttpURLConnection connection  =
                    /**
                     * 请求地址
                     */
                    String path = "http://192.168.43.35:8080//controlStatus";
                    URL url=new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //HttpURLConnection connection = (HttpURLConnection) nURL.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    //connection.setRequestProperty("User-Agent", "Fiddler");
                    connection.setRequestProperty("Content-Type", "application/json");
                    //connection.setRequestProperty("Content-Length", content.length()+"");
                    connection.connect();
                    System.out.println("返回中2");
                    OutputStream os = connection.getOutputStream();
                    os.write(content.getBytes());
                    int responseCode = connection.getResponseCode();
                    if(200 == responseCode){
                        System.out.println("已响应");
                    }
                    os.close();
                    /**
                     * 服务器返回结果
                     * 继续干什么事情....待续
                     */
                    //String result = read(connection.getInputStream());

                    //Log.i("success",os);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

