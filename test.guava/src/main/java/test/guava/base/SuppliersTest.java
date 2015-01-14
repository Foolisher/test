package test.guava.base;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/10/20.
 */
public class SuppliersTest {


    @Test
    public void test() {

        Supplier<Object> objectSupplier = Suppliers.memoizeWithExpiration(new Supplier<Object>() {
            @Override
            public Object get() {
                return 1;
            }
        }, 1, TimeUnit.MINUTES);

        Supplier<Object> memoize = Suppliers.memoize(new Supplier<Object>() {
            @Override
            public Object get() {
                return 1l;
            }
        });

        Object o = memoize.get();

        System.out.println(o);

    }

}
