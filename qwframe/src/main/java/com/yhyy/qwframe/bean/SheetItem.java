package com.yhyy.qwframe.bean;

import android.graphics.drawable.Drawable;

import com.yhyy.qwframe.inter.OnSheetClickListener;

public class SheetItem {
    Drawable drawableLeft;
    String name;
    Integer color;
    OnSheetClickListener onSheetClickListener;

    public SheetItem(String name, Integer color, OnSheetClickListener onSheetClickListener) {
        this.name = name;
        this.color = color;
        this.onSheetClickListener = onSheetClickListener;
    }

    public SheetItem(Drawable drawableLeft, String name, Integer color, OnSheetClickListener onSheetClickListener) {
        this.drawableLeft = drawableLeft;
        this.name = name;
        this.color = color;
        this.onSheetClickListener = onSheetClickListener;
    }

    public Drawable getDrawableLeft() {
        return drawableLeft;
    }

    public void setDrawableLeft(Drawable drawableLeft) {
        this.drawableLeft = drawableLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public OnSheetClickListener getOnSheetClickListener() {
        return onSheetClickListener;
    }

    public void setOnSheetItemClickListener(OnSheetClickListener onSheetClickListener) {
        this.onSheetClickListener = onSheetClickListener;
    }
}
