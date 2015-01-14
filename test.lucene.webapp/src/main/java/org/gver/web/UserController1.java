package org.gver.web;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-13.
 */
@Profile(value = {"dev"})
@Controller
public class UserController1 {


    public UserController1(){
        System.out.println("加载: "+this.getClass());
    }

    @Scheduled(cron = "* * * ? * MON")
    public void startup(){
        System.out.println("-----------------");
    }


}
