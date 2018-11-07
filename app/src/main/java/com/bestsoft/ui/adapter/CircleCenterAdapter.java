package com.bestsoft.ui.adapter;

import com.bestsoft.R;
import com.bestsoft.bean.CircleCenterModel;
import com.bestsoft.bean.PhotoInfo;
import com.bestsoft.ui.widget.MultiImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * @package: com.bestsoft.ui.adapter
 * @user:xhkj
 * @date:2018/11/2
 * @description: 发圈中心
 **/
public class CircleCenterAdapter extends BaseQuickAdapter<CircleCenterModel, BaseViewHolder> {
    public CircleCenterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleCenterModel item) {
        helper.addOnClickListener(R.id.txt_share);
        MultiImageView multiImageView = helper.getView(R.id.multiply);

        ArrayList<PhotoInfo> imageInfo = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            PhotoInfo info = new PhotoInfo();
            info.setUrl("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
            imageInfo.add(info);
        }
        multiImageView.setList(imageInfo);
    }
}
