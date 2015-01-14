package test.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Random;

/**
 * @author wanggen on 14-7-25.
 * @Desc:
 */
public class OrderingTest {

    @Test
    public void IntegerOrderTest(){

        Random random = new Random();
        int max=10000;

        Ordering<Integer> integerOrdering = new Ordering<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return left-right;
            }
        };

        ImmutableList<Integer> ordered = integerOrdering.immutableSortedCopy(
                Lists.newArrayList(random.nextInt(max), random.nextInt(max),
                        random.nextInt(max), random.nextInt(max))
        );

        System.out.println(ordered);

    }

}
