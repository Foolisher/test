package dubbo.action;

import dubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;


public class Consumer {



    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService"); //
        while(true){
//            try{
                demoService.sayHello("hello wanggen~"); // ִ

//            }catch (Exception e){
//                e.printStackTrace();
//            }
            TimeUnit.SECONDS.sleep(1);
        }

    }

}