package com.bestsoft.ui.adapter;

import com.bestsoft.R;
import com.bestsoft.bean.ArticleModel;
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
public class CircleCenterAdapter extends BaseQuickAdapter<ArticleModel, BaseViewHolder> {
    public CircleCenterAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleModel item) {
        MultiImageView multiImageView = helper.getView(R.id.multiply);
        ArrayList<PhotoInfo> imageInfo = new ArrayList<>();
        PhotoInfo info = new PhotoInfo();
        info.setUrl(item.getShare_img());
        imageInfo.add(info);
        multiImageView.setList(imageInfo);
        helper.setText(R.id.txt_name, item.getName())
                .setText(R.id.txt_create_at, item.getCreate_at())
                .setText(R.id.txt_content, item.getContent())
                .addOnClickListener(R.id.ll_share);
    }
}
