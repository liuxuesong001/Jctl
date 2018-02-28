package com.colud.jctl.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.bean.BazaarItem;
import com.colud.jctl.mvp.contract.BazaarContract;
import com.colud.jctl.mvp.presenter.BazaarPresenter;
import com.colud.jctl.ui.activity.BazaarDetailActivity;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.socks.library.KLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 市场动态页
 * Created by Jcty on 2017/3/15.
 */

public class BazaarDynaminFragment extends BaseFragment<BazaarPresenter> implements BazaarContract.View {

    private int addressType = 0;

    private Dialog progressDialog;



    @BindView(R.id.gv)
    GridView mGridView;

    private SimpleAdapter mAdapter;


    private int[] imgs = new int[]{

            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.g,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
    };


    @Override
    public int getContentViewId() {
        return R.layout.fragment_bazaardy_namic;
    }


    @Override
    public void initViews() {

        List<Map<String, Object>> maps = new ArrayList<>();

        for (int i = 0; i < imgs.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("icon", imgs[i]);
            maps.add(map);
        }
        mAdapter = new SimpleAdapter(getActivity(), maps, R.layout.item_bazaar_img, new String[]{"icon"}, new int[]{R.id.bazaar_img});

        mGridView.setAdapter(mAdapter);
    }

    @Override
    public void addListener() {

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addressType = ++position;
                mPresenter.setBazaaData(String.valueOf(addressType));
            }
        });
    }

    @Override
    public void initData() {

    }


    @Override
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(getActivity(), R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText(JctlApplication.getAppResources().getString(R.string.loading));
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(BazaarItem data) {
        if (data.getMktDyns().size() > 0) {
            Intent intent = new Intent(getActivity(), BazaarDetailActivity.class);
            intent.putExtra("address", (Serializable) data.getMktDyns());
            intent.putExtra("type", addressType);
            startActivity(intent);
        } else {
            ToastUtils.showLong(data.getMsg());
        }

    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
        KLog.d(err);
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected BazaarPresenter onCreatePresenter() {
        return new BazaarPresenter(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
