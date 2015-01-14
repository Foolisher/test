package org.gver.web;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-13.
 */
@Profile(value = {"prod"})
@Controller
public class UserController2 {

    public UserController2(){
        System.out.println("加载: "+this.getClass());
    }

}
