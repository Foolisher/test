/*
 * Copyright (c) 2015 杭州端点网络科技有限公司
 */

package sonar;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2014-12-17.
 */
public class Test_NP_NULL_PARAM_DEREF {

    public void m1(){
        List arg1 = null;
        Map arg2 = null;
        m2(arg1, arg2);
    }


    public void m2(List arg1, Map arg2){
        System.out.println(arg1.toString());
        System.out.println(arg2.toString());
    }

    @Test
    public void test() {

        HashMap<Object, Object> params = Maps.newHashMap();
        params.put("1", "2");
        String body = HttpRequest.post("http://uv.haier.com/api/groupbuy/alipay/pcurl", true, "1", "2")
//                .send("11111111")
//                .contentLength(1000)
                .body();
        System.out.println(body);


    }


}
