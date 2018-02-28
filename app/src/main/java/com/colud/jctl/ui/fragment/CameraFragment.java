package com.colud.jctl.ui.fragment;

import android.widget.GridView;

import com.colud.jctl.adapters.activity.CameraAdapter;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.bean.CameraBean;
import com.colud.jctl.mvp.contract.CameraContract;
import com.colud.jctl.mvp.presenter.CameraPresenter;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 视频监控页面
 * Created by Jcty on 2017/4/5.
 */

public class CameraFragment extends BaseFragment<CameraPresenter> implements CameraContract.View {


    @BindView(R.id.grid_test)
    GridView gridView;


    private CameraAdapter mAdapter;


    @Override
    protected CameraPresenter onCreatePresenter() {

        return new CameraPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_camera;
    }

    @Override
    public void initViews() {

        initAdapter();

    }

    private void initAdapter() {

        mAdapter = new CameraAdapter(getActivity(), R.layout.item_camera_layout, null);


    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {

        mPresenter.setCameraData();

    }


    //	@OnClick({R.id.camera_ll_a, R.id.camera_ll_b})
    //	public void onClick(View view) {
    //		Intent intent;
    //		switch (view.getId()) {
    //			case R.id.camera_ll_a:
    //				intent=new Intent(getActivity(), NewInfoDetailActivity.class);
    //				intent.putExtra("url","http://60.255.50.139/");
    //				getActivity().startActivity(intent);
    //				break;
    //			case R.id.camera_ll_b:
    //				intent=new Intent(getActivity(), NewInfoDetailActivity.class);
    //				intent.putExtra("url","http://60.255.50.139/");
    //				getActivity().startActivity(intent);
    //				break;
    //		}
    //	}

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(CameraBean data) {

        if (!data.getEasyDarwin().getBody().getChannelCount().equals("0")) {

            mAdapter.setGridData((ArrayList<CameraBean.EasyDarwinBean.BodyBean.ChannelsBean>) data.getEasyDarwin().getBody().getChannels());

            gridView.setAdapter(mAdapter);

            mAdapter.notifyDataSetChanged();
        } else {

            ToastUtils.showLong("暂无摄像头信息");
        }

    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }
}
