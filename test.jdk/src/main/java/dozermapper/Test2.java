/*
 * Copyright (c) 2015 杭州端点网络科技有限公司
 */

package dozermapper;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2014-12-09.
 */
public class Test2 {

    DozerBeanMapper mapper = new DozerBeanMapper();

    @Test
    public void test() {


        DozerBeanMapper mapper = new DozerBeanMapper();

        User user = new User();
        user.setCity("1111");
        user.setCountry("lskjdfs;ldkfj");
        user.setLanguage("o8sujdlfkjs");
        user.setProvince("lskjdflskjdf");
        user.setUnionid("lkdsjflskdjfl");
//        user.setPrivilege(new ArrayList<String>(3));

        Map map;
        map = mapper.map(user, TreeMap.class);

        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            map = mapper.map(user, Map.class);
        }
        System.out.println(System.currentTimeMillis()-start);

        //31979


    }

    @Test
    public void test2() {

        User user = new User();
        user.setCity("1111");
        user.setCountry("lskjdfs;ldkfj");
        user.setLanguage("o8sujdlfkjs");
        user.setProvince("lskjdflskjdf");
        user.setUnionid("lkdsjflskdjfl");

        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
//            SignUtil.sign(user, "lskdfjsldkj");
            mapper.map(user, Map.class);
        }
        System.out.println(System.currentTimeMillis()-start);

    }


    @Test
    public void test3() {

        User user = new User();
        user.setCity("1111");
        user.setCountry("lskjdfs;ldkfj");
        user.setLanguage("o8sujdlfkjs");
        user.setProvince("lskjdflskjdf");
        user.setUnionid("lkdsjflskdjfl");

        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            GroupSignUtil.generateSign(user, "lskdfjsldkj");
//            SignUtil.sign(user, "lskdfjsldkj");
        }
        System.out.println(System.currentTimeMillis()-start);



    }


    @Test
    public void test4() {

        User user = new User();
        user.setCity("1111");
        user.setCountry("lskjdfs;ldkfj");
        user.setLanguage("o8sujdlfkjs");
        user.setProvince("lskjdflskjdf");
        user.setUnionid("lkdsjflskdjfl");

        String sign = SignUtil.sign(user, "1");

        System.out.println(SignUtil.verify(user, sign, "1"));

    }


    @Test
    public void test5() {

        System.out.println("http://www.daqihui.com/page?_url=http://www.baidu.com".replaceAll("(^(http|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+", ""));
        System.out.println("http://www.daqihui.com/page?_url=http://www.baidu.com".replaceAll("^((http|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+", ""));
        System.out.println("https://www.daqihui.com/page".replaceAll("(^(http|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+", ""));
        System.out.println("http://www.daqihui.com http://www.a.b".replaceAll("(^(http|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+", ""));
        System.out.println("http://www.daqihui".replaceAll("((http|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+", ""));
        System.out.println("www.daqihui.com/page".replaceAll("((http|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+", ""));

    }


    @Test
    public void testDateTime() {

        DateTime now = DateTime.now();
        DateTime payTimeout = now.plusDays(1);
        long hours = 0l;
        if(payTimeout.getMillis()>now.getMillis()){
            hours = payTimeout.minus(now.getMillis()).getMillis() / TimeUnit.HOURS.toMillis(1);
        }

        assertTrue(hours == 24);


        payTimeout = now.plusHours(50);
        if(payTimeout.getMillis()>now.getMillis()){
            hours = payTimeout.minus(now.getMillis()).getMillis() / TimeUnit.HOURS.toMillis(1);
        }
        
        assertTrue(hours == 50);


        payTimeout = now.plusHours(1020);
        if(payTimeout.getMillis()>now.getMillis()){
            hours = payTimeout.minus(now.getMillis()).getMillis() / TimeUnit.HOURS.toMillis(1);
        }

        assertTrue(hours == 1020);
        
    }


    @Test
    public void test6() {

        DateTime now = DateTime.now();
        DateTime payTimeout = new DateTime(now.plusHours(3).minusMinutes(1).plusMinutes(58));
        long hours = 0l;
        if(payTimeout.getMillis()>now.getMillis()){
            hours = payTimeout.minus(now.getMillis()).getMillis() / TimeUnit.HOURS.toMillis(1);
        }
        System.out.println(hours);


    }

    @Test
    public void test7() {

        DateTime now = DateTime.now();
        DateTime payTimeout = new DateTime(now.plusHours(3).minusMinutes(10));
        long hours = 0l;
        if(payTimeout.getMillis()>now.getMillis()){
            hours = payTimeout.minus(now.getMillis()).getMillis() / TimeUnit.HOURS.toMillis(1);
        }
        System.out.println(hours);


    }

    @Test
    public void test8() {

        DateTime now = DateTime.now();
        DateTime payTimeout = new DateTime(now.plusHours(3).minusMinutes(50));
        long hours = 0l;
        if(payTimeout.getMillis()>now.getMillis()){
            hours = payTimeout.minus(now.getMillis()).getMillis() / TimeUnit.HOURS.toMillis(1);
        }
        System.out.println(hours);


    }


    @Test
    public void test9() throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("测试商品测试商品洗衣机XQB50-728E", "UTF-8"));
    }


}
