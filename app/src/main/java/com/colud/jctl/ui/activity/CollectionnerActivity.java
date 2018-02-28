package com.colud.jctl.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.MenuAdapter;
import com.colud.jctl.adapters.listener.OnItemClickListener;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.MsgInformItem;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人收藏
 * Created by Jcty on 2017/3/22.
 */

public class CollectionnerActivity extends BaseActivity {
    @BindView(R.id.coll_rv)
    SwipeMenuRecyclerView collRv;
    @BindView(R.id.login_toolbar)
    TitleBar toolbar;
    @BindView(R.id.title_cotent)
    TextView tvContent;
    private static int isPoistion;
    @BindView(R.id.coll_btm_delete)
    LinearLayout collBtmDelete;
    private List<MsgInformItem> mList = new ArrayList<>();
    private MenuAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    protected static boolean STATE_TOUCH = false;

    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_collectionner;
    }

    @Override
    public void initViews() {
        setTitle();
        getData();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        collRv.setLayoutManager(manager);
        //		adapter=new MsgInformAdapter(this,mlist,true);
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        collRv.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        collRv.setSwipeMenuItemClickListener(menuItemClickListener);

        mAdapter = new MenuAdapter(mList);
        mAdapter.setOnItemClickListener(onItemClickListener);
        collRv.setAdapter(mAdapter);

        //初始化 开始加载更多的loading View
        //		mAdapter.setLoadingView(R.layout.load_loading_layout);
        //设置加载更多触发的事件监听
        //		mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
        //			@Override
        //			public void onLoadMore(boolean isReload) {
        //				loadMore();
        //			}
        //		});


        //设置item点击事件监听
    }

    protected void setTitle() {
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.tv_gl));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.mine_wdsc));
        toolbar.setNavigationIcon(R.drawable.img_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
        }
    }

    /**
     * Item点击监听。
     */
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(getApplicationContext(), "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemClickCheckBox(CheckBox cb, int position) {
            isPoistion = position;
            if (cb.isChecked()) {
                cb.setChecked(false);
            } else {
                cb.setChecked(true);
            }

        }

        @Override
        public void onItemClickItemView(View view, int position) {
        }
    };

    @Override
    public void addListener() {
        // 设置菜单Item点击监听。
        collRv.setSwipeMenuItemClickListener(menuItemClickListener);
    }

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(getApplicationContext(), "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(getApplicationContext(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }

            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                return;
            } else {
                mList.remove(adapterPosition);
                mAdapter.notifyItemRemoved(adapterPosition);
            }
        }
    };
    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.margin_50dp);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            //			{
            //				SwipeMenuItem addItem = new SwipeMenuItem(getApplicationContext())
            //						.setBackgroundDrawable(R.color.color_ECECEC)// 点击的背景。
            //						.setImage(R.mipmap.ic_action_add) // 图标。
            //						.setWidth(width) // 宽度。
            //						.setHeight(height); // 高度。
            //				swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
            //
            //				SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
            //						.setBackgroundDrawable(R.drawable.selector_red)
            //						.setImage(R.mipmap.ic_action_close)
            //						.setWidth(width)
            //						.setHeight(height);
            //
            //				swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            //			}

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem closeItem = new SwipeMenuItem(getApplicationContext())
                        .setBackgroundDrawable(R.color.grey)
                        //						.setImage(R.mipmap.ic_action_delete)
                        .setText("管理") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);

                swipeRightMenu.addMenuItem(closeItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext())
                        .setBackgroundDrawable(R.color.red)
                        //						.setImage(R.mipmap.ic_action_close)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                //				SwipeMenuItem addItem = new SwipeMenuItem(getApplicationContext())
                //						.setBackgroundDrawable(R.color.red)
                //						.setText("删除")
                //						.setTextColor(Color.WHITE)
                //						.setWidth(width)
                //						.setHeight(height);
                //				swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };

    @Override
    public void initData() {

    }

    private void getData() {
        mList.add(new MsgInformItem("温室环境监测系统让土地生 “金”", "现在一年四季我们都可以在市场上买到新鲜大蒜阿斯顿阿斯顿阿斯阿斯顿阿斯顿", "3/20"));
        mList.add(new MsgInformItem("农业物联网项目的实际应用", "社会在发展，科技在进步，农业也不能固步自封大蒜阿斯顿阿斯顿阿斯阿斯顿阿斯顿", "3/22"));
        mList.add(new MsgInformItem("大棚环境远程监控系统让蝴蝶部分阿迪阿斯顿阿斯顿", "蝴蝶兰种植不像垃圾，种辣椒是一种粗放种植阿斯顿等等等", "3/23"));
    }

    @OnClick({R.id.coll_btm_delete, R.id.title_cotent})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_cotent:
                if (mList.size() > 0) {
                    if ("管理".equals(tvContent.getText().toString())) {
                        tvContent.setText("取消");
                        collBtmDelete.setVisibility(View.VISIBLE);
                        STATE_TOUCH = true;
                    } else {
                        collBtmDelete.setVisibility(View.GONE);
                        STATE_TOUCH = false;
                        tvContent.setText("管理");
                    }
                } else {
                    ToastUtils.showLong("暂无消息");
                }
                break;
            case R.id.tv_qx:
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < mList.size(); i++) {
                    if (mAdapter.getIsSelected().get(i)) {
                        mAdapter.getIsSelected().put(i, false);
                    } else {
                        mAdapter.getIsSelected().put(i, true);
                    }
                }
                // 刷新listview和TextView的显示
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.coll_btm_delete:
                //				for (int i=0;i<mlist.size();i++){
                //					if (adapter.getIsSelected().valueAt(i)){
                //						adapter.getIsSelected().delete(adapter.getIsSelected().keyAt(i));
                //						//                当前的项已被删除，记得i要自减，否则会出现混乱
                //						i--;
                //					}
                //				}
                //				adapter.notifyDataSetChanged();
                mList.remove(isPoistion);
                mAdapter.notifyItemRemoved(isPoistion);
                break;
        }
        mAdapter.setStateTouch(STATE_TOUCH);
        mAdapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            collBtmDelete.setVisibility(View.GONE);
            STATE_TOUCH = false;
            tvContent.setText("管理");
            ToastUtils.showLong("暂无消息");
        }

    }

    //	private void loadMore() {
    //
    //		new Handler().postDelayed(new Runnable() {
    //			@Override
    //			public void run() {
    //
    //				if (mAdapter.getItemCount() > 15 && isFailed) {
    //					isFailed = false;
    //					//加载失败，更新footer view提示
    //					mAdapter.setLoadFailedView(R.layout.load_failed_layout);
    //				} else if (mAdapter.getItemCount() > 17) {
    //					//加载完成，更新footer view提示
    //					mAdapter.setLoadEndView(R.layout.load_end_layout);
    //				} else {
    //					final List<String> data = new ArrayList<>();
    //					for (int i = 0; i < 12; i++) {
    //						data.add("item--" + (mAdapter.getItemCount() + i - 1));
    //					}
    //					//刷新数据
    //					mAdapter.setLoadMoreData(data);
    //				}
    //			}
    //		}, 2000);
    //	}
}
