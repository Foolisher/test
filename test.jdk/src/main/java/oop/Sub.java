package oop;

/**
 * @author wanggen on 14-8-2.
 * @Desc:
 */
public class Sub extends Supper {

    public void m1(){
        System.out.println("Sub.m1()");
    }

    public static void main(String[] args){

        Supper obj = new Sub();
        obj.m2();

    }

}
