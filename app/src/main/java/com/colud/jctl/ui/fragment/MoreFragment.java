package com.colud.jctl.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.colud.jctl.JctlApplication;
import com.colud.jctl.MainActivity;
import com.colud.jctl.api.ApiConstants;
import com.colud.jctl.api.IMutualListener;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.base.UIInitF;
import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.RateDialogItem;
import com.colud.jctl.bean.VersionUpdateBean;
import com.colud.jctl.counst.Constants;
import com.colud.jctl.mvp.contract.ExitContract;
import com.colud.jctl.mvp.presenter.ExitPresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.service.DownloadApkServiceNotification;
import com.colud.jctl.ui.activity.AboutUsActivity;
import com.colud.jctl.ui.activity.ChangePassActivity;
import com.colud.jctl.ui.activity.FeedbackActivity;
import com.colud.jctl.ui.activity.LoginActivity;
import com.colud.jctl.ui.activity.MsgInformActivity;
import com.colud.jctl.ui.activity.NewInfoActivity;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.ui.custom.dialog.RateDialog;
import com.colud.jctl.ui.custom.dialog.fragment.CustomDialogFragment;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.FileUtil;
import com.colud.jctl.utils.NetStatusUtil;
import com.colud.jctl.utils.SpUtils;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS;
import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS_EXIT;
import static com.colud.jctl.api.KeyConstants.USER_NAME;
import static com.colud.jctl.api.KeyConstants.isDownloading;
import static com.colud.jctl.counst.Counts.BROADCAST_ISEXIT;


/**
 * 更多
 * Created by Jcty on 2017/2/28.
 */

public class MoreFragment extends BaseFragment<ExitPresenter> implements UIInitF, ExitContract.View {

    //
    //	@BindView(R.id.status_bar)
    //	View statusBar;
    @BindView(R.id.more_listview)
    ListView moreListview;
    //	@BindView(R.id.tv_title)
    //	TextView tvTitle;

    protected SimpleAdapter mSimpleAdapter;
    @BindView(R.id.title_toolbar)
    TitleBar toolbar;

    @BindView(R.id.btn_exit)
    Button btnExit;

    private RateDialog dialog;

    private RateDialogItem dialogItem = new RateDialogItem();

    private Map<String, String> map = new HashMap<>();

    private Dialog progressDialog;

    private List<String> s = new ArrayList<>();

    private IsLoginReceiver mLoginReceiver;

    private IMutualListener mIMutualListener;


    public static MoreFragment getInstance(String pageName) {
        Bundle args = new Bundle();
        args.putString(Constants.ARGS, pageName);
        MoreFragment moreFragment = new MoreFragment();
        moreFragment.setArguments(args);
        return moreFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mIMutualListener=(IMutualListener)activity;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_more;
    }


    @Override
    public void addListener() {

    }



    @Override
    public void initViews() {
        //		btn_back.setVisibility(View.GONE);
        //		tvTitle.setText(JctlApplication.getAppResources().getString(R.string.text_h_gd));
        //		//		dialog = new RateDialog(getContext(), R.style.MyDialog);
        //		moreListview.addFooterView(new View(getActivity()));
        //		//判断SDK版本是否大于等于19，大于就让他显示，小于就要隐藏，不然低版本会多出来一个
        //		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //			BarStatusHeightUtil.setTranslucentStatus(getActivity(), true);
        //			statusBar.setVisibility(View.VISIBLE);
        //			//还有设置View的高度，因为每个型号的手机状态栏高度都不相同
        //		} else {
        //			statusBar.setVisibility(View.GONE);
        //		}
        initToolbar();
        setAdapterMineOrder();
    }

    private void initToolbar() {
        AppCompatActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.more_gd));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(activity), 0, 0);
        }
    }

    @Override
    public void initData()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginReceiver = new IsLoginReceiver();
        IntentFilter filter =new IntentFilter();
