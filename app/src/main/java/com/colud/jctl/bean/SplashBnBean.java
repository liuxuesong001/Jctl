package com.colud.jctl.bean;

import java.util.List;

/**
 *
 * Created by Jcty on 2017/5/17.
 */

public class SplashBnBean extends BaseBean {


    /**
     * news : [{"dataTime":"2017-05-17","id":1,"title":"威海水产品市场：大棚海参继续下滑 海捕鱼类价格高企","content":"　　眼下，黄渤菜供应量充裕，整体菜价有所回落。其中瓜果类蔬菜品种价格跌多涨少；而叶类蔬菜随着品种与上市量的增多，价格也随之有大幅下滑。预计本周蔬菜整体价格还将会有所波动下跌。","url":"http://pfsc.agri.cn/scdt/201705/t20170516_273664.htm"}]
     * flag : 1
     * banner : [{"path":"http://www.e-unite.cn:8085/20170505/d447661f62b0422aa04944e2b43e7810.jpg","url":"www.baidu.com"},{"path":"http://www.e-unite.cn:8085/20170505/3d740d4a67d74f5c89194c5f18c7bf8a.jpg","url":"www.baidu.com"},{"path":"http://www.e-unite.cn:8085/20170505/d3e91d9c90724b05b0dff45941f67174.jpg","url":"www.baidu.com"}]
     */

    private int flag;
    private List<NewsBean> news;
    private List<BannerBean> banner;

    private List<String>banners;
    private List<String>urls;

    public List<String> getBanners() {
        return banners;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class NewsBean extends BaseBean {
        /**
         * dataTime : 2017-05-17
         * id : 1
         * title : 威海水产品市场：大棚海参继续下滑 海捕鱼类价格高企
         * content : 　　眼下，黄渤海伏期休渔已经过去二十天了。据了解，目前威海水产市场上所销售的海产品并未出现断档的现象，市场上各种鱼类、贝类货源供应还是较为的充足。 　　据调查的信息显示，目前，冷冻海鲜和人工养殖海鲜成为水产品市场上销售的主角，再加上外地调入部分鲜活海产品，所以基本能够保证海产品市场的供应。据了解，受休渔期影响，目前市场上部分新鲜的海捕鱼的价格有所上涨，而冷冻海鲜和各类人工养殖海产品的价格则相对平稳；此外，作为海鱼的替代产品，近期市场上淡水鱼价格也一直高位坚挺。目前市场新鲜大鲅鱼每公斤价格一般在36-44元，新鲜海捕大牙鲆鱼每公斤价格在80-90元，冰冻小黄花鱼价格一般在26-30元，养殖大黄花鱼每公斤价格在38-44元，养殖加吉鱼每公斤价格为40元左右，而养殖鲈鱼价格在23-24元。 　　从贝类区域了解到，因为市场销售的大多数贝类都是人工养殖的，供应量变化不大，相比海鱼价格的上涨幅度，价格相对稳定，基本与休渔前的价格持平；另外，从淡水鱼区域了解到，休渔期间，淡水鱼的销量也随之上升，导致部分淡水鱼价格水涨船高，近来市场上活鲫鱼、草鱼的价格持续坚挺，其中：市场活草鱼每公斤价格已经上涨至18-19元。 　　而随着天气的炎热，本地池养海参大量上市，大棚养殖活海参价格震荡回落，目前市场大棚养殖活海参每公斤价格下降至84元左右，比前期下降4.5%；同比上涨5%。
         * url : http://pfsc.agri.cn/scdt/201705/t20170517_273706.htm
         */

        private String dataTime;
        private int id;
        private String title;
        private String content;
        private String url;

        public String getDataTime() {
            return dataTime;
        }

        public void setDataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class BannerBean extends BaseBean {
        /**
         * path : http://www.e-unite.cn:8085/20170505/d447661f62b0422aa04944e2b43e7810.jpg
         * url : www.baidu.com
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
