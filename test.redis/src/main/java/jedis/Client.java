/*
 * Copyright (c) 2015
 */

package jedis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Random;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-01-08.
 */
public class Client {

    Jedis jedis = new Jedis("localhost", 1111);

    Random random = new Random();

    @Test
    public void add() {

        String ids[] = new String[1000];
        for (int i = 0; i < ids.length; i++)
            ids[i] = random.nextLong() + "";

        long start = System.currentTimeMillis();
        jedis.sadd("ignoreIds", ids);
        System.out.println("插入耗时: " + (System.currentTimeMillis() - start));

    }

    @Test
    public void members() {

        long start = System.currentTimeMillis();
        jedis.smembers("ignoreIds");
        System.out.println("查询耗时: " + (System.currentTimeMillis() - start));

    }

    @Test
    public void sremv() {

        jedis.del("ignoreIds");

    }

    @Test
    public void hmap() {


        @Data
        @AllArgsConstructor
        class Person {
            private String name;
            private Integer age;
            private String address;
        }

        Map map = Maps.newHashMap();
        map.put("wg0", new Person("wg0", 23, "浙江"));
        map.put("wg1", new Person("wg1", 23, "浙江"));
        map.put("wg2", new Person("wg2", 23, "浙江"));
        map.put("wg3", new Person("wg3", 23, "浙江"));
        map.put("wg4", new Person("wg4", 23, "浙江"));
        map.put("wg5", new Person("wg5", 23, "浙江"));
        map.put("wg6", new Person("wg6", 23, "浙江"));
        map.put("wg7", new Person("wg7", 23, "浙江"));
        map.put("wg8", new Person("wg7", 23, "浙江"));
        map.put("wg9", new Person("wg7", 23, "浙江"));
        map.put("wg10", new Person("wg7", 23, "浙江"));
        map.put("wg11", new Person("wg7", 23, "浙江"));


        Kryo kryo = new Kryo();
        Registration registration = kryo.register(Map.class);
        Output output = new Output(1024);
        kryo.writeObject(output, new Person("王根", 23, "浙江省"));
        System.out.println(output.toBytes().length);

        long time = System.currentTimeMillis();
        jedis.set("wg".getBytes(), output.toBytes());
        System.out.println("写入耗时: " + (System.currentTimeMillis() - time));


        time = System.currentTimeMillis();
        for (int i = 1; i <= 100000; i++) {
            byte[] wgBytes = jedis.get("wg".getBytes());
            Input input = new Input(1024);
            input.setBuffer(wgBytes);
            Person persons = kryo.readObject(input, Person.class);
        }
        System.out.println("查询并且反序列化耗时: " + (System.currentTimeMillis() - time));

    }

}
