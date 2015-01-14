package test.guava.cache;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wanggen on 14-7-11.
 * @Desc:
 */
public class CacheBuilderTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        LoadingCache<Object, Object> loadingCache = CacheBuilder.newBuilder()
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build(new CacheLoader<Object, Object>() {
                    @Override
                    public Object load(Object key) throws Exception {
                        return "msg:" + key;
                    }
                });


        loadingCache.put("1", "cache1");

        TimeUnit.SECONDS.sleep(10);

        System.out.println(loadingCache.get("1"));


    }

}


class UserService implements RemovalListener<String, String>{

    @Override
    public void onRemoval(RemovalNotification<String, String> notification) {
        System.out.println(notification.getKey()+":"+notification.getValue());
    }

    public void updateUser(){

    }


}