package com.colud.jctl.ui.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.colud.jctl.JctlApplication;
import com.colud.jctl.MainActivity;
import com.colud.jctl.api.IMutualListener;
import com.colud.jctl.api.KeyConstants;
import com.colud.jctl.base.BaseFragment;
import com.colud.jctl.base.UIInitF;
import com.colud.jctl.bean.ExitItem;
import com.colud.jctl.bean.IconItem;
import com.colud.jctl.bean.RegItem;
import com.colud.jctl.bean.UserItem;
import com.colud.jctl.counst.Constants;
import com.colud.jctl.mvp.contract.IconContract;
import com.colud.jctl.mvp.presenter.IconPresenter;
import com.colud.jctl.receiver.BroadCastManager;
import com.colud.jctl.ui.activity.CollectionnerActivity;
import com.colud.jctl.ui.activity.HelpCenterActivity;
import com.colud.jctl.ui.activity.LoginActivity;
import com.colud.jctl.ui.activity.PersonageActivity;
import com.colud.jctl.ui.custom.TitleBar;
import com.colud.jctl.utils.BarStatusHeightUtil;
import com.colud.jctl.utils.FileUtil;
import com.colud.jctl.utils.ToastUtils;
import com.jctl.colud.R;
import com.roger.gifloadinglibrary.GifLoadingView;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.colud.jctl.api.KeyConstants.LOGIN_SUCCESS;
import static com.colud.jctl.counst.Counts.BROADCAST_EXIT;
import static com.colud.jctl.counst.Counts.BROADCAST_LOGIN;


/**
 * 我的页面
 * Created by Jcty on 2017/2/28.
 */

public class MineFragment extends BaseFragment<IconPresenter> implements UIInitF, IconContract.View {


    @BindView(R.id.mine_listview)
    ListView mineListview;
    @BindView(R.id.mine_login_rl)
    LinearLayout mineLoginRl;
    @BindView(R.id.mine_circleImageView)
    CircleImageView mImageView;
    @BindView(R.id.tv_username)
    TextView tvName;

    @BindView(R.id.mine_toolbar)
    TitleBar toolbar;

    private SimpleAdapter mSimpleAdapter;

    private UserItem userItem;

    private RegItem regItem;
    private GifLoadingView mGifLoadingView;

    private ExitReceiver mExitReceiver;

    private Map<String, String> map = new ArrayMap<>();
    /**
     * 判断的标识
     */
    //	private File filepath;//返回的文件地址
    private String filepath = "";//返回的文件地址
    private File pathFile;
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private static final int UPLOAD_INIT_PROCESS = 4;//上传初始化
    protected static final int UPLOAD_FILE_DONE = 2;//上传中
    private static final int UPLOAD_IN_PROCESS = 5;//上传文件响应


    private Uri mImageUri; //图片路径Uri
    private String mImagePath;
    private String mFileName; //图片名称
    private Bitmap mBitmap;

    private IMutualListener mIMutualListener;


    public static MineFragment getInstance(String pageName) {
        Bundle args = new Bundle();
        args.putString(Constants.ARGS, pageName);
        MineFragment mineFragment = new MineFragment();
        mineFragment.setArguments(args);
        return mineFragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mIMutualListener=(IMutualListener)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExitReceiver = new ExitReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_EXIT);
        intentFilter.addAction(BROADCAST_LOGIN);
        BroadCastManager.getInstance().registerReceiver(getActivity(),mExitReceiver,intentFilter);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void addListener() {
    }

    @Override
    public void initViews() {
        initToolbar();

        mImageView.bringToFront();
        //		mineListview.addHeaderView(new View(getActivity()));
        //		mineListview.addFooterView(new View(getActivity()));
        setAdapterMineOrder();
    }

