package com.colud.jctl.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jcty on 2017/3/7.
 */

public class WeatherData implements Serializable {


    private static final long serialVersionUID = -8207775509509918440L;


    /**
     * message : accurate
     * cod : 200
     * count : 1
     * list : [{"id":1816670,"name":"Beijing","coord":{"lat":39.9075,"lon":116.3972},"main":{"temp":16,"pressure":1023,"humidity":23,"temp_min":16,"temp_max":16},"dt":1490857200,"wind":{"speed":2},"sys":{"country":"CN"},"rain":null,"snow":null,"clouds":{"all":0},"weather":[{"id":800,"main":"Clear","description":"Sky is Clear","icon":"01d"}]}]
     */

    private String message;
    private String cod;
    private int count;
    private List<ListBean> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1816670
         * name : Beijing
         * coord : {"lat":39.9075,"lon":116.3972}
         * main : {"temp":16,"pressure":1023,"humidity":23,"temp_min":16,"temp_max":16}
         * dt : 1490857200
         * wind : {"speed":2}
         * sys : {"country":"CN"}
         * rain : null
         * snow : null
         * clouds : {"all":0}
         * weather : [{"id":800,"main":"Clear","description":"Sky is Clear","icon":"01d"}]
         */

        private int id;
        private String name;
        private CoordBean coord;
        private MainBean main;
        private int dt;
        private WindBean wind;
        private SysBean sys;
        private Object rain;
        private Object snow;
        private CloudsBean clouds;
        private List<WeatherBean> weather;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CoordBean getCoord() {
            return coord;
        }

        public void setCoord(CoordBean coord) {
            this.coord = coord;
        }

        public MainBean getMain() {
            return main;
        }

        public void setMain(MainBean main) {
            this.main = main;
        }

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public SysBean getSys() {
            return sys;
        }

        public void setSys(SysBean sys) {
            this.sys = sys;
        }

        public Object getRain() {
            return rain;
        }

        public void setRain(Object rain) {
            this.rain = rain;
        }

        public Object getSnow() {
            return snow;
        }

        public void setSnow(Object snow) {
            this.snow = snow;
        }

        public CloudsBean getClouds() {
            return clouds;
        }

        public void setClouds(CloudsBean clouds) {
            this.clouds = clouds;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }

        public static class CoordBean {
            /**
             * lat : 39.9075
             * lon : 116.3972
             */

            private double lat;
            private double lon;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }
        }

        public static class MainBean {
            /**
             * temp : 16
             * pressure : 1023
             * humidity : 23
             * temp_min : 16
             * temp_max : 16
             */

            private int temp;
            private int pressure;
            private int humidity;
            private int temp_min;
            private int temp_max;

            public int getTemp() {
                return temp;
            }

            public void setTemp(int temp) {
                this.temp = temp;
            }

            public int getPressure() {
                return pressure;
            }

            public void setPressure(int pressure) {
                this.pressure = pressure;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public int getTemp_min() {
                return temp_min;
            }

            public void setTemp_min(int temp_min) {
                this.temp_min = temp_min;
            }

            public int getTemp_max() {
                return temp_max;
            }

            public void setTemp_max(int temp_max) {
                this.temp_max = temp_max;
            }
        }

        public static class WindBean {
            /**
             * speed : 2
             */

            private int speed;

            public int getSpeed() {
                return speed;
            }

            public void setSpeed(int speed) {
                this.speed = speed;
            }
        }

        public static class SysBean {
            /**
             * country : CN
             */

            private String country;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }
        }

        public static class CloudsBean {
            /**
             * all : 0
             */

            private int all;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }
        }

        public static class WeatherBean {
            /**
             * id : 800
             * main : Clear
             * description : Sky is Clear
             * icon : 01d
             */

            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
