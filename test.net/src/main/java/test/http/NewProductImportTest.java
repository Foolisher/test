package test.http;


import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;

import java.net.ProtocolException;
import java.util.Map;

/**
 * @author wanggen on 14-7-24.
 */
public class NewProductImportTest {


    public static void main(String[] args) throws ProtocolException {

        Map params = Maps.newHashMap();
        params.put("originalCreationDate", "2014/01/01-01:01:01:110");
        params.put("outTester", "外检员XXX");
        params.put("prototypeSendDate", "2014/08/09");
        params.put("mouldNumber", "编号-NO1");


        HttpRequest req = HttpRequest
                .post("http://www.snz.com/api/newproduct/put", params, true)
                .header("Request Method", "POST")
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded");

        String response = req.body("UTF-8");

        System.out.println(response);

    }

}
