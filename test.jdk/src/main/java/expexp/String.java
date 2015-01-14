package expexp;

import org.junit.Test;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-13.
 */
public class String {

    @Test
    public void replace(){

        System.out.println("包装模块".replaceAll("(模块$)|(类$)", ""));
        System.out.println("发送包装类".replaceAll("(^发送)|(类$)|(模块$)", ""));

    }

}
