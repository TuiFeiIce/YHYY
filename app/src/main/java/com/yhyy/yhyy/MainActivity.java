package com.yhyy.yhyy;

import android.os.Bundle;

import com.yhyy.qwframe.quick.QuickActivity;

import butterknife.ButterKnife;

public class MainActivity extends QuickActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    private void initListener() {

    }

    private void initToolBar() {
    }

    private void initData() {

    }
}