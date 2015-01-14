package test.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author wanggen on 14-7-27.
 * @Desc:
 */
public class WorkerListener {


    @Subscribe
    public void lookMsg(WorkerMsg msg) {
        System.err.println(this + "  You got message:" + msg.getContent());
    }

}
