package org.gver.http;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-1.
 */
public class Decode {

    @Test
    public void URLDecode() throws UnsupportedEncodingException {

        String url = "'dubbo%253A%252F%252F10.0.0.24%253A20880%252Fdubbo.service.DemoService%253Fanyhost%253Dtrue%2526application%253Dprovider%2526dubbo%253D2.5.3%2526interface%253Ddubbo.service.DemoService%2526methods%253DsayHello%2526pid%253D5296%2526side%253Dprovider%2526timestamp%253D1409548452854";

        System.out.println(URLDecoder.decode(url, "GB2312"));

    }

}
