package com.colud.jctl.utils;

/**
 * Created by Jcty on 2017/5/3.
 */

public class ArraysUtils {


    /**
     * 取出float数组最大值下标
     *
     * @param arrays
     * @return
     */
    public static int getMax(float[] arrays) {

        int result = 0;

        if (arrays == null || arrays.length == 0) {
            return result;
        }

        Integer step = 0;//下标

        float max = 0.00f;

        for (Float num : arrays) {
            if (num > max) {
                max = num;
                result = step;
            }
            step++;
        }
        return result;

    }

    /**
     * 取出float数组最大值
     *
     * @param arrays
     * @return
     */
    public static int getMaxValue(float[] arrays) {
        Float result = 0.00f;

        for (Float num : arrays) {
            if (num > result) {
                result = num;
            }
        }
        return result.intValue();

    }
}
