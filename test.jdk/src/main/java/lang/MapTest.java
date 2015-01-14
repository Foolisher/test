package lang;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author wanggen on 14-8-8.
 * @Desc:
 */
public class MapTest {

    @Test
    public void MapValuesTest(){

        Map<String, String> values = Maps.newHashMap();
        values.put("1", "1");
        values.put("2", "2");
        values.put("3", "3");

        Collection<String> valColl = values.values();


        System.out.println(valColl.getClass());
        System.out.println((List)valColl);

    }

}
