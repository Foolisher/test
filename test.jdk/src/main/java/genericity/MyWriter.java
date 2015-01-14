package genericity;

import java.util.HashMap;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-9.
 */
public class MyWriter<T> {

    public void write(T obj) {


    }


    public static <T> MyWriter<T> build(T obj) {
        return new MyWriter<T>();
    }


    public static void main(String[] args){

        MyWriter<HashMap> myWriter = MyWriter.build(new HashMap());


    }



}
