package collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author wanggen on 14-7-10.
 * @Desc:
 */
public class ListTest {


    @Test
    public void test1() {

        List list = null;

        Collections.sort(list = Lists.newArrayList(34, 65634, 345, 75654, 343, 4564542, 543, 456543), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        System.out.println(list);

    }

}
