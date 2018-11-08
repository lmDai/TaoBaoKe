package com.bestsoft.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bestsoft.R;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/8
 * @description:
 **/
public class MenuAdapter extends DelegateAdapter.Adapter<MenuAdapter.MainViewHolder> {


    private Context mContext;

    private LayoutHelper mLayoutHelper;
    private int type;


    private int mCount = 0;

    public MenuAdapter(Context context, LayoutHelper layoutHelper, int count,
                       int type) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.type = type;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (type == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false);
        } else if (type == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_menu_two, parent, false);
        }
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int i) {


    }


    @Override
    public int getItemCount() {
        return mCount;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}

