package test.http;

import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * SignUtil Tester.
 *
 * @author wanggen
 * @version 1.0
 * @since 十二月 23, 2014
 */
public class SignUtilTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: sign(Object data, String salt, String... excludes)
     */
    @Test
    public void testSign() throws Exception {


        Param param = new Param();
        param.setName("王根");
        param.setProvince("zj");
        param.setAddress("杭州");
//
//        String sign = SignUtil.sign(param, "Alksdjflkwje34543l5k3jl45");

        Map mapP = Maps.newTreeMap();
        mapP.put("sdfsdf", "sdfgds");
        mapP.put("fsdfdfg", "fdss");
        mapP.put("erhfdgdfsd", "wdfgd");
        mapP.put("r", "");
        mapP.put("aaa", null);
        mapP.put("lll", "");
        mapP.put("lkjsdlkf", null);

        String sign1 = SignUtil.sign(mapP, "lksdjflskdj");

        long start = System.currentTimeMillis();
        for(int i=1;i<=1000000;i++)
            SignUtil.sign(mapP, "lksdjflskdj");
        System.out.println(System.currentTimeMillis()-start);

        start = System.currentTimeMillis();
        for(int i=1;i<=1000000;i++)
            SignUtil.sign(param, "lksdjflskdj");
        System.out.println(System.currentTimeMillis()-start);

    }


    @Data
   public static class Param{
        private String name;
        private String age;
        private String address;
        private String province;
    }

    /**
     * Method: verify(Object data, String sign, String salt, String... excludeKeys)
     */
    @Test
    public void testVerify() throws Exception {
        //TODO: Test goes here...

    }


    /**
     * Method: toTreeMap(Object data, String... excludes)
     */
    @Test
    public void testToTreeMap() throws Exception {
        //TODO: Test goes here...

    }

} 
