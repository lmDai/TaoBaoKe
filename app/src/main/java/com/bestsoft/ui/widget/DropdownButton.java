package com.bestsoft.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bestsoft.R;

/**
 * @package: com.bestsoft.ui.widget
 * @user:xhkj
 * @date:2018/11/13
 * @description:
 **/
public class DropdownButton extends RelativeLayout {
    TextView textView;
    private boolean checked = false;

    public DropdownButton(Context context) {
        this(context, null);
    }

    public DropdownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_button, this, true);
        textView = view.findViewById(R.id.textView);

    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        Drawable icon;
        if (checked) {
            icon = getResources().getDrawable(R.drawable.ic_dropdown_normal);
            textView.setTextColor(getResources().getColor(R.color.colorBlack));
        } else {
            icon = getResources().getDrawable(R.drawable.ic_dropdown_normal);
            textView.setTextColor(getResources().getColor(R.color.colorBlack));
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    public boolean getChecked() {
        return checked;
    }

}
