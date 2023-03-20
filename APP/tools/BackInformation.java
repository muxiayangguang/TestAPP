package com.example.testapp.tools;

import com.example.testapp.util.SetStatus;
import com.example.testapp.util.Status;

import java.util.Set;

public class BackInformation {

    public static SetStatus backSetting(String[] setList){
        SetStatus setStatus=new SetStatus();
        setStatus.setOnOrOff(setList[0]);
        setStatus.setMode(setList[1]);
        setStatus.setLowestNumber(setList[2]);
        setStatus.setHighestNumber(setList[3]);
        System.out.println(setList[3]);
        return setStatus;

    }

    public static Status backControl(String[] controlList){
        Status Status=new Status();
        Status.setOnOrOff(controlList[0]);
        Status.setMode(controlList[1]);
        Status.setNumber(controlList[2]);
        //System.out.println(setList[3]);
        return Status;

    }
}
