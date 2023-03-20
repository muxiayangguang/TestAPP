package com.example.demo;

import com.example.SerialPort.ControlInformation;
import com.example.classCollection.Alarm;
import com.example.classCollection.Status;
import com.example.dataProcessing.ParseControlJSON;
import com.example.dataProcessing.ParseJSON;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpringBoot {
    ImageIdentification ima=new ImageIdentification();
    String str[]=ima.GetStatus();

    AlarmInformation ala=new AlarmInformation();
    Alarm alarm=new Alarm();
    ParseJSON parseJSON=new ParseJSON();
    ParseControlJSON parseControlJSON=new ParseControlJSON();
    ControlInformation controlInformation=new ControlInformation();
/*    @RequestMapping("/status")//http://localhost:8080//status
    public Status ReturnStatus(){
        ImageIdentification ima=new ImageIdentification();
        String str[]=ima.GetStatus();
        Status status=new Status();
        status.setOnOrOff(str[0]);
        status.setMode(str[1]);
        status.setNumber(str[2]);
        return status;
    }*/

   @RequestMapping("/alarm")//http://localhost:8080//alarm
    public Alarm ReturnAlarm(){
        //str=ima.GetStatus();
        alarm.setAlarmInformation(ala.GetAlarmInformation(str));
        str=ima.GetStatus();
        return alarm;

    }

    @RequestMapping("/setStatus")//http://localhost:8080//setStatus
    public String ReturnSetStatus(@RequestBody String str) throws JSONException {

        parseJSON.parseDate(str);

        JSONObject jsonObject=new JSONObject(str);
        return jsonObject.toString();
    }

    @RequestMapping("/controlStatus")//http://localhost:8080//controlStatus
    public String ReturnControlStatus(@RequestBody String str2) throws JSONException, InterruptedException {
        //System.out.print(str);

        parseControlJSON.parseControlDate(str2);//解析


        str=ima.GetStatus();

        controlInformation.Controling(str);//去控制



        JSONObject jsonObject=new JSONObject(str2);
        return jsonObject.toString();
    }
}
