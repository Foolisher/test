package org.gver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * &#x529f;&#x80fd;&#x63cf;&#x8ff0;:
 * <p/>
 *
 * @author wanggen on 14-9-2.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Value("#{app.user}")
    private String user;

    @Override
    public String order(String order) {
        System.out.println(user+" > Handling order: "+order);
        return order+" is created!";
    }
}
