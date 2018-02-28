package com.colud.jctl.bean;

/**
 * Created by Jcty on 2017/4/24.
 */

public class IconItem extends BaseBean {

    /**
     * flag : 1
     * url : http://61.149.204.178:8085/20170424/b393341a634d4e82b2838e2214203a50.png
     */

    private int flag;
    private String base64;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    private String url;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
