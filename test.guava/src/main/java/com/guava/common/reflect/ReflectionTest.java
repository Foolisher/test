package com.guava.common.reflect;

import com.google.common.reflect.Reflection;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <pre>
 * 功能描述:
 * </pre>
 *
 * @author wanggen on 14/11/26.
 */
public class ReflectionTest {


    @Test
    public void test() {

        Api api = Reflection.newProxy(Api.class, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                long start = System.currentTimeMillis();
                Object result = method.invoke(proxy, args);
                System.out.println("执行 【"+method.getName()+"】耗时: ["+(System.currentTimeMillis()-start)+"]");
                return result;
            }
        });

        api.say("网关");

    }

    public static interface Api {
        public void say(String greet);
    }

    public static class ApiImpl implements Api{

        @Override
        public void say(String greet) {
            System.err.println(greet+", hello.");
        }
    }

}