//        filter.addAction(BROADCAST_EXIT);
        filter.addAction(BROADCAST_ISEXIT);
        BroadCastManager.getInstance().registerReceiver(getActivity(),mLoginReceiver,filter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setAdapterMineOrder() {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("消息通知");
        strList.add("新消息提醒");
        strList.add("清空缓存");
        //		strList.add("使用方法");
        strList.add("关于我们");
        strList.add("意见反馈");
        strList.add("版本更新");
        strList.add("修改密码");
        ArrayList<Integer> ivList = new ArrayList<>();
        ivList.add(R.drawable.msg_info);
        ivList.add(R.drawable.new_info);
        ivList.add(R.drawable.qkhc);
        //		ivList.add(R.drawable.syff);
        ivList.add(R.drawable.gywm);
        ivList.add(R.drawable.yjfk);
        ivList.add(R.drawable.bbsj);
        ivList.add(R.drawable.xgmm);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();

        for (int i = 0; i < strList.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("textcontent", strList.get(i));
            map.put("ivdrawable", ivList.get(i));
            listItem.add(map);
        }
        String[] from = {"ivdrawable", "textcontent"};
        int[] to = {R.id.mine_item_img, R.id.mine_item_content};
        mSimpleAdapter = new SimpleAdapter(getActivity(), listItem,
                R.layout.item_mine_listview, from, to) {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                // TODO Auto-generated method stub
                return super.getView(position, convertView, parent);
            }
        };
        moreListview.setAdapter(mSimpleAdapter);
        mSimpleAdapter.notifyDataSetChanged();
        moreListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                String url;
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), MsgInformActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), NewInfoActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 2:
                        dialogItem.setTvTitle("确定清空缓存吗？");
                        dialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                        dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                            @Override
                            public void onClickLeft() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onClickRight() {
                                ToastUtils.showShort("已全部清空");
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        dialogItem.setTvTitle("");
                        dialogItem.setTvContent("");
                        //						showDialog(getView());
                        //						intent=new Intent(getActivity(),OrderFenXiangActivity.class);
                        //						getActivity().startActivity(intent);
                        break;
                    //					case 3:
                    //						ToastUtils.showShort("使用方法");
                    //						intent=new Intent(getActivity(),OrderFenXiangActivity.class);
                    //						getActivity().startActivity(intent);
                    //						break;
                    case 3:
                        intent = new Intent(getActivity(), AboutUsActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity(), FeedbackActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 5:
                        if (FileUtil.FilePath(getActivity(), ApiConstants.DOWNLOADPATH + "zhny.apk")) {
                            dialogItem.setTvTitle("已经下载完成!");
                            dialogItem.setTvContent("确定安装?");
                            dialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                            dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                                @Override
                                public void onClickLeft() {
                                    dialog.dismiss();

                                }

                                @Override
                                public void onClickRight() {
                                    dialog.dismiss();
                                    // 通过Intent安装APK文件
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setDataAndType(Uri.parse("file://" + ApiConstants.DOWNLOADPATH + "zhny.apk"),
                                            "application/vnd.android.package-archive");
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                }
                            });
                            dialog.show();
                            dialogItem.setTvTitle("");
                            dialogItem.setTvContent("");
                        } else {
                            map.clear();
                            if (NetStatusUtil.getVersion(getActivity()) != 0) {
                                map.put("inVersion", String.valueOf(NetStatusUtil.getVersion(getActivity())));
                                mPresenter.setUpdateV(map);
                            }
                        }

                        break;
                    case 6:
                        intent = new Intent(getActivity(), ChangePassActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    default:

                }
            }
        });
    }

    /**
     * 对话框
     */
    public void showDialog(View view) {
        //		ContactServiceDialogFragment csdf = ContactServiceDialogFragment.newInstance();
        //		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        //		csdf.show(ft, "contactDialog");
        final CustomDialogFragment dialogFragment = CustomDialogFragment.getInstance("清空缓存", "包括图片,安装包等");
        dialogFragment.show(getChildFragmentManager(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("已全部清空");
                dialogFragment.dismiss();
            }
        });
    }


    @OnClick({R.id.btn_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit:
                //				showDialog(getView());
                if (KeyConstants.LGOIN_IS) {
                    dialogItem.setTvTitle("确定退出登录？");
                    dialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                    dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                        @Override
                        public void onClickLeft() {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight() {
                            s.clear();
                            s.add(KeyConstants.USER_ITEM);
                            s.add(KeyConstants.KAY_REG);
                            s.add(KeyConstants.USER_PHOTO);
                            s.add(KeyConstants.KEY_SINGLEID);
                            s.add(USER_NAME);
                            SpUtils.removeCeChe(s);
                            dialog.dismiss();
                            mPresenter.setExit();

                        }
                    });
                    dialog.show();
                    dialogItem.setTvTitle("");
                    dialogItem.setTvContent("");
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("login",LOGIN_SUCCESS_EXIT);
                    startActivityForResult(intent,LOGIN_SUCCESS_EXIT);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected ExitPresenter onCreatePresenter() {
        return new ExitPresenter(this);
    }


    @Override
    public void showDialog() {
        //		JctlApplication.getGifLoadingView().show(getActivity().getFragmentManager(),"");
        if (progressDialog == null) {
            progressDialog = new Dialog(getActivity(), R.style.progress_dialog);
            progressDialog.setContentView(R.layout.dialog);
            progressDialog.setCancelable(true);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            msg.setText("请稍后...");
            progressDialog.show();
        }
    }

    @Override
    public void onSucceed(ExitItem data) {
        if (data != null && 1 == data.getFlag()) {
            ToastUtils.showShort(data.getMsg());
            KeyConstants.LGOIN_IS = false;
            btnExit.setText("请登录");
            mIMutualListener.actActivity(0,data);
        }

    }

    @Override
    public void onSucceedUpdate(final VersionUpdateBean data) {
        if (data.getVersion().getInVersion() != null) {
            dialogItem.setTvTitle("已更新至" + data.getVersion().getOutVersion());
            dialogItem.setTvContent(data.getVersion().getRemark());
            dialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
            dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                @Override
                public void onClickLeft() {
                    dialog.dismiss();

                }

                @Override
                public void onClickRight() {
                    dialog.dismiss();
                    if (NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_2G ||
                            NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_NO ||
                            NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_UNKNOWN) {
                        ToastUtils.showLong("抱歉,您当前网络较差,请连接Wifi网络进行下载更新。");
                        return;

                    } else if (NetStatusUtil.netSatus(getActivity()) == NetStatusUtil.NetState.NET_3G)

                    {
                        dialogItem.setTvTitle("您当前的网络是 3G网络");
                        dialogItem.setTvContent("是否要下载更新?");
                        dialog = new RateDialog(getContext(), R.style.MyDialog, dialogItem);
                        dialog.setMyClickListener(new RateDialog.onClickRateDialog() {
                            @Override
                            public void onClickLeft() {
                                dialog.dismiss();

                            }

                            @Override
                            public void onClickRight() {
                                dialog.dismiss();
                                if (!NetStatusUtil.isServiceWorked(getActivity()) && isDownloading) {
                                    Intent serviceIntent = new Intent(getActivity(), DownloadApkServiceNotification.class);
                                    serviceIntent.putExtra("downloadURL", data.getVersion().getUrl());
                                    serviceIntent.putExtra("savePath", ApiConstants.DOWNLOADPATH);
                                    serviceIntent.putExtra("mApkName", "zhny.apk");
                                    getActivity().startService(serviceIntent);
                                    isDownloading = false;
                                } else {
                                    ToastUtils.showLong("下载中请稍后...");
                                }
                            }
                        });
                        dialog.show();
                        dialogItem.setTvTitle("");
                        dialogItem.setTvContent("");
                    } else {
                        if (!NetStatusUtil.isServiceWorked(getActivity()) && isDownloading) {
                            Intent serviceIntent = new Intent(getActivity(), DownloadApkServiceNotification.class);
                            serviceIntent.putExtra("downloadURL", data.getVersion().getUrl());
                            serviceIntent.putExtra("savePath", ApiConstants.DOWNLOADPATH);
                            serviceIntent.putExtra("mApkName", "zhny.apk");
                            getActivity().startService(serviceIntent);
                            isDownloading = false;
                        } else

                        {
                            ToastUtils.showLong("下载中请稍后...");
                        }
                    }
                }
            });
            dialog.show();
            dialogItem.setTvTitle("");
            dialogItem.setTvContent("");
        } else if (!"".equals(data.getMsg())) {
            ToastUtils.showLong(data.getMsg());
        }

    }

    @Override
    public void onFailure(String err, Throwable e) {
        ToastUtils.showLong(err);
    }

    @Override
    public void onFail(String err) {
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode)
        {
            case LOGIN_SUCCESS:
                btnExit.setText(JctlApplication.getAppResources().getString(R.string.more_tcdl));
                break;
            default:
        }
    }
    /**
     * 接受广播并处理
     */
    class IsLoginReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action)
            {
                case BROADCAST_ISEXIT:
                    btnExit.setText(JctlApplication.getAppResources().getString(R.string.more_tcdl));
                    break;
                default:

            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(),mLoginReceiver);
    }
}
