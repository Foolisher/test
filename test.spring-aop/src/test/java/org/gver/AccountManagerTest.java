package org.gver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AccountManager Tester.
 *
 * @author wanggen
 * @version 1.0
 * @since <pre>九月 2, 2014</pre>
 */
public class AccountManagerTest extends BaseServiceTest{

    @Autowired
    AccountManager accountManager;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: createAccount(String username)
     */
    @Test
    public void testCreateAccount() throws Exception {
        accountManager.createAccount("gen");
    }


}
