package org.gver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * OrderServiceImpl Tester.
 *
 * @author wanggen
 * @version 1.0
 * @since <pre>九月 2, 2014</pre>
 */
public class OrderServiceImplTest extends BaseServiceTest{

    @Autowired
    OrderService orderService;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: order(String order)
     */
    @Test
    public void testOrder() throws Exception {
        orderService.order("I Am Order");
    }


}
