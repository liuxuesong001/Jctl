package com.colud.jctl.utils.location;

import com.baidu.location.BDLocation;

/**
 * Created by Jcty on 2017/3/30.
 */

public class LocationUtil {

    public static boolean isLocationResultEffective(BDLocation bdLocation) {
        return bdLocation != null
                && (bdLocation.getLocType() == BDLocation.TypeGpsLocation
                || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation);
    }
}
