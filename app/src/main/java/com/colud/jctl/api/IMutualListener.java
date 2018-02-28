package com.colud.jctl.api;


import com.colud.jctl.bean.WeatherHome;

/**
 * @package com.colud.jctl.api
 * @FileName IMutualListener
 * @date or 2017/12/8  14:20
 * @Developer 刘雪松
 * @emial liuxuesong001@gmail.com
 */

public interface IMutualListener {


    WeatherHome getTemp();

    /**
     * FragmentActivity用来改变Fragment中的展示内容
     */
    void actActivity(int type,Object str);

    /**
     * Fragment用来改变FragmentActivity中的展示内容
     */
    void fragmentData(int type, Object str);

}
