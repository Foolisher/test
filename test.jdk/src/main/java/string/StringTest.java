package string;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertTrue;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/11/4.
 */
public class StringTest {

    @Test
    public void test1() {

        String src = "1，2,3,4";

        String[] split = src.split("[,，]");

        assertTrue(split[0].trim().equals("1"));
        assertTrue(split[1].trim().equals("2"));
        assertTrue(split[2].trim().equals("3"));
        assertTrue(split[3].trim().equals("4"));

    }


    @Test
    public void test2() {

        assertTrue("1 2 34".replace(" ", "").equals("1234"));

    }


    @Test
    public void test3() {

        String str = "{\"name\":\"\\u65b0\\u5efa\\u6587\\u672c\\u6587\\u6863.txt\",\"url\":\"http://wj.ihaier.com:8080/docs/2014/10/28/270_48a3823e1ee72a1a06a62cbdcb4a5cd0.txt\"}";

        System.out.println(str);

        String str2 = new String(str.getBytes(StandardCharsets.UTF_8), Charset.forName("GBK"));

        System.out.println(str2);



    }


    @Test
    public void test4() {

        int i=512;
        while(i>=0){
            System.out.println(i); i<<=1;
        }

    }

    @Test
    public void test() {

        System.out.println("123".substring(0, 1));
        System.out.println("123".substring(0, 2));
        System.out.println("123".substring(0, 3));


    }


}
