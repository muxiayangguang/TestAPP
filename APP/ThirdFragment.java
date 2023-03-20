package com.example.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapp.controller.ControlRequest;
import com.example.testapp.tools.BackInformation;
import com.example.testapp.util.Status;

public class ThirdFragment  extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String controlList[]=new String[3];
        Spinner onOffSpinner = (Spinner) view.findViewById(R.id.onOrOff2);
        onOffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                controlList[0] = (String) onOffSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Spinner mode2Spinner = (Spinner) view.findViewById(R.id.mode2);
        mode2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                controlList[1] = (String) mode2Spinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Spinner temperatureSpinner = (Spinner) view.findViewById(R.id.temperature);
        temperatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                controlList[2] = (String) temperatureSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        view.findViewById(R.id.button_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Status status= BackInformation.backControl(controlList);
                ControlRequest.ConRequest(status);

                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });
    }
}
