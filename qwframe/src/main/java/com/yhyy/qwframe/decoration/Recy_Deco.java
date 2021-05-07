package com.yhyy.qwframe.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.qwframe.R;

/**
 * Created by IceWolf on 2019/9/14.
 */

public class Recy_Deco extends RecyclerView.ItemDecoration {
    //N(Normal)
    //F(FOOTER)
    //纵向布局分割线
    public static final int VER = 0;
    //横向布局分割线
    public static final int HOR = 1;
    //表格布局分割线
    public static final int GRID = 2;
    //表格布局中间分割线
    public static final int WELL = 3;

    private int orientation = 0;

    private int dividerWidth = 0;

    private Paint paint;

    /**
     * 默认纵向布分割线
     */
    public Recy_Deco(Context context) {
        this(context, VER);
    }

    /**
     * @param orientation 方向类型
     */
    public Recy_Deco(Context context, int orientation) {
        this(context, orientation, ContextCompat.getColor(context, R.color.gray_line), context.getResources().getDimensionPixelSize(R.dimen.one));
    }

    /**
     * @param orientation 方向类型
     * @param color       分割线颜色
     * @param divWidth    分割线宽度
     */
    public Recy_Deco(Context context, int orientation, int color, int divWidth) {
        this.setOrientation(orientation);
        dividerWidth = divWidth;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        switch (orientation) {
            case VER:
                //纵向布局分割线
                drawVertical(c, parent);
                break;
            case HOR:
                //横向布局分割线
                drawHorizontal(c, parent);
                break;
            case GRID:
                //表格格局分割线
                drawGrid(c, parent);
                break;
            case WELL:
                //表格格局分割线
                drawWell(c, parent);
                break;
        }
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemPosition = parent.getChildAdapterPosition(view);
        RecyclerView.Adapter mAdapter = parent.getAdapter();
        if (mAdapter != null) {
            switch (orientation) {
                case VER:
                    /**
                     * 纵向布局分割线
                     *     如果是第一个Item，则不需要分割线
                     */
                    if (itemPosition != 0) {
                        outRect.set(0, dividerWidth, 0, 0);
                    }
                    break;
                case HOR:
                    /**
                     * 横向布局分割线
                     *     如果是第一个Item，则不需要分割线
                     */
                    if (itemPosition != 0) {
                        outRect.set(dividerWidth, 0, 0, 0);
                    }
                    break;
                case GRID:
                    /**
                     * 表格格局分割线
                     *      1：当是第一个Item的时候，四周全部需要分割线
                     *      2：当是第一行Item的时候，需要额外添加顶部的分割线
                     *      3：当是第一列Item的时候，需要额外添加左侧的分割线
                     *      4：默认情况全部添加底部和右侧的分割线
                     */
                    RecyclerView.LayoutManager gridManager = parent.getLayoutManager();
                    if (gridManager instanceof GridLayoutManager) {
                        GridLayoutManager mGridLayoutManager = (GridLayoutManager) gridManager;
                        int mSpanCount = mGridLayoutManager.getSpanCount();//一行的数量
                        if (itemPosition == 0) {//1
                            outRect.set(dividerWidth, dividerWidth, dividerWidth, dividerWidth);
                        } else if ((itemPosition + 1) <= mSpanCount) {//2
                            outRect.set(0, dividerWidth, dividerWidth, dividerWidth);
                        } else if (((itemPosition + mSpanCount) % mSpanCount) == 0) {//3
                            outRect.set(dividerWidth, 0, dividerWidth, dividerWidth);
                        } else {//4
                            outRect.set(0, 0, dividerWidth, dividerWidth);
                        }
                    }
                    break;
                case WELL:
                    /**
                     * 表格格局分割线
                     *      1：当是第一个Item的时候，四周全部需要分割线
                     *      2：当是第一行Item的时候，需要额外添加顶部的分割线
                     *      3：当是第一列Item的时候，需要额外添加左侧的分割线
                     *      4：默认情况全部添加底部和右侧的分割线
                     */
                    RecyclerView.LayoutManager wellManager = parent.getLayoutManager();
                    if (wellManager instanceof GridLayoutManager) {
                        GridLayoutManager mGridLayoutManager = (GridLayoutManager) wellManager;
                        int mSpanCount = mGridLayoutManager.getSpanCount();//一行的数量
                        int childCount = parent.getAdapter().getItemCount();
                        int count = (int) Math.ceil((double) childCount / (double) mSpanCount);//总行数
                        if (((itemPosition + 1) % mSpanCount) > 0) {
                            outRect.set(0, 0, dividerWidth, dividerWidth);
                        }
                        if ((itemPosition / mSpanCount) + 1 < count) {
                            outRect.set(0, 0, dividerWidth, dividerWidth);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * 绘制纵向列表分割线
     *
     * @param c      绘制容器
     * @param parent RecyclerView
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        int mChildCount = parent.getChildCount();
        for (int i = 0; i < mChildCount; i++) {
            View mChild = parent.getChildAt(i);
            drawTop(c, mChild, parent);
        }
    }

    /**
     * 绘制横向列表分割线
     *
     * @param c      绘制容器
     * @param parent RecyclerView
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int mChildCount = parent.getChildCount();
        for (int i = 0; i < mChildCount; i++) {
            View mChild = parent.getChildAt(i);
            drawLeft(c, mChild, parent);
        }
    }

    /**
     * 绘制表格类型分割线
     *
     * @param c      绘制容器
     * @param parent RecyclerView
     */
    private void drawGrid(Canvas c, RecyclerView parent) {
        int mChildCount = parent.getChildCount();
        for (int i = 0; i < mChildCount; i++) {
            View mChild = parent.getChildAt(i);
            RecyclerView.LayoutManager mLayoutManager = parent.getLayoutManager();
            if (mLayoutManager instanceof GridLayoutManager) {
                GridLayoutManager mGridLayoutManager = (GridLayoutManager) mLayoutManager;
                int mSpanCount = mGridLayoutManager.getSpanCount();
                if (i == 0) {
                    drawTop(c, mChild, parent);
                    drawLeft(c, mChild, parent);
                }
                if ((i + 1) <= mSpanCount) {
                    drawTop(c, mChild, parent);
                }
                if (((i + mSpanCount) % mSpanCount) == 0) {
                    drawLeft(c, mChild, parent);
                }
                drawRight(c, mChild, parent);
                drawBottom(c, mChild, parent);
            }
        }
    }

    /**
     * 绘制井格类型分割线
     *
     * @param c      绘制容器
     * @param parent RecyclerView
     */
    private void drawWell(Canvas c, RecyclerView parent) {
        int mChildCount = parent.getChildCount();
        for (int i = 0; i < mChildCount; i++) {
            View mChild = parent.getChildAt(i);
            RecyclerView.LayoutManager mLayoutManager = parent.getLayoutManager();
            if (mLayoutManager instanceof GridLayoutManager) {
                GridLayoutManager mGridLayoutManager = (GridLayoutManager) mLayoutManager;
                int mSpanCount = mGridLayoutManager.getSpanCount();
                int childCount = parent.getAdapter().getItemCount();
                int count = (int) Math.ceil((double) childCount / (double) mSpanCount);//总行数
                if (((i + 1) % mSpanCount) > 0) {
                    drawRight(c, mChild, parent);
                }
                if ((i / mSpanCount) + 1 < count) {
                    drawBottom(c, mChild, parent);
                }
            }
        }
    }

    /**
     * 绘制右边分割线
     *
     * @param c            绘制容器
     * @param mChild       对应ItemView
     * @param recyclerView RecyclerView
     */
    private void drawLeft(Canvas c, View mChild, RecyclerView recyclerView) {
        RecyclerView.LayoutParams mChildLayoutParams = (RecyclerView.LayoutParams) mChild.getLayoutParams();
        int left = mChild.getLeft() - mChildLayoutParams.leftMargin - dividerWidth;
        int top = mChild.getTop() - mChildLayoutParams.topMargin;
        int right = mChild.getLeft() - mChildLayoutParams.rightMargin;
        int bottom;
        if (isGridLayoutManager(recyclerView)) {
            bottom = mChild.getBottom() + mChildLayoutParams.bottomMargin + dividerWidth;
        } else {
            bottom = mChild.getBottom() + mChildLayoutParams.bottomMargin;
        }
        c.drawRect(left, top, right, bottom, paint);
    }

    /**
     * 绘制顶部分割线
     *
     * @param c            绘制容器
     * @param mChild       对应ItemView
     * @param recyclerView RecyclerView
     */
    private void drawTop(Canvas c, View mChild, RecyclerView recyclerView) {
        RecyclerView.LayoutParams mChildLayoutParams = (RecyclerView.LayoutParams) mChild.getLayoutParams();
        int left;
        int top = mChild.getTop() - mChildLayoutParams.topMargin - dividerWidth;
        int right = mChild.getRight() + mChildLayoutParams.rightMargin;
        int bottom = mChild.getTop() - mChildLayoutParams.bottomMargin;
        if (isGridLayoutManager(recyclerView)) {
            left = mChild.getLeft() - mChildLayoutParams.leftMargin - dividerWidth;
        } else {
            left = mChild.getLeft() - mChildLayoutParams.leftMargin;
        }
        c.drawRect(left, top, right, bottom, paint);
    }

    /**
     * 绘制右边分割线
     *
     * @param c            绘制容器
     * @param mChild       对应ItemView
     * @param recyclerView RecyclerView
     */
    private void drawRight(Canvas c, View mChild, RecyclerView recyclerView) {
        RecyclerView.LayoutParams mChildLayoutParams = (RecyclerView.LayoutParams) mChild.getLayoutParams();
        int left = mChild.getRight() + mChildLayoutParams.leftMargin;
        int top;
        int right = mChild.getRight() + mChildLayoutParams.rightMargin + dividerWidth;
        int bottom = mChild.getBottom() + mChildLayoutParams.bottomMargin;
        if (isGridLayoutManager(recyclerView)) {
            top = mChild.getTop() - mChildLayoutParams.topMargin - dividerWidth;
        } else {
            top = mChild.getTop() - mChildLayoutParams.topMargin;
        }
        c.drawRect(left, top, right, bottom, paint);
    }

    /**
     * 绘制底部分割线
     *
     * @param c            绘制容器
     * @param mChild       对应ItemView
     * @param recyclerView RecyclerView
     */
    private void drawBottom(Canvas c, View mChild, RecyclerView recyclerView) {
        RecyclerView.LayoutParams mChildLayoutParams = (RecyclerView.LayoutParams) mChild.getLayoutParams();
        int left = mChild.getLeft() - mChildLayoutParams.leftMargin;
        int top = mChild.getBottom() + mChildLayoutParams.bottomMargin;
        int bottom = mChild.getBottom() + mChildLayoutParams.bottomMargin + dividerWidth;
        int right;
        if (isGridLayoutManager(recyclerView)) {
            right = mChild.getRight() + mChildLayoutParams.rightMargin + dividerWidth;
        } else {
            right = mChild.getRight() + mChildLayoutParams.rightMargin;
        }
        c.drawRect(left, top, right, bottom, paint);
    }

    /**
     * 判断RecyclerView所加载LayoutManager是否为GridLayoutManager
     *
     * @param recyclerView RecyclerView
     * @return 是GridLayoutManager返回true，否则返回false
     */
    private boolean isGridLayoutManager(RecyclerView recyclerView) {
        RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
        return (mLayoutManager instanceof GridLayoutManager);
    }

    /**
     * 初始化分割线类型
     *
     * @param orientation 分割线类型
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}