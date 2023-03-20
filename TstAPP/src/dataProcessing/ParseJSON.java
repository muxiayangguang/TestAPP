package com.example.dataProcessing;

import com.example.classCollection.SetStatus;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {
    //public String[] needList;
    SetStatus setStatus=new SetStatus();
    static String needList[]=new String[4];

    public void parseDate(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            setStatus.setOnOrOff(jsonObject.getString("onOrOff"));
            setStatus.setMode(jsonObject.getString("mode"));
            setStatus.setLowestNumber(jsonObject.getString("lowestNumber"));
            setStatus.setHighestNumber(jsonObject.getString("highestNumber"));
            //System.out.print("解析的信息1：");
            //System.out.println(setStatus.getOnOrOff());
            //System.out.println("111"+alarm.getAlarmInformation());
            //Log.i("qin",alarm.getAlarmInformation());
            //parseStr=parseStr+jsonObject.getString("alarmInformation");

            needList[0] = setStatus.getOnOrOff();
            needList[1] = setStatus.getMode();
            needList[2] = setStatus.getLowestNumber();
            needList[3] = setStatus.getHighestNumber();
            //System.out.println("首个need: "+needList[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String[] ReturnSetStatus(){
/*        for(int i=0;i<needList.length;i++){
            System.out.print(needList[i]+"  ");
        }*/
        return needList;
    }
}
