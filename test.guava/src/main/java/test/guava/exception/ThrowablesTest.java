package test.guava.exception;

import com.google.common.base.Throwables;
import org.junit.Test;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/11/3.
 */
public class ThrowablesTest {


    @Test
    public void test1() {

        try{
            throw new RuntimeException("一个异常出来了");
        }catch(Exception e){
            System.out.println(Throwables.getStackTraceAsString(e));
        }


    }

}
