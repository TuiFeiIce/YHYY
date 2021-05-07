package com.yhyy.qwframe.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.yhyy.qwframe.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by IceWolf on 2019/9/14.
 */

public class Recy_Blod extends RecyclerView.ItemDecoration {
    private Paint dividerPaint;
    int normal;
    int blod;
    ArrayList<Integer> intlist = new ArrayList<>();

    public Recy_Blod(Context context, int normal, int blod, int color, ArrayList<Integer> intlist) {
        dividerPaint = new Paint();
        dividerPaint.setColor(color);
        this.normal = normal;
        this.blod = blod;
        this.intlist = intlist;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int mChildCount = parent.getChildCount();
        for (int i = 0; i < mChildCount; i++) {
            View mChild = parent.getChildAt(i);
            RecyclerView.LayoutParams mChildLayoutParams = (RecyclerView.LayoutParams) mChild.getLayoutParams();
            int left = mChild.getLeft() - mChildLayoutParams.leftMargin;
            int top;
            int right = mChild.getRight() + mChildLayoutParams.rightMargin;
            int bottom = mChild.getTop() - mChildLayoutParams.bottomMargin;
            if (intlist.contains(i)) {
                top = mChild.getTop() - mChildLayoutParams.topMargin - blod;
                c.drawRect(left, top, right, bottom, dividerPaint);
            } else {
                top = mChild.getTop() - mChildLayoutParams.topMargin - normal;
                c.drawRect(left, top, right, bottom, dividerPaint);
            }

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = parent.getChildAdapterPosition(view);
        RecyclerView.Adapter mAdapter = parent.getAdapter();
        if (mAdapter != null) {
            if (itemPosition != 0) {
                if (intlist.contains(itemPosition)) {
                    outRect.set(0, blod, 0, 0);
                } else {
                    outRect.set(0, normal, 0, 0);
                }
            }
        }
    }
}
