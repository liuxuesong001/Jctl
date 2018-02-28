package com.colud.jctl.utils;

public class StringUtils {
    /**
     * 字符是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ' && c != '\t' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否包含某字符
     *
     * @param strs
     * @param s
     * @return
     */
    public static boolean isContain(String[] strs, String s) {
        if (strs == null || strs.length <= 0) {
            return false;
        }
        for (String s1 : strs) {
            if (s1.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断在数组的位置
     *
     * @param strs
     * @param s
     * @return
     */
    public static int findIndex(String[] strs, String s) {
        if (strs == null || strs.length <= 0) {
            return -1;
        }
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 针对TextView显示中文中出现的排版错乱问题，通过调用此方法得以解决
     *
     * @param str
     * @return 返回全部为全角字符的字符串
     */
    public static String toDBC(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }

        }
        return new String(c);
    }

}
