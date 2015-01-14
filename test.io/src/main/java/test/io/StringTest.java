package test.io;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by wanggen on 14-7-2.
 */
public class StringTest {

    @Test
    public void testReplace(){
        System.out.println("www".replaceAll("www\\.?", "").trim().equals(""));
    }


    @Test
    public void test2() {

        System.out.println("1".substring(-2));
        System.out.println("12".substring(-2));
        System.out.println("123".substring(-2));
        System.out.println("1234".substring(-2));

    }


    public static void main(String[] args) throws InterruptedException {


        while(true){

            int a=100;

            System.out.println(a++);

            TimeUnit.SECONDS.sleep(1);

        }

    }

}
