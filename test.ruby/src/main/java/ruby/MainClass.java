/*
 * Copyright (c) 2015 杭州端点网络科技有限公司
 */

package ruby;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainClass {


    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine engine = manager.getEngineByName("jruby");

        engine.eval(new BufferedReader(new FileReader("/usr/dev/workspace-luna/test/test.ruby/src/main/java/ruby/TempConverter.rb")));

        Invocable invocable = (Invocable) engine;
        Object tempconverter = invocable.invokeFunction("getTempConverter");

        double degreesCelsius = (Double) invocable.invokeMethod(tempconverter, "f2c", 98.6);
        System.out.println(degreesCelsius);

        double degreesFahrenheit = (Double) invocable.invokeMethod(tempconverter, "c2f",100.0);
        System.out.println(degreesFahrenheit);
    }
}