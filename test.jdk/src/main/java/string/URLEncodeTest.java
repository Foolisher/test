package string;

import com.github.kevinsawicki.http.HttpRequest;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/11/21.
 */
public class URLEncodeTest {

    @Test
    public void test() throws UnsupportedEncodingException {

        System.err.println(URLEncoder.encode("paidAtStart=2014-10-01%2000:01:34&paidAtEnd=2014-11-25%2022:00:00"));

        System.out.println(URLEncoder.encode("paidAtStart=2014-10-01 00:01:34&paidAtEnd=2014-11-25 22:00:00", "UTF-8"));

        System.out.println(com.sun.deploy.net.URLEncoder.encode("paidAtStart=2014-10-01 00:01:34&paidAtEnd=2014-11-25 22:00:00", "UTF-8"));


        System.out.println(URLDecoder.decode("paidAtStart%3D2014-10-01+00%3A01%3A34%26paidAtEnd%3D2014-11-25+22%3A00%3A00", "UTF-8"));

        String qstr = URLEncoder.encode("?wd=中文", "UTF-8");
        String body = HttpRequest.get("http://www.baidu.com?wd=中文").body();
        System.out.println(body);

    }

}
