package string;

import org.junit.Test;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-19.
 */
public class StringTokenizer {

    @Test
    public void test(){
        java.util.StringTokenizer stringTokenizer = new java.util.StringTokenizer("10   2343 3543 898");
        System.out.println(stringTokenizer.nextToken());
        System.out.println(stringTokenizer.nextToken());
        System.out.println(stringTokenizer.nextToken());
        System.out.println(stringTokenizer.nextToken());
    }

}
