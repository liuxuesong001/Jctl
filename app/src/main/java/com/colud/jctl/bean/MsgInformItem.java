package com.colud.jctl.bean;

import java.io.Serializable;

/**
 * Created by Jcty on 2017/3/27.
 */

public class MsgInformItem implements Serializable {
    private static final long serialVersionUID = -8236128402003964170L;
    private String infoIcon;
    private String infoTitle;

    public boolean getInfoState() {
        return infoState;
    }

    public void setInfoState(boolean infoState) {
        this.infoState = infoState;
    }

    private boolean infoState;


    public MsgInformItem(String infoTitle, String infoContent, String infoDate) {
        this.infoTitle = infoTitle;
        this.infoContent = infoContent;
        this.infoDate = infoDate;
    }

    public String getInfoIcon() {
        return infoIcon;
    }

    public void setInfoIcon(String infoIcon) {
        this.infoIcon = infoIcon;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    private String infoContent;
    private String infoDate;
}
