package com.example.demo;

import com.example.dataProcessing.Normalization;
import com.example.dataProcessing.ParseJSON;

public class AlarmInformation {
    //static String NeedList[]=new String[]{"Powered on!","24","30","Heating"};
    ParseJSON parseJSON = new ParseJSON();
    String NeedList[]=parseJSON.ReturnSetStatus();

    //String NeedList[]=new ParseJSON().needList;
    public String GetAlarmInformation(String[] src1)
    {
        System.out.print("设置状态：");
        for(int i=0;i<NeedList.length;i++){
            System.out.print(NeedList[i]+"  ");
        }
        System.out.println("\n");

        Normalization normalization=new Normalization();
        String[] src=normalization.Unify(src1);

        System.out.print("当前状态：");
        for(int i=0;i<src.length;i++){
            System.out.print(src[i]+"  ");
        }
        System.out.println("\n");

        //System.out.println(NeedList[2]);
        String AlarmInformation = "";
        if(src[0].equals(NeedList[0])){
            if(src[0].equals("开机")) {
                if (Integer.parseInt(src[2]) < Integer.parseInt(NeedList[2].substring(0, 2)) ||
                        Integer.parseInt(src[2]) > Integer.parseInt(NeedList[3].substring(0, 2))) {
                    AlarmInformation = AlarmInformation + "警告：当前温度为：" + src[2] + "℃!\n";
                }
                if (!src[1].equals(NeedList[1])) {
                    AlarmInformation = AlarmInformation + "警告：当前模式为：" + src[1] + "!";
                }
            }
            if(Integer.parseInt(src[2])>=Integer.parseInt(NeedList[2].substring(0,2))&&
                    Integer.parseInt(src[2])<=Integer.parseInt(NeedList[3].substring(0,2))&&
                    src[1].equals(NeedList[1])){
                AlarmInformation=AlarmInformation+"It is currently in a normal state.";
            }
        }else if(!src[0].equals(NeedList[0])&&NeedList[0]==null){
            AlarmInformation=AlarmInformation+"初始状态.";
        }else if(!src[0].equals(NeedList[0])&&NeedList[0]!=null){
            AlarmInformation=AlarmInformation+"警告：开关机状态不符！";
        }
        //System.out.println("第一个need: "+NeedList[0]);
        //System.out.print("警报信息：");
        System.out.println(AlarmInformation);
        return AlarmInformation;
    }

}
