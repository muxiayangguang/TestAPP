package com.example.dataProcessing;

import com.example.classCollection.SetStatus;
import com.example.classCollection.Status;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseControlJSON {
    Status status=new Status();
    static String controlList[]=new String[3];

    public void parseControlDate(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            status.setOnOrOff(jsonObject.getString("onOrOff"));
            status.setMode(jsonObject.getString("mode"));
            status.setNumber(jsonObject.getString("number"));
            //System.out.print("解析的信息1：");
            //System.out.println(setStatus.getOnOrOff());
            //System.out.println("111"+alarm.getAlarmInformation());
            //Log.i("qin",alarm.getAlarmInformation());
            //parseStr=parseStr+jsonObject.getString("alarmInformation");

            controlList[0] = status.getOnOrOff();
            controlList[1] = status.getMode();
            controlList[2] = status.getNumber();
            //System.out.println("首个need: "+controlList[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String[] ReturnControlStatus(){
/*        for(int i=0;i<needList.length;i++){
            System.out.print(needList[i]+"  ");
        }*/
        return controlList;
    }
}
