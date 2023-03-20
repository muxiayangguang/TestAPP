package com.example.testapp.util;

public class SetStatus {
    private String onOrOff;

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

    public String getLowestNumber() {
        return lowestNumber;
    }

    public void setLowestNumber(String lowestNumber) {
        this.lowestNumber = lowestNumber;
    }

    public String getHighestNumber() {
        return highestNumber;
    }

    public void setHighestNumber(String highestNumber) {
        this.highestNumber = highestNumber;
    }

    private String mode;
    private String lowestNumber;
    private String highestNumber;
}
