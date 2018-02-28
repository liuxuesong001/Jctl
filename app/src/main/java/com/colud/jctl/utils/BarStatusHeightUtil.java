package com.colud.jctl.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Jcty on 2017/3/16.
 */

public class BarStatusHeightUtil {
    // 通过反射机制获取手机状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //        ((ViewGroup)activity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);//设置跟布局fitsystemwindow=true
    }

    /**
     * 获取状态栏高度,在页面还没有显示出来之前
     *
     * @param a
     * @return
     */
    public static int getStateBarHeight(Activity a) {
        int result = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = a.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置状态栏
     */
    protected void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            getStatusBarHeight(activity);
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top == 0 ? 60 : rect.top;
    }
    //	/**
    //	 * 设置透明状态栏与导航栏
    //	 * @param navi true不设置导航栏|false设置导航栏
    //	 */
    //	public void setStatusBar(boolean navi) {
    //		//api>21,全透明状态栏和导航栏;api>19,半透明状态栏和导航栏
    //		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //			Window window = getWindow();
    //			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //			window.setStatusBarColor(Color.TRANSPARENT);
    //			if (navi) {
    //				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//状态栏不会被隐藏但activity布局会扩展到状态栏所在位置
    //						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
    //						| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    //				window.setNavigationBarColor(Color.TRANSPARENT);
    //			} else {
    //				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    //						| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    //			}
    //		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //			if (navi) {
    //				//半透明导航栏
    //				getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    //			}
    //			//半透明状态栏
    //			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //		}
    //	}

    /**
     * 进入沉浸模式
     *
     * @param view view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void hideSystemUI(View view) {
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY//会自动隐藏
        );
    }

    /**
     * 退出沉浸模式
     *
     * @param view view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void showSystemUI(View view) {
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    //	/**
    //	 * 获取状态栏高度
    //	 *
    //	 * @param context 上下文
    //	 * @return 状态栏高度
    //	 */
    //	public int getStatusBarHeight(Context context) {
    //		int statusBarHeight = 0;
    //		try {
    //			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
    //			Object obj = clazz.newInstance();
    //			Field field = clazz.getField("status_bar_height");
    //			int temp = Integer.parseInt(field.get(obj).toString());
    //			statusBarHeight = context.getResources().getDimensionPixelSize(temp);
    //		} catch (Exception e) {
    //			e.printStackTrace();
    //		}
    //		return statusBarHeight;
    //	}
    //获取状态栏高度
    public static int getStatusBarHeightAll(Context context) {
        int statusBarHeight = dip2px(context, 25);
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    //根据手机的分辨率从 dp 的单位 转成为 px(像素)
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public static void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 判断是否有虚拟按键
     *
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }
}
