package com.yhyy.qwframe.quick;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.yhyy.qwframe.bean.Event;
import com.yhyy.qwframe.utils.EventBusUtil;
import com.yhyy.qwframe.widget.Dialog_ProgressBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class QuickActivity extends AppCompatActivity{
    public AppCompatActivity context;
    public Dialog_ProgressBar dialogProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ExitApplication.getInstance().addActivity(this);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        dialogProgressBar = new Dialog_ProgressBar(context);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExitApplication.setCurrentActivity(context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitApplication.removeActivity(context);
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

    }
}
