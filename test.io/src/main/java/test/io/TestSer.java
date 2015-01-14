package test.io;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/10/30.
 */
public class TestSer{

    public static void main(String[] args) throws IOException {

        List<? extends Object> from = Splitter.on(",").omitEmptyStrings().trimResults().splitToList("1,2,3,4");
        List<Object> transform = Lists.transform(from, new Function<Object, Object>() {
            @Override
            public Object apply(Object input) {
                return input;
            }
        });

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
        objectOutputStream.writeObject(transform);



    }


    @Test
    public void test() throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());

        Map map = Maps.newHashMap();
        map.put("1", 1);
        map.put("2", 2);

        objectOutputStream.writeObject(map);

        Map map1 = Maps.filterValues(map, new Predicate() {
            @Override
            public boolean apply(Object input) {
                return input != null;
            }
        });

        objectOutputStream.writeObject(map1);

    }

    @Test
    public void test2() throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());

        Map map = ImmutableMap.of("1", 1, "2", 2);

        ImmutableMap map1 = ImmutableMap.copyOf(map);

        objectOutputStream.writeObject(map);

        objectOutputStream.writeObject(map1);

    }

}
