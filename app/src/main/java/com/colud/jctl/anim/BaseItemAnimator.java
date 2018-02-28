package com.colud.jctl.anim;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;

/**
 * Item 动画效果
 * Created by Administrator on 2017/9/11.
 */

public class BaseItemAnimator extends SimpleItemAnimator {
    /**
     * Item 移除回调
     *
     * @param holder
     * @return
     */
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        resetAnimator(holder);
        return false;
    }

    /**
     * Item 添加回调
     *
     * @param holder
     * @return
     */
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        resetAnimator(holder);
        return false;
    }

    /**
     * 重置动画
     *
     * @param holder
     */
    private void resetAnimator(RecyclerView.ViewHolder holder) {

    }

    /**
     * 用于控制添加，移动更新时，其它Item的动画执行
     *
     * @param holder
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @return
     */
    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    /**
     * Item 更新回调
     *
     * @param oldHolder
     * @param newHolder
     * @param fromLeft
     * @param fromTop
     * @param toLeft
     * @param toTop
     * @return
     */
    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    /**
     * 真正控制执行动画的地方
     */
    @Override
    public void runPendingAnimations() {

    }

    /**
     * 停止某个Item 动画
     *
     * @param item
     */
    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    /**
     * 停止所有动画
     */
    @Override
    public void endAnimations() {

    }

    /**
     * @return
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
