package dubbo.serviceimpl;

import dubbo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wanggen on 14-7-1.
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Value("#{app.key}")
    private String key;

    public DemoServiceImpl(){
        log.info("实例化:{}", this);
        System.out.println("==> "+key);
        log.info("==> "+key);
    }

    @PostConstruct
    public void init(){
        System.out.println(key);
    }

    @Override
    public void sayHello(String name) throws InterruptedException {
        log.info("hello :" + name + "      " + new SimpleDateFormat("HH:mm:ss,SSS").format(new Date()));
        TimeUnit.SECONDS.sleep(5);
    }

    @Override
    public void put(String msg) {
        log.info("Got message: "+msg);
    }

    @Override
    public String get() {
        return "HELLO";
    }
}