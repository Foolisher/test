package time;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/11/10.
 */
public class TimeUnitTest {

    @Test
    public void test1() {

        System.out.println(TimeUnit.MINUTES.toMillis(1));

        System.out.println(TimeUnit.HOURS.toMillis(1));

    }

}
