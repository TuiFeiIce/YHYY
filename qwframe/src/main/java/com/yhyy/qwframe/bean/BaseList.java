package com.yhyy.qwframe.bean;

import java.util.ArrayList;

public class BaseList<T> {

    private String status;
    private String msg;
    private ArrayList<T> data;

    public BaseList() {
    }

    public BaseList(String status, String msg, ArrayList<T> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
