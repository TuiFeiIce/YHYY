package com.yhyy.qwframe.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.yhyy.qwframe.base.VH;
import com.yhyy.qwframe.base.VHFoot;
import com.yhyy.qwframe.base.VHHead;
import com.yhyy.qwframe.inter.OnItemClickListener;
import com.yhyy.qwframe.inter.OnLongClickListener;

import java.util.List;

public abstract class Adapter_All<T> extends RecyclerView.Adapter {
    public OnItemClickListener onItemClickListener;
    public OnLongClickListener onLongClickListener;
    private List<T> datas;
    public static final int HEAD = 0;
    public static final int BODY = 1;
    public static final int FOOT = 2;

    public Adapter_All(List<T> datas) {
        this.datas = datas;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD:
                return VHHead.get(parent, getLayoutId(viewType));
            case BODY:
                return VH.get(parent, getLayoutId(viewType));
            case FOOT:
                return VHFoot.get(parent, getLayoutId(viewType));
        }
        return null;
    }

    //解决 GridLayoutManager 加载更多时，加载动画最后一个item没有占满屏幕宽度问题
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0 || datas.size() == 0 || datas.size() == position - 1) {
                        return gridManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    //解决 StaggeredGridLayoutManager 加载更多时，加载动画最后一个item没有占满屏幕宽度问题
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            int position = holder.getLayoutPosition();
            if (position == 0 || datas.size() == position - 1) {
                params.setFullSpan(true);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof VHHead) {
            VHHead vhHead = (VHHead) viewHolder;
            convert1(vhHead);
        } else if (viewHolder instanceof VH) {
            VH vh = (VH) viewHolder;
            convert2(vh, datas.get(position - 1), position);
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
        } else if (viewHolder instanceof VHFoot) {
            VHFoot vhFoot = (VHFoot) viewHolder;
            convert3(vhFoot);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemListener) {
        this.onItemClickListener = itemListener;
    }

    public void setOnLongClickListener(OnLongClickListener longListener) {
        this.onLongClickListener = longListener;
    }

    @Override
    public int getItemCount() {
        return datas.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        } else if (position == datas.size() + 1) {
            return FOOT;
        } else {
            return BODY;
        }
    }


    public abstract void convert1(VHHead holder);

    public abstract void convert2(VH holder, T data, int position);

    public abstract void convert3(VHFoot holder);

    public void AddHeaderItem(List<T> items) {
        datas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<T> items) {
        datas.addAll(items);
        notifyDataSetChanged();
    }
}
