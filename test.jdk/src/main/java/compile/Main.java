/*
 * Copyright (c) 2014 杭州端点网络科技有限公司
 */

package compile;

import lombok.Getter;

import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.net.URI;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-06-04.
 */
public class Main {

    static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    public static void main(String[] args){



    }


}


class StringSource extends SimpleJavaFileObject {

    @Getter
    private String code;

    public StringSource(String name, String code) {
        super(URI.create("string:///" + name.replace(".", "/") + ".java"), Kind.SOURCE);
        this.code = code;
    }

}