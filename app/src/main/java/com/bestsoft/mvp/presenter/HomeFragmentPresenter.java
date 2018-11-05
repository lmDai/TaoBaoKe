package com.bestsoft.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bestsoft.Constant;
import com.bestsoft.R;
import com.bestsoft.common.https.ProgressObserver;
import com.bestsoft.common.https.rxUtils.RxUtil;
import com.bestsoft.common.utils.Utils;
import com.bestsoft.mvp.contract.HomeFragmentContract;
import com.bestsoft.mvp.contract.InputInvateInfoContract;
import com.bestsoft.mvp.model.LoginModel;
import com.bestsoft.ui.activity.IntroductionActivity;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;
import com.bestsoft.ui.adapter.BasePagerAdapter;
import com.bestsoft.ui.adapter.FastEntranceAdapter;
import com.bestsoft.ui.fragment.HomeFragment;
import com.bestsoft.ui.widget.GlideImageLoader;
import com.bestsoft.utils.IntentUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @package: com.bestsoft.mvp.presenter
 * @user:xhkj
 * @date:2018/10/29
 * @description:
 **/
public class HomeFragmentPresenter extends HomeFragmentContract.Presenter {
    private TabLayout tab;
    private ViewPager viewPager;

    @Override
    public DelegateAdapter initRecyclerView(RecyclerView recyclerView) {
        //初始化
        //创建VirtualLayoutManager对象
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getView().getContext());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);

        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        //设置适配器
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerView.setAdapter(delegateAdapter);
        return delegateAdapter;
    }

    @Override
    public BaseDelegateAdapter initTitle() {
        return new BaseDelegateAdapter(getView().getContext(), new LinearLayoutHelper(), R.layout.layout_home_title, 1, Constant.viewType.typeTitle) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.getView(R.id.ll_know).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentUtils.get().goActivity(getView().getContext(), IntroductionActivity.class);
                    }
                });
            }
        };
    }

    @Override
    public BaseDelegateAdapter initGvMenu() {
        //menu
        // 在构造函数设置每行的网格个数
        final TypedArray proPic = getView().getContext().getResources().obtainTypedArray(R.array.find_gv_image);
        final String[] proName = getView().getContext().getResources().getStringArray(R.array.find_gv_title);
        final List<Integer> images = new ArrayList<>();
        for (int a = 0; a < proName.length; a++) {
            images.add(proPic.getResourceId(a, R.drawable.ic_me));
        }
        proPic.recycle();
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setPadding(0, 10, 0, 10);
        gridLayoutHelper.setVGap(16);
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        return new BaseDelegateAdapter(getView().getContext(), gridLayoutHelper, R.layout.item_vp_grid_iv, 10, Constant.viewType.typeGv) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, @SuppressLint("RecyclerView") final int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_new_seed_title, proName[position]);
                holder.setImageResource(R.id.iv_new_seed_ic, images.get(position));
                holder.getView(R.id.ll_new_seed_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getView().setOnclick(position);
                    }
                });
            }
        };
    }


    @Override
    public BaseDelegateAdapter initSearch() {
        return new BaseDelegateAdapter(getView().getContext(), new LinearLayoutHelper(), R.layout.layout_home_search, 1, Constant.viewType.typeTitle) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);

            }
        };
    }

    @Override
    public BaseDelegateAdapter initBanner() {
        return new BaseDelegateAdapter(getView().getContext(), new LinearLayoutHelper(), R.layout.layout_home_banner, 1, Constant.viewType.typeTitle) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                Banner banner = holder.getView(R.id.banner);
                String[] urls = getView().getContext().getResources().getStringArray(R.array.url);

                List list = Arrays.asList(urls);
                List<?> images = new ArrayList(list);
                banner.setImages(images)
                        .setImageLoader(new GlideImageLoader())
                        .start();
            }
        };
    }

    @Override
    public BaseDelegateAdapter initFastEntrceTitle() {

        return new BaseDelegateAdapter(getView().getContext(), new LinearLayoutHelper(), R.layout.base_view_title, 1, Constant.viewType.typeTitle) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.setText(R.id.tv_title, "快速入口");
            }
        };
    }

    @Override
    public BaseDelegateAdapter initFastEntrace() {
        return new BaseDelegateAdapter(getView().getContext(), new LinearLayoutHelper(), R.layout.layout_home_entrace, 1, Constant.viewType.typeList) {

            @Override
            public void onViewRecycled(BaseViewHolder holder) {
                if (holder.itemView instanceof ViewPager) {
                    ((ViewPager) holder.itemView).setAdapter(null);
                }
            }

            @Override
            public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 1)
                    return new BaseViewHolder(
                            LayoutInflater.from(getView().getContext()).inflate(R.layout.layout_home_entrace, parent, false));

                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            protected void onBindViewHolderWithOffset(BaseViewHolder holder, int position, int offsetTotal) {

            }

            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                if (holder.itemView instanceof RecyclerView) {
                    RecyclerView recyclerView = (RecyclerView) holder.itemView;
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getView().getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    List<String> fruitList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        fruitList.add(i + "");
                    }
                    FastEntranceAdapter adapter = new FastEntranceAdapter(fruitList);
                    recyclerView.setAdapter(adapter);

                }
            }

        };
    }

    @Override
    public BaseDelegateAdapter initStickyTab() {
        return new BaseDelegateAdapter(getView().getContext(), new StickyLayoutHelper(), R.layout.layout_home_sticky, 1, Constant.viewType.typeSticky) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                tab = holder.getView(R.id.tab_layout);
                if (holder.itemView instanceof TabLayout) {
                    HomeFragmentPresenter.this.tab = (TabLayout) holder.itemView;
                }
            }
        };
    }

    @Override
    public BaseDelegateAdapter initFragment() {
        return new BaseDelegateAdapter(getView().getContext(), new LinearLayoutHelper(), R.layout.layout_home_viewpager, 1, Constant.viewType.typeFooter) {
            @Override
            public void onBindViewHolder(BaseViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                viewPager = holder.getView(R.id.view_pager);
                if (holder.itemView instanceof ViewPager) {
                    HomeFragmentPresenter.this.viewPager = (ViewPager) holder.itemView;
                }
            }
        };
    }


    public TabLayout getTab() {
        return tab;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
