package org.gver.web.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-8-25.
 */
@Component
@Slf4j
public class HandlebarsEngine {

    private Handlebars handlebars;

    public HandlebarsEngine(){
        handlebars = new Handlebars(new FileTemplateLoader("/usr/dev/workspace-luna/test/test.lucene.webapp/src/main/webapp/"));
        register();
    }

    private void register() {
        handlebars.registerHelper("none", new Helper<Object>() {
            @Override
            public CharSequence apply(Object context, Options options) throws IOException {
                return null;
            }
        });
    }

    public String apply(Object context, String location) {
        try {
            Template template = handlebars.compile(location);
            return template.apply(context);
        } catch (IOException e) {
            log.error("Failed to load .hbs template:[{}]", location, e);
            return "页面未找到！";
        }
    }

}
