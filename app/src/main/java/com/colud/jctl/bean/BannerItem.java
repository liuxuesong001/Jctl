package com.colud.jctl.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jcty on 2017/4/22.
 */

public class BannerItem extends BaseBean {


    /**
     * flag : 1
     * info : [{"path":"http://61.149.204.178:8085/20170421/c59536a0fde74c8391ed44fa4f9837a2.jpg","url":""},{"path":"http://61.149.204.178:8085/20170421/65b58399bf9442aeb00ec96f11bf5994.jpg","url":""},{"path":"http://61.149.204.178:8085/20170421/08077757656e4da6a5e0a7a6608c8730.jpg","url":""}]
     */

    private int flag;
    private List<InfoBean> info;
    private List<String> sList;

    public List<String> getsList() {
        return sList;
    }

    public void setsList(List<String> sList) {
        this.sList = sList;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable {
        /**
         * path : http://61.149.204.178:8085/20170421/c59536a0fde74c8391ed44fa4f9837a2.jpg
         * url :
         */

        private String path;
        private String url;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
