/*
 * Copyright (c) 2014
 */

package compile;

import com.google.common.collect.Lists;
import lombok.Getter;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-06-04.
 */
public class Main {

    static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        compiler.run(null, System.out, System.err, "-sourcepath", "src", "files/Test.java");

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);


        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects("files/Test.java", "files/Test2.java");
        JavaCompiler.CompilationTask compilerTask = compiler.getTask(null, null, null, null, null, javaFileObjects);

        compilerTask.call();


        String personCode =
                "public class Person{" +
                        "String name=\"java\";" +
                        "}";
        ArrayList<StringSource> person = Lists.newArrayList(new StringSource("Person", personCode));
        JavaCompiler.CompilationTask task = compiler.getTask(new FileWriter("files/Person.class"), null, null, null, null, person);
        System.out.println(task.call());

        Class.forName("Person");



    }


}


class StringSource extends SimpleJavaFileObject {

    @Getter
    private String code;

    public StringSource(String name, String code) {
        super(URI.create("string:///" + name.replace(".", "/") + ".java"), Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors){
        return code;
    }

}