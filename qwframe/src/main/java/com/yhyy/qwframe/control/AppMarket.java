package com.yhyy.qwframe.control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceWolf on 2019/9/13.
 */
public class AppMarket {
    public static final List<String> Market = new ArrayList<String>() {//应用市场
        {
            add(new String("com.huawei.appmarket"));//华为
            add(new String("com.xiaomi.market"));//小米
            add(new String("com.oppo.market"));//OPPO
            add(new String("com.bbk.appstore"));//vivo
            add(new String("com.qihoo.appstore"));//360
            add(new String("com.meizu.mstore"));//魅族
            add(new String("com.baidu.appsearch"));//百度
            add(new String("com.tencent.android.qqdownloader"));//应用宝
        }
    };
}
