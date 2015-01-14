/*
 * Copyright (c) 2014 杭州端点网络科技有限公司
 */

package reflection;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-01-09.
 */
public class InvocationTest {

    public static class Person{
        public void say(String name){
            System.out.println("hello: "+name);
        }
        public void say2(String name, int age){
            System.out.println("hello:"+name+" you are: "+age);
        }
    }

    @Test
    public void test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Person person = new Person();
        MethodUtils.invokeMethod(person, "say", "王根", 24);
    }

}
