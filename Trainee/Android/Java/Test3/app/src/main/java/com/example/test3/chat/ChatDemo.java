package com.example.test3.chat;

import com.google.firebase.Timestamp;

public class ChatDemo {

    private String viewType;
    private String Msg;
    private String Receiver;
    private Timestamp TimeStamp;

    public ChatDemo(String viewType, String Msg, String Receiver, Timestamp TimeStamp)
    {
        this.Msg = Msg;
        this.viewType = viewType;
        this.Receiver = Receiver;
        this.TimeStamp = TimeStamp;
    }

    public ChatDemo(){

    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public Timestamp getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        TimeStamp = timeStamp;
    }
}
