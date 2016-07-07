package com.demo.callrecordcatch;

/**
 * Created by Waylon on 16/7/7.
 */


public class CallLogInfo {
        private String name;
        private String number;
        private String date;
        private int type;   // 来电:1，拨出:2,未接:3
        private String time;   //通话时长

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CallLogInfo(String name, String number, String date, int type, String time){
            setName(name);
            setNumber(number);
            setType(type);
            setTime(time);
            setDate(date);


        }



}
