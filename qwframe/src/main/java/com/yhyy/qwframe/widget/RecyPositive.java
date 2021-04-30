package com.yhyy.qwframe.widget;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by IceWolf on 2019/9/16.
 */
public abstract class RecyPositive extends RecyclerView.OnScrollListener {

    // 用来标记是否正在向上滑动
    private boolean isSliding = false;

    public enum MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED
    }

    //layoutManager的类型（枚举）
    protected MANAGER_TYPE ManagerType;

    //最后一个可见的item的位置
    private int lastVisibleItemPosition;

    //Staggered最后一个的位置
    private int[] lastPositions;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (ManagerType == null) {
            if (layoutManager instanceof GridLayoutManager) {
                ManagerType = MANAGER_TYPE.GRID;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                ManagerType = MANAGER_TYPE.STAGGERED;
            } else {
                ManagerType = MANAGER_TYPE.LINEAR;
            }
        }
        switch (ManagerType) {
            case LINEAR:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case GRID:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                break;
            case STAGGERED:
                StaggeredGridLayoutManager staggeredGridLayoutManager
                        = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = getMaxElem(lastPositions);
                break;
        }
        // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
        isSliding = dy > 0;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        // 当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if (visibleItemCount > 0 && lastVisibleItemPosition >= (totalItemCount - 1) && isSliding) {
                // 加载更多
                onLoadMore();
            }
        }
    }

    //加载更多回调
    public abstract void onLoadMore();

    //用于判断角标最大者
    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i] > maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }
}