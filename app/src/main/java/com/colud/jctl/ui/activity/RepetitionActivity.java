package com.colud.jctl.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.adapters.activity.RepetitionAdapterDemo;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.bean.RepBean;
import com.colud.jctl.base.AppManager;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 开关重复页
 * Created by Jcty on 2017/4/6.
 */

public class RepetitionActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rep_rv)
    RecyclerView rv;


    //	private RepetitionAdapter mAdapter;
    private RepetitionAdapterDemo mAdapter;

    //	private List<String>mList=new ArrayList<>();


    private RepBean repBean;

    private List<Integer> intList = new ArrayList<>();

    private ArrayList<Integer> mList;


    @OnClick({R.id.btn_back, R.id.ll_back, R.id.tv_content})
    public void onClick(View v) {
        Intent mIntent = new Intent();
        switch (v.getId()) {
            case R.id.ll_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.btn_back:
                AppManager.newInstance().finishCurrentActivity();
                break;
            case R.id.tv_content:
                //				mAdapter.updateDataSet(mAdapter.getSelectedItem());
                mIntent.putExtra("conList", mAdapter.getSelectedItem());
                this.setResult(KeyConstants.INTENT_RESULT, mIntent);
                AppManager.newInstance().finishCurrentActivity();
                intList.clear();
                mList.clear();
                //				mAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected BasePesenter onCreatePresenter() {
        return new BasePesenter();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_repetition;
    }

    @Override
    public void initViews() {
        tvTitle.setText(JctlApplication.getAppResources().getString(R.string.tv_cf));
        tvContent.setVisibility(View.VISIBLE);
        tvContent.setText(JctlApplication.getAppResources().getString(R.string.mine_wc));
        initAdapter();

    }

    protected void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        mAdapter = new RepetitionAdapterDemo(repBean.getWeek());
        //		mAdapter = new RepetitionAdapter(repBean.getWeek());
        //		mAdapter.setOnItemClickListener(onItemClickListener);
        rv.setAdapter(mAdapter);
        if (mList.size() > 0) {
            mAdapter.getNewItem(mList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        //这里就是传过来的数据sList从哪里传到哪里
        //我把下面的数据，先设置好，然后返回去了。setResult 方法，然后我在回来的时候，把我之前选中的标记上就可以了。
        //名字弄混了，改好了
        ArrayList<String> slist = new ArrayList<>();
        mList = (ArrayList<Integer>) getIntent().getSerializableExtra("sList");
        repBean = new RepBean();
        slist.add("星期日");
        slist.add("星期一");
        slist.add("星期二");
        slist.add("星期三");
        slist.add("星期四");
        slist.add("星期五");
        slist.add("星期六");
        repBean.setWeek(slist);

    }
}
