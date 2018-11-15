package com.bestsoft.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsoft.R;
import com.bestsoft.bean.ClassfyModel;
import com.bestsoft.bean.FiltModel;
import com.bestsoft.utils.GlideUtil;
import com.blankj.utilcode.utils.SizeUtils;

import java.util.List;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/13
 * @description:筛选弹框
 **/
public class FiltPopuWindow extends PopupWindow {
    public FiltPopuWindow(Context context, View view) {
        //这里可以修改popupwindow的宽高
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(view);
        initViews();
    }

    private void initViews() {
        setAnimationStyle(R.style.popwin_anim_style);
        //setBackgroundDrawable(new ColorDrawable(0x00000000));
        setFocusable(true);
        setOutsideTouchable(true);
    }

    public static class Builder {
        private Context context;
        private List<ClassfyModel> listData;
        private int columnCount;
        private GridLayout rootGridLayout;
        private LinearLayout contextll;
        //背景颜色
        private int colorBg = Color.parseColor("#F8F8F8");
        private int tabTextSize = 14;//SP

        private OnCloseListener listener;
        //当前加载的行数
        private int row = -1;
        private FiltPopuWindow mFiltPopuWindow;

        public Builder(Context context, OnCloseListener listener) {
            this.context = context;
            this.listener = listener;
        }

        /**
         * 设置数据源
         *
         * @return
         */
        public Builder setDataSource(List<ClassfyModel> listData) {
            this.listData = listData;
            return this;
        }

        public Builder setColumnCount(int columnCount) {
            this.columnCount = columnCount;
            return this;
        }

        public Builder setColorBg(int color) {
            colorBg = context.getResources().getColor(color);
            return this;
        }

        public Builder setTabTextSize(int tabTextSize) {
            this.tabTextSize = tabTextSize;
            return this;
        }


        public Builder build() {
            newItemLayout(getRowCount(), columnCount);
            //添加选项
            addTabs(listData);
            return this;
        }

        private void newItemLayout(int rowCount, int columnCount) {
            contextll = new LinearLayout(context);
            contextll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contextll.setBackgroundColor(context.getResources().getColor(R.color.color_33000000));
            contextll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mFiltPopuWindow != null) {
                        mFiltPopuWindow.dismiss();
                        //点击外部消失
                    }
                }
            });
            rootGridLayout = new GridLayout(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rootGridLayout.setOrientation(GridLayout.HORIZONTAL);
            rootGridLayout.setRowCount(rowCount);
            rootGridLayout.setColumnCount(columnCount);
            rootGridLayout.setBackgroundColor(colorBg);
            rootGridLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            int pandd = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
            lp.weight = 1;
            rootGridLayout.setPadding(pandd, pandd, pandd, pandd);
            contextll.addView(rootGridLayout, lp);
        }

        /**
         * 添加选项
         */

        private void addTabs(List<ClassfyModel> classfyModels) {

            for (int i = 0; i < classfyModels.size(); i++) {
                if (i % columnCount == 0) {
                    row++;
                }
                ClassfyModel classfyModel = classfyModels.get(i);
                final TextView lable = new TextView(context);
                final ImageView imgIcon = new ImageView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120,
                        120);//两个400分别为添加图片的大小
                imgIcon.setLayoutParams(params);
                LinearLayout container = new LinearLayout(context);
                container.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                container.setOrientation(LinearLayout.VERTICAL);
                container.setGravity(Gravity.CENTER);
                GlideUtil.loadRoundImage(context, classfyModel.getIcon(), 0, imgIcon);
                lable.setTextColor(context.getResources().getColor(R.color.filter_tab_color));
                lable.setSingleLine(true);
                lable.setGravity(Gravity.CENTER);
                lable.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                //这里可以自行修改tab框框的大小
                int panddT = context.getResources().getDimensionPixelSize(R.dimen.dp_4);
                int panddL = context.getResources().getDimensionPixelSize(R.dimen.dp_8);
                lable.setPadding(panddL, panddT, panddL, panddT);
                lable.setTextSize(tabTextSize);
                container.addView(imgIcon);
                container.addView(lable);
                rootGridLayout.addView(container, getItemLayoutParams(i, row));
                lable.setText(classfyModel.getName());
                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //lable.setSelected(true);
                        Log.i("sinle", classfyModel.getName() + "");
                        listener.onClick(mFiltPopuWindow, classfyModel);
                    }
                });
            }
        }


        private GridLayout.LayoutParams getItemLayoutParams(int i, int row) {
            //使用Spec定义子控件的位置和比重
            GridLayout.Spec rowSpec = GridLayout.spec(row, 1f);
            GridLayout.Spec columnSpec = GridLayout.spec(i % columnCount, 1f);
            //将Spec传入GridLayout.LayoutParams并设置宽高为0，必须设置宽高，否则视图异常
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams(rowSpec, columnSpec);
            lp.width = 0;
            lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
            lp.bottomMargin = context.getResources().getDimensionPixelSize(R.dimen.dp_8);
            if (i % columnCount == 0) {//最左边
                lp.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
                lp.rightMargin = context.getResources().getDimensionPixelSize(R.dimen.dp_20);
            } else if ((i + 1) % columnCount == 0) {//最右边
                lp.rightMargin = context.getResources().getDimensionPixelSize(R.dimen.dp_10);
            } else {//中间
                lp.rightMargin = context.getResources().getDimensionPixelSize(R.dimen.dp_20);
            }
            return lp;
        }

        /**
         * 计算行数
         *
         * @return
         */
        private int getRowCount() {
            int row = 0;
            row += (listData.size() / columnCount) + (listData.size() % columnCount > 0 ? 1 : 0);
            return row;
        }

        public FiltPopuWindow createPop() {
            if (listData == null || listData.size() == 0) {
                try {
                    throw new Exception("没有筛选条件");
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                return null;
            }
            mFiltPopuWindow = new FiltPopuWindow(context, contextll);
            return mFiltPopuWindow;
        }

        public interface OnCloseListener {
            void onClick(FiltPopuWindow popupWindow, ClassfyModel confirm);
        }

    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}
