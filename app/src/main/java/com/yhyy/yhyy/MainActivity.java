package com.yhyy.yhyy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.qwframe.adapter.Adapter_Switch;
import com.yhyy.qwframe.base.VH;
import com.yhyy.qwframe.base.VHFoot;
import com.yhyy.qwframe.inter.OnItemClickListener;
import com.yhyy.qwframe.quick.QuickActivity;
import com.yhyy.qwframe.widget.RecyPositive;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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