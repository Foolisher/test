package test.guava.collections;

import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;
import lombok.Data;
import org.junit.Test;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14-10-16.
 */
public class RangeMapTest {


    @Test
    public void test() {

        TreeRangeMap<Integer, Student> treeRangeMap = TreeRangeMap.create();

        treeRangeMap.put(Range.closedOpen(0, 59), new Student());

    }


}

@Data
class Student{

    private String name;
    private Integer score;

}
