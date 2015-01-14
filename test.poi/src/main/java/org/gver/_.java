package org.gver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-17.
 */
public class _ {

    public static JdbcTemplate jdbcTemplate;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

}
