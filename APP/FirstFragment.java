package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapp.controller.StatusInformation;
import com.example.testapp.util.Alarm;

public class FirstFragment extends Fragment {
    private static Alarm ala;

    public static void getAlarm(){
        StatusInformation sta=new StatusInformation();
        ala=sta.getNewData();
        //sta.postNewData();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //得到警报信息
        FirstFragment.getAlarm();//修改成
        // ------------------------循环改变使用
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println(ala.getAlarmInformation());

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(ala.getAlarmInformation());

/*        if(ala.getAlarmInformation()!="It is currently in a normal state."){

        }*/


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.button_firstfirst).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ThirdFragment);
            }
        });
    }
}