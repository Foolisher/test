package dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context.start();
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }

}
