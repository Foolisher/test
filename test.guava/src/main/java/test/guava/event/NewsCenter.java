package test.guava.event;

import com.google.common.eventbus.EventBus;

/**
 * @author wanggen on 14-7-27.
 * @Desc:
 */
public class NewsCenter {

    private NewsCenter(){}

    public static NewsCenter create(){
        return new NewsCenter();
    }

    private static EventBus newsEventBus = new EventBus("news");

    public static void register(Object listner){
        newsEventBus.register(listner);
    }

    public static void unregister(Object listner){
        newsEventBus.register(listner);
    }

    public static void post(Message msg){
        newsEventBus.post(msg);
    }

}
