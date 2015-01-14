package org.gver;

import org.springframework.stereotype.Component;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-2.
 */
@Component
public class AccountManager {


    public AccountManager(){
        System.err.println("实例化:"+this);
    }

    public void createAccount(String username){
        System.err.println("["+username+"] is created");
    }

}
