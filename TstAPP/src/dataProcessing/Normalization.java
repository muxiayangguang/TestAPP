package com.example.dataProcessing;

public class Normalization {
    public String[] Unify(String[] list){
        switch (list[0]){
            case "Powered on!":
                list[0]="开机";
            case "开机":
                list[0]="开机";
                break;
            case "Powered off!":
                list[0]="关机";
                break;
            case "关机":
                list[0]="关机";
                break;
            case "Power is not connected!":
                list[0]="未接电源";
                break;
            case "未接电源":
                list[0]="未接电源";
                break;
            default:
                list[0]="error";
                break;
        }

        switch (list[1]){
            case "Automatic":
                list[1]="自动";
                break;
            case "自动":
                list[1]="自动";
                break;
            case "Colding":
                list[1]="制冷";
                break;
            case "制冷":
                list[1]="制冷";
                break;
            case "Drying":
                list[1]="干燥";
                break;
            case "干燥":
                list[1]="干燥";
                break;
            case "制热":
                list[1]="制热";
                break;
            case "Heating":
                list[1]="制热";
                break;
            default:
                list[1]="error2";
                break;
        }
        return list;
    }
}
