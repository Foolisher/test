package test.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-21.
 */
public class CacheTest {

    Cache<Long, Long> cache = CacheBuilder.newBuilder().build();

    @Test
    public void test() {

        LoadingCache loadingCache = CacheBuilder.newBuilder().build(new CacheLoader() {
            @Override
            public Object load(Object key) throws Exception {
                if ("A".equals(key)) return "A:1";
                else if ("B".equals(key)) return "B:2";
                return null;
            }
        });

        try {
            Object a = loadingCache.get("A");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        try {
            Object b = loadingCache.get("B");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Object b = loadingCache.get("B");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Object c = null;
        try {
            c = loadingCache.getUnchecked("C");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            c = loadingCache.getUnchecked("C");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            c = loadingCache.getUnchecked("C");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
