package org.gver.groovy;

import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-17.
 */
public class Main {

    public static void main(String[] args) throws IOException, ScriptException {

//        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
//
//        ScriptEngine groovy = scriptEngineManager.getEngineByExtension("groovy");
//
//        Object eval = groovy.eval("println 'hello 王根'");
//
//        System.out.println(eval);

        Map param = Maps.newHashMap();
        param.put("user", "王根");
        param.put("exp", "34543*34543/23434+87");

        Binding binding = new Binding(param);

        GroovyShell groovyShell = new GroovyShell(binding);

        Object val = groovyShell.evaluate(new File(Resources.getResource("Student.groovy").getPath()));

        System.out.println(val);

    }


}
