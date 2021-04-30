package com.yhyy.qwframe.utils;

import com.yhyy.qwframe.bean.Event;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    //发送普通事件
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    //发送粘性事件
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }
}
