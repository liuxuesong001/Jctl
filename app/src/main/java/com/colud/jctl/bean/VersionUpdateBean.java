package com.colud.jctl.bean;

/**
 * Created by Jcty on 2017/6/23.
 */

public class VersionUpdateBean extends BaseBean {

    /**
     * flag : 1
     * version : {"id":"7","isNewRecord":false,"createDate":"2017-06-09 17:59:27","updateDate":"2017-06-12 17:42:13","startNum":0,"inVersion":"1.0.1.170609","outVersion":"1.0.1.170609","remark":"版本1","status":"1","url":"http://www.e-unite.cn:8085/20170612/f3c16b02a26f4cccaa6f160111a53605.apk","autoUpdate":"1"}
     */

    private int flag;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private VersionBean version;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public VersionBean getVersion() {
        return version;
    }

    public void setVersion(VersionBean version) {
        this.version = version;
    }

    public static class VersionBean extends BaseBean {
        /**
         * id : 7
         * isNewRecord : false
         * createDate : 2017-06-09 17:59:27
         * updateDate : 2017-06-12 17:42:13
         * startNum : 0
         * inVersion : 1.0.1.170609
         * outVersion : 1.0.1.170609
         * remark : 版本1
         * status : 1
         * url : http://www.e-unite.cn:8085/20170612/f3c16b02a26f4cccaa6f160111a53605.apk
         * autoUpdate : 1
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private int startNum;
        private String inVersion;
        private String outVersion;
        private String remark;
        private String status;
        private String url;
        private String autoUpdate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public int getStartNum() {
            return startNum;
        }

        public void setStartNum(int startNum) {
            this.startNum = startNum;
        }

        public String getInVersion() {
            return inVersion;
        }

        public void setInVersion(String inVersion) {
            this.inVersion = inVersion;
        }

        public String getOutVersion() {
            return outVersion;
        }

        public void setOutVersion(String outVersion) {
            this.outVersion = outVersion;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAutoUpdate() {
            return autoUpdate;
        }

        public void setAutoUpdate(String autoUpdate) {
            this.autoUpdate = autoUpdate;
        }
    }
}
