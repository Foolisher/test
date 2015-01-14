package maven.test;

/**
 * @author wanggen on 14-8-5.
 */
public class Hello {


    public static void main(String[] args) {

        new Hello().m0();

    }


    public void m0() {
        m1(null, new Object(), new Object());
    }

    private void m1(Integer arg1, Object arg2, Object arg3) {
        String str = arg1.toString() + "";
        str.length();
        arg2.equals("");
        arg3.equals("");
    }


}
