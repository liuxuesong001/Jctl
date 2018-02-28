package com.colud.jctl.bean;

/**
 *  天气 Bean
 * Created by Jcty on 2017/4/13.
 */

public class WeatherHome extends BaseBean {

    /**
     * status : 1
     * info : {"text":"阴","code":"9","temperature":"19"}
     */


    private int status;
    private InfoBean info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean extends BaseBean {
        /**
         * text : 阴
         * code : 9
         * temperature : 19
         */

        private String text;
        private String code;
        private String temperature;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }
    }
}
