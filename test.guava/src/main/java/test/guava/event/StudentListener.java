package test.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author wanggen on 14-7-27.
 * @Desc:
 */
public class StudentListener {

    @Subscribe
    public void lookStudyMessage(StudentMsg msg) {
        System.out.println(this + " You got Message: " + msg.getContent());
    }

}
