package com.colud.jctl.rx.event;

/**
 * @package com.colud.jctl.rx.event
 * @FileName MyEvent
 * @date or 2017/12/6  11:38
 * @Developer 刘雪松
 * @emial liuxuesong001@gmail.com
 */

public class MyEvent {

    private static MyEvent sDefaultBus;

    public MyEvent() {

    }
    public int eventType;//可能类型有很多种，数据也不一样
    public Object data;//数据对象

    /**
     * @return
     */
    public static MyEvent getMyEvent() {
        if (sDefaultBus == null) {
            synchronized (MyEvent.class) {
                if (sDefaultBus == null) {
                    sDefaultBus = new MyEvent();
                }
            }
        }
        return sDefaultBus;
    }

}
