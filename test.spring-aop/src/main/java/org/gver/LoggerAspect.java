package org.gver;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Before("execution(* *create*(..))")
    public void logForOrderAction(){
        log.info("处理订单");
    }


}
