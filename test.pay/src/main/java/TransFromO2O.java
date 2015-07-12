/*
 * Copyright (c) 2014
 */

import com.github.kevinsawicki.http.HttpRequest;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.terminus.common.utils.JsonMapper.JSON_NON_DEFAULT_MAPPER;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-01-13.
 */
public class TransFromO2O {


    @Test
    public void testAlipayTrans() throws IOException {

        String url = "http://uv.haier.com/api/groupbuy/alipay/query?start=20150110000101&end=20150111000101&pageNo=1&pageSize=1000&sign=6132A83888590561FFA967799CD59DF2";


        HttpRequest resp = HttpRequest.get(url);

        System.out.println("Result: "+resp);

        Map map = JSON_NON_DEFAULT_MAPPER.fromJson(resp.body(), Map.class);

        Map result = (Map) map.get("result");
        Map paging = (Map) result.get("paging");
        List<Map> accountLogList = (List<Map>) paging.get("accountLogList");

        System.out.println("账务记录: "+accountLogList);

        System.out.println("body:"+resp.body());


        //        Map resultMap = JSON_NON_EMPTY_MAPPER.fromJson(resp, Map.class);
//
//        resultMap.remove("result");
//        resultMap.remove("request");
//
//        String resp1 = JSON_NON_EMPTY_MAPPER.toJson(resultMap);
//
//        System.out.println("Map to string:"+resp1);

    }

}
