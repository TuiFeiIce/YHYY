package com.yhyy.qwframe.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class VH extends RecyclerView.ViewHolder {
    private final SparseArray<View> viewSparseArray = new SparseArray<View>();
    private final View ConvertView;

    private VH(View view) {
        super(view);
        ConvertView = view;
        // setTag
        ConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param parent
     * @param layoutId
     * @return
     */
    public static VH get(ViewGroup parent,
                         int layoutId) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new VH(convertView);
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param Id
     * @return
     */

    public <T extends View> T getView(int Id) {
        View view = viewSparseArray.get(Id);
        if (view == null) {
            view = ConvertView.findViewById(Id);
            viewSparseArray.put(Id, view);
        }
        return (T) view;
    }
}
