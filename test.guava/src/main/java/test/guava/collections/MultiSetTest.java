package test.guava.collections;

import com.google.common.collect.*;
import com.google.common.util.concurrent.AtomicLongMap;
import com.google.gson.Gson;

/**
* @author wanggen on 14-7-16.
* @Desc:
*/
public class MultiSetTest {

    public static void main(String[] args){

        Multiset multiset = ConcurrentHashMultiset.create();
        multiset.add(1);
        multiset.add(1);
        multiset.add(1);
        multiset.add(1);
        multiset.add(2);
        multiset.add(2);
        multiset.add(2);
        multiset.add(2);

        multiset.add("90~100");
        multiset.add("90~100");
        multiset.add("90~100");
        multiset.add("80~89");
        multiset.add("80~89");

        System.out.println(multiset.count(1));

        System.out.println(multiset.count(1));

        System.out.println(multiset.count(33));

        System.out.println(new Gson().toJson(multiset));


        AtomicLongMap<String> scoreCount = AtomicLongMap.create();
        scoreCount.incrementAndGet("90~100");
        scoreCount.incrementAndGet("90~100");
        scoreCount.incrementAndGet("90~100");
        scoreCount.incrementAndGet("80~89");
        scoreCount.incrementAndGet("80~89");
        scoreCount.incrementAndGet("<70");

        System.out.println(scoreCount);

    }

}
