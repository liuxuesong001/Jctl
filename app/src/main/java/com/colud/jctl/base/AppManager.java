package com.colud.jctl.base;

import android.app.Activity;

import java.util.Stack;


/**
 * Acticity 管理类
 * Created by xandone on 2016/12/21.
 */
public class AppManager {

    private static Stack<Activity> activityStack;

    private volatile static AppManager instance;

    private AppManager() {
    }

    public static AppManager newInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager();
                activityStack = new Stack<>();
            }
        }
        return instance;
    }

    /**
     * 添加一个Activity 管理
     *
     * @param activity
     */
    public void addActivivty(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除一个Activity
     *
     * @param activity
     */
    public void removectivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activityStack == null || activityStack.isEmpty()) {
            return;
        }
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            return;
        }
        Activity activity = activityStack.get(activityStack.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (activityStack == null || activityStack.isEmpty()) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 判断当前打开的Activity
     *
     * @param clazz
     * @return
     */
    public boolean isOpenActivity(Class<?> clazz) {
        if (activityStack != null) {
            if (clazz == activityStack.peek().getClass()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
}
