package com.colud.jctl.ui.activity;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.bean.ChannelsBean;
import com.colud.jctl.mvp.contract.ChannelContract;
import com.colud.jctl.mvp.presenter.ChannelPresenter;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.yyl.videolist.utils.V;
import com.yyl.videolist.video.VlcVideoView;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 摄像头播放界面
 * Created by Jcty on 2017/8/24.
 */

public class PlayActivity extends BaseActivity<ChannelPresenter> implements ChannelContract.View {

    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    @BindView(R.id.vlc)
    VlcVideoView vlcVideoView;

    @BindView(R.id.up)
    Button up;
    @BindView(R.id.down)
    Button down;
    @BindView(R.id.left)
    Button left;
    @BindView(R.id.right)
    Button right;

    @BindView(R.id.max)
    Button max;

    @BindView(R.id.min)
    Button min;

    private Dialog dialog;

    private Handler handler = new Handler();

    private String url = "";

    private String channel = "";

    private String name = "";

    private Map<String, Object> map = new ArrayMap<>();


    @OnClick({R.id.max, R.id.min, R.id.up, R.id.down, R.id.left, R.id.right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.min:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "zoomout");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                    stopPTZ();
                }
                break;
            case R.id.max:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "zoomin");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                    stopPTZ();
                }
                break;
            case R.id.up:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "up");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                    stopPTZ();
                } else {
                    ToastUtils.showLong("请稍后,正在获取通道信息。");
                }

                break;
            case R.id.down:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");
                    map.put("command", "down");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                    stopPTZ();
                } else {
                    ToastUtils.showLong("请稍后,正在获取通道信息。");
                }
                break;
            case R.id.left:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "left");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                    stopPTZ();
                } else {
                    ToastUtils.showLong("请稍后,正在获取通道信息。");
                }
                break;
            case R.id.right:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "right");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                    stopPTZ();
                } else {
                    ToastUtils.showLong("请稍后,正在获取通道信息。");
                }
                break;
            default:
                map.clear();
                if (channel != null) {
                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "stop");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);
                }
                break;
        }
    }

    /**
     * 停止PTZ
     */
    private void stopPTZ() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                map.clear();
                if (channel != null) {

                    map.put("channel", Integer.valueOf(channel));
                    map.put("actiontype", "single");//single  continuous
                    map.put("command", "stop");
                    map.put("speed", 5);
                    map.put("protocol", "onvif");
                    mPresenter.setPTZ(map);

                }
            }
        }, 500);

    }

    @Override
    protected ChannelPresenter onCreatePresenter() {
        return new ChannelPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_play;
    }

    @Override
    public void initViews() {
        initTitle();
        //		String url = "rtmp://www.e-unite.cn:10935/hls/stream_1";
        //		vlcVideoView = V.findV(this, R.id.vlc);
        //		vlcVideoView.onAttached(this);
        //		vlcVideoView.playVideo(url);


        //		try{
        //			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
        //				mediaPlayer.stop();
        //				mediaPlayer.release();
        //				mediaPlayer = null;
        //			}
        //			mediaPlayer = new MediaPlayer(libVLC);

        //
        //
        //			//
        //			mediaPlayer.getVLCVout().setVideoSurface(srfc.getHolder().getSurface(), srfc.getHolder());
        //			//播放前还要调用这个方法
        //			mediaPlayer.getVLCVout().attachViews();
        //
        //			Media media = new Media(libVLC, Uri.parse(url));
        //
        //			mediaPlayer.setMedia(media);
        //			mediaPlayer.play();
        //
        //
        //		}catch (Exception e){
        //           e.printStackTrace();
        //		}

    }

    private void initTitle() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        if (!name.equals("")) {
            toolbar.setCenterTitle(name);
        } else {
            toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.sxtgc));
        }
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }

    @Override
    public void onBackPressed() {
        if (vlcVideoView.onBackPressed(this))
            return;
        super.onBackPressed();
    }

    @Override
    public void addListener() {

    }

    @Override
    public void initData() {
        channel = getIntent().getStringExtra("channel");
        name = getIntent().getStringExtra("name");
        if (channel != null) {
            map.clear();
            map.put("channel", Integer.valueOf(channel));
            map.put("protocol", "RTMP");
            mPresenter.setChannelData(map);

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        //		if (mediaPlayer != null) {
        //			mediaPlayer.pause();
        //		}
    }

    @Override
    protected void onResume() {
        super.onResume();
        //		if (mediaPlayer != null) {
        //			mediaPlayer.play();
        //		}
    }

    @Override
    public void showDialog() {
        if (dialog == null) {
            dialog = new Dialog(PlayActivity.this, R.style.progress_dialog);
            dialog.setContentView(R.layout.dialog);
            dialog.setCancelable(true);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("请稍后...");
            dialog.show();
        }
    }

    @Override
    public void onSucceed(ChannelsBean data) {

        vlcVideoView = V.findV(this, R.id.vlc);
        vlcVideoView.onAttached(this);
        url = data.getEasyDarwin().getBody().getURL().replace("{host}", "www.e-unite.cn");
        //		url = data.getEasyDarwin().getBody().getURL().replace("{host}", "192.168.0.110");
        vlcVideoView.playVideo(url);
    }

    @Override
    public void onSucceedPTZ(ChannelsBean data) {
        if (data != null && data.getEasyDarwin().getHeader().getErrorNum() != null) {
            if (data.getEasyDarwin().getHeader().getErrorNum().equals("200")) {

            }
        } else {
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
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

}
