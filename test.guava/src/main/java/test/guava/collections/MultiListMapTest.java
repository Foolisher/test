package test.guava.collections;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/11/24.
 */
public class MultiListMapTest {

    @Test
    public void test() {

        Random random = new Random(1000);
        List<Person> persons = Lists.newArrayList();

        for(int i=0;i<=50;i++){
            persons.add(new Person((long) random.nextInt(50), random.nextInt()+""));
        }

        long start = System.currentTimeMillis();
        ImmutableListMultimap<Long, Person> personMap = Multimaps.index(persons, new Function<Person, Long>() {
            public Long apply(Person input) {
                return input.getId();
            }
        });
        System.out.println("耗时(毫秒):"+(System.currentTimeMillis()-start));


    }


}

@Data
@AllArgsConstructor
class Person{

    private Long id;
    private String name;

}
