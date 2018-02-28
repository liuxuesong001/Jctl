package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/13.
 */
public class PickerViewData implements Serializable {
    private static final long serialVersionUID = 1082502030448350392L;
    private String content;

    public PickerViewData(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //    @Override
    //    public String getPickerViewText() {
    //        return content;
    //    }
}
