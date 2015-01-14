package oop;

/**
 * @author wanggen on 14-8-2.
 */
public class Supper {

    private void m0(){
        System.out.println("Supper.m0()");
    }

    public void m1(){
        System.out.println("Supper.m1()");
    }

    public void m2(){
        this.m1();
        this.m0();
    }

}
