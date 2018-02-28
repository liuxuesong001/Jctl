package com.colud.jctl.bean;

/**
 *
 * Created by Jcty on 2017/4/14.
 */

public class UserItem extends BaseBean {

    /**
     * flag : 1
     * user : {"id":"1","name":"系统管理员","password":"123456","loginName":"plat"}
     */

    private int flag;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private UserBean user;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean extends BaseBean
    {

        /**
         * id : 1
         * name : 系统管理员
         * password : 123456
         * loginName : plat
         */

        //		private Strng id;
        private String name;
        private String password;
        private String loginName;
        private String channelId;
        private String singleId;

        public String getSingleId() {
            return singleId;
        }

        public void setSingleId(String singleId) {
            this.singleId = singleId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        private String photo;

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        //		public String getId() {
        //			return id;
        //		}
        //
        //		public void setId(String id) {
        //			this.id = id;
        //		}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }
    }
}
