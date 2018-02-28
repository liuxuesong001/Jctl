package com.colud.jctl.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/24
 * Notes:
 */
public class GsonUtils {

    private static Gson gson;

    private GsonUtils() {

    }

    public static Gson getSingleInstance() {
        if (gson == null) {
            synchronized (GsonUtils.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }

    public static Gson newInstance() {
        return new Gson();
    }

    /**
     * JSON转成list集合
     *
     * @param gsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> GsonToList(String gsonStr, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonStr, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }
}
