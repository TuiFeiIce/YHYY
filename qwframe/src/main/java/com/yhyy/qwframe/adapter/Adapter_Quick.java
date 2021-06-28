package com.yhyy.qwframe.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.qwframe.base.VH;
import com.yhyy.qwframe.inter.OnItemClickListener;
import com.yhyy.qwframe.inter.OnLongClickListener;

import java.util.List;

public abstract class Adapter_Quick<T> extends RecyclerView.Adapter<VH> {
    public OnItemClickListener onItemClickListener;
    public OnLongClickListener onLongClickListener;
    private List<T> datas;

    public Adapter_Quick(List<T> datas) {
        this.datas = datas;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        convert(vh, datas.get(position), position);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(vh.itemView, position);
            }
        });
        vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongClickListener.OnLongClick(vh.itemView, position);
                return false;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener itemListener) {
        this.onItemClickListener = itemListener;
    }

    public void setOnLongClickListener(OnLongClickListener longListener) {
        this.onLongClickListener = longListener;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public abstract void convert(VH holder, T data, int position);

    public void AddHeaderItem(List<T> items) {
        datas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<T> items) {
        datas.addAll(items);
        notifyDataSetChanged();
    }
}
