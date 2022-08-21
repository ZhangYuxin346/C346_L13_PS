package com.myapplicationdev.android.c346_l13_ps;

public class Carpark {
    private int lots;
    private String type;
    private int available;
    private String carparkNum;

    public String getCarparkNum() {
        return carparkNum;
    }

    public void setCarparkNum(String carparkNum) {
        this.carparkNum = carparkNum;
    }

    public String toString() {
        return "Carpark" +
                "\ncarpark number: " + carparkNum +
                "\nlots: " + lots +
                "\ntype: " + type +
                "\navailable: " + available;
    }

    public int getLots() {
        return lots;
    }

    public void setLots(int lots) {
        this.lots = lots;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Carpark(int lots, String type, int available, String carparkNum) {
        this.lots = lots;
        this.type = type;
        this.available = available;
        this.carparkNum = carparkNum;
    }
}