    private void initToolbar() {
        AppCompatActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示自带标题
        toolbar.setCenterTitle(JctlApplication.getAppResources().getString(R.string.mine_wd));
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            toolbar.setPadding(0, BarStatusHeightUtil.getStatusBarHeightAll(getActivity()), 0, 0);
        }
    }

    @OnClick({R.id.mine_login_rl, R.id.mine_circleImageView})
    public void onClick(View v) {
        Intent in;
        switch (v.getId()) {
            case R.id.mine_login_rl:
                in = new Intent(getActivity(), LoginActivity.class);
                in.putExtra("login",LOGIN_SUCCESS);
                startActivityForResult(in,LOGIN_SUCCESS);
                break;
            case R.id.mine_circleImageView:
                if (KeyConstants.LGOIN_IS) {
                    getPhoto();
                } else {
                    ToastUtils.showLong("请登录后设置头像");
                }
                //				in = new Intent(getActivity(), LoginActivity.class);
                //				startActivity(in);
                break;
            default:
                break;
        }
    }


    @Override
    public void initData() {

        //        userItem = (UserItem) JctlApplication.getCache().getAsObject(KeyConstants.USER_ITEM);
        //
        //        regItem = (RegItem) JctlApplication.getCache().getAsObject(KeyConstants.KAY_REG);
        //
        //        if (userItem != null) {
        //            mineLoginRl.setVisibility(View.GONE);
        //            tvName.setVisibility(View.VISIBLE);
        //            tvName.setText(userItem.getUser().getName());
        //        }
        //        if (regItem != null) {
        //            mineLoginRl.setVisibility(View.GONE);
        //            tvName.setVisibility(View.VISIBLE);
        //            tvName.setText(regItem.getLoginName());
        //        }
        //
        //        Bitmap iconBit = JctlApplication.getCache().getAsBitmap(KeyConstants.USER_PHOTO);
        //        if (iconBit != null) {
        //            mImageView.setImageBitmap(iconBit);
        //        }

    }



    @Override
    public void onResume() {

        super.onResume();
    }

    private void setAdapterMineOrder() {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("帮助中心");
        strList.add("我的收藏");
        strList.add("个人资料");
        ArrayList<Integer> ivList = new ArrayList<>();
        ivList.add(R.drawable.bzzx);
        ivList.add(R.drawable.wdsc);
        ivList.add(R.drawable.grzl);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

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
        mineListview.setAdapter(mSimpleAdapter);
        mSimpleAdapter.notifyDataSetChanged();
        mineListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                String url;
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), HelpCenterActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), CollectionnerActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), PersonageActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    default:
                        break;

                }
            }
        });
    }

    /**
     * 从相册选择图片来源
     */
    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    /**
     * 从系统相机选择图片来源
     */
    private void getCamera() {
        //图片名称 时间命名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        mFileName = format.format(date);
        Log.d("data", "mFileName=" + mFileName);

        //存储至DCIM文件夹
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        File outputImage = new File(path, mFileName + ".jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
            mImagePath = path + "/" + mFileName + ".jpg";
            Log.d("data", "mImagePath=" + mImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //将File对象转换为Uri并启动照相程序
        mImageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri); //指定图片输出地址
        startActivityForResult(intent, CAMERA_REQUEST); //启动照相
        //		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //		// 下面这句指定调用相机拍照后的照片存储的路径
        //		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
        //				Environment.getExternalStorageDirectory(), "hand.jpg")));
        //		startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST://从相册取
                if (data != null) {
                    mImageUri = data.getData();
                    //对相册取出照片进行裁剪
                    photoClip(mImageUri);
                }
                break;
            case PHOTO_CLIP:
                //完成
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        //图片解析成Bitmap对象
                        mBitmap = extras.getParcelable("data");
                        //						try {
                        //							mBitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(mImageUri));
                        //						} catch (FileNotFoundException e) {
                        //							e.printStackTrace();
                        //						}
                        //获得图片路径
                        //						try {
                        //							pathFile = FileUtil.saveFile(mBitmap, Environment.getExternalStorageDirectory().toString(), "icon.jpg");
                        //						} catch (IOException e) {
                        //							e.printStackTrace();
                        //						}
                        filepath = FileUtil.bitmapToBase64(mBitmap);
                        //						try {
                        //							filepath=FileUtil.encodeBase64File("",pathFile);
                        //						} catch (Exception e) {
                        //							e.printStackTrace();
                        //						}
                        //						Bitmap nmap=FileUtil.base64ToBitmap(filepath);
                        //上传照片
                        toUploadFile();
                        //上传完成将照片写入imageview与用户进行交互
                    }
                }
                break;
            case LOGIN_SUCCESS:
                if (data != null) {
                    userItem = (UserItem) data.getSerializableExtra("userData");
                    if (userItem != null) {
                        mineLoginRl.setVisibility(View.GONE);
                        tvName.setVisibility(View.VISIBLE);
                        tvName.setText(userItem.getUser().getName());
                        if (userItem.getUser().getPhoto() != null) {
                            Glide.with(this)
                                    .load(userItem.getUser().getPhoto())
                                    .asBitmap()
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            mImageView.setImageBitmap(resource);
                                        }
                                    }); //方法中设置asBitmap可以设置回调类型
                        }
                        mIMutualListener.actActivity(1, userItem);
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /****
     * 调用系统自带切图工具对图片进行裁剪
     * 微信也是
     *
     * @param uri
     */
    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }

    /****
     * 调用系统自带切图工具对图片进行裁剪
     * 微信也是
     *
     * @param uri
     */
    private void photoClipXj(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
        intent.setDataAndType(mImageUri, "image/*");
        intent.putExtra("scale", true);
        //设置宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        //广播刷新相册
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(mImageUri);
        getActivity().sendBroadcast(intentBc);
        startActivityForResult(intent, PHOTO_CLIP); //设置裁剪参数显示图片至ImageView
    }

    /**
     * 上传图片到服务器
     */
    private void toUploadFile() {
        map.clear();
        if (!"".equals(filepath)) {
            map.put("singleId", KeyConstants.USER_SINGLEID);
            map.put("file", filepath);
            map.put("reg", "png");
            mPresenter.setIconData(map);
        }
        //		@Query("file") String file,
        //                                     @Query("reg") String type
        //		pd = ProgressDialog.show(this, "", "正在上传文件...");
        //		pd.show();
        //		String fileKey = "avatarFile";
        //		UploadUtil uploadUtil = UploadUtil.getInstance();
        //		uploadUtil.setOnUploadProcessListener(MainActivity.this); //设置监听器监听上传状态
        //
        //		Map<String, String> params = new HashMap<String, String>();//上传map对象
        //		params.put("userId", "");
        //		uploadUtil.uploadFile(filepath, fileKey, "上传头像的地址", params);
        //		Toast.makeText(this, "上传成功", Toast.LENGTH_LONG).show();
    }

    @Override
    protected IconPresenter onCreatePresenter() {
        return new IconPresenter(this);
    }



    @Override
    public void showDialog() {
        //		mGifLoadingView=new GifLoadingView();
        //		mGifLoadingView.setImageResource(R.drawable.num6);
        //		mGifLoadingView.show(getActivity().getFragmentManager(),"");
    }

    @Override
    public void onSucceed(IconItem data) {
        if (data != null && 1 == data.getFlag()) {
            Glide.with(this)
                    .load(data.getUrl())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            JctlApplication.getCache().put(KeyConstants.USER_PHOTO, resource);
                            ToastUtils.showLong("上传成功");
                            mImageView.setImageBitmap(mBitmap);
                        }
                    }); //方法中设置asBitmap可以设置回调类型
        }
    }

    @Override
    public void onFailure(String err, Throwable e) {

    }

    @Override
    public void onFail(String err) {
        KLog.d(err);
    }

    @Override
    public void hideDialog() {
    }


    /**
     * 接受广播并处理
     */
    class ExitReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            switch (action)
            {
                case BROADCAST_EXIT:
                    ExitItem data = (ExitItem) intent.getSerializableExtra("state");
                    if (data!=null && data.getFlag()==1)
                    {
                        tvName.setText(null);
                        mineLoginRl.setVisibility(View.VISIBLE);
                        tvName.setVisibility(View.GONE);
                    }
                    break;
                case BROADCAST_LOGIN:
                    userItem = (UserItem) intent.getSerializableExtra("userData");
                    if (userItem!=null) {
                        mineLoginRl.setVisibility(View.GONE);
                        tvName.setVisibility(View.VISIBLE);
                        tvName.setText(userItem.getUser().getName());
                        if (userItem.getUser().getPhoto() != null) {
                            Glide.with(getActivity())
                                    .load(userItem.getUser().getPhoto())
                                    .asBitmap()
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            mImageView.setImageBitmap(resource);
                                        }
                                    }); //方法中设置asBitmap可以设置回调类型
                        }
                        mIMutualListener.actActivity(1,userItem);
                    }
                    break;
                default:

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(getActivity(),mExitReceiver);
    }
}
