package com.example.testapp.util;

public class Status {
    public String getOnOrOff() {
        return onOrOff;
    }

    public void setOnOrOff(String onOrOff) {
        this.onOrOff = onOrOff;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String onOrOff;
    private String mode;
    private String number;
}
