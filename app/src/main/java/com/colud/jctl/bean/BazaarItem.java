package com.colud.jctl.bean;

import java.util.List;

/**
 *
 * Created by Jcty on 2017/4/17.
 */

public class BazaarItem extends BaseBean {

    /**
     * flag : 1
     * mktDyns : [{"id":0,"productName":"标准粉","price":"3","marketName":"石家庄桥西","releaseDate":"2017-03-27"}]
     */

    private int flag;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<MktDynsBean> mktDyns;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<MktDynsBean> getMktDyns() {
        return mktDyns;
    }

    public void setMktDyns(List<MktDynsBean> mktDyns) {
        this.mktDyns = mktDyns;
    }

    public static class MktDynsBean extends BaseBean {
        /**
         * id : 0
         * productName : 标准粉
         * price : 3
         * marketName : 石家庄桥西
         * releaseDate : 2017-03-27
         */

        private int id;
        private String productName;
        private String price;
        private String marketName;
        private String releaseDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarketName() {
            return marketName;
        }

        public void setMarketName(String marketName) {
            this.marketName = marketName;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }
    }
}
