package com.colud.jctl.ui.activity;

import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.colud.jctl.base.AppManager;
import com.colud.jctl.base.BaseActivity;
import com.colud.jctl.base.BasePesenter;
import com.colud.jctl.ui.custom.Html5WebView;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.jctl.colud.R;

import butterknife.BindView;

/**
 * Created by Jcty on 2017/4/17.
 */

public class NewInfoDetailActivity extends BaseActivity {
    @BindView(R.id.web_layout)
    LinearLayout mLayout;
    //	@BindView(R.id.nf_wv)
    //	WebView vw;
    //	@BindView(R.id.progress_bar)
    //	ProgressBar pb;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    private String mUrl;
    private Html5WebView mWebView;

    @Override
    protected BasePesenter onCreatePresenter() {
        return null;
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_newinfodetail;
    }

    @Override
    public void initViews() {
        initToolbar();//设置Toolbar
        initWebView();//初始化WebView
        initSettings();//初始化WebSettings

    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setNavigationIcon(R.drawable.img_back);

        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(this), 0, 0);
        }
    }

    protected void initWebView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        mWebView = new Html5WebView(NewInfoDetailActivity.this);
        mWebView.setLayoutParams(params);
        mWebView.addProgressBar();
        mLayout.addView(mWebView);

    }

    protected void initSettings() {
        //		WebSettings settings=vw.getSettings();
        //		//支持获取手势焦点
        //		vw.requestFocusFromTouch();
        //		//支持JS
        //		settings.setJavaScriptEnabled(true);
        //
        //		//支持获取手势焦点，输入用户名、密码或其他
        //
        //		settings.setJavaScriptEnabled(true);  //支持js
        //		settings.setPluginState(WebSettings.PluginState.ON);
        //		settings.setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级
        //		//设置自适应屏幕，两者合用
        //		settings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        //		settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //		settings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        //		settings.setBuiltInZoomControls(true); //设置内置的缩放控件。
        //		//若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        //		settings.setTextZoom(2);//设置文本的缩放倍数，默认为 100
        //
        //		settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //
        //		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        //		settings.supportMultipleWindows();  //多窗口
        //		settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        //		settings.setAllowFileAccess(true);  //设置可以访问文件
        //		settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        //		settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        //		settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        //		settings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //		settings.setStandardFontFamily("");//设置 WebView 的字体，默认字体为 "sans-serif"
        //		settings.setDefaultFontSize(15);//设置 WebView 字体的大小，默认大小为 16
        //		settings.setMinimumFontSize(13);//设置 WebView 支持的最小字体大小，默认为 8
    }

    @Override
    public void addListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData() {

        mUrl = getIntent().getStringExtra("url");
        if (mUrl != null && !"".equals(mUrl)) {
            mWebView.loadUrl(mUrl);
            toolbar.setCenterTitle(R.string.app_name);
        }
    }

    private long mOldTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mOldTime < 1500) {
                mWebView.clearHistory();
                mWebView.loadUrl(mUrl);
            } else if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                AppManager.newInstance().finishCurrentActivity();
            }
            mOldTime = System.currentTimeMillis();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
