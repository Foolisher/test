package test.http;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static io.terminus.common.utils.JsonMapper.JSON_NON_DEFAULT_MAPPER;

/**
 * <pre>
 * 功能描述:
 * </pre>
 *
 * @author wanggen on 14/11/27.
 */
public class HttpRequest0 {


    @Test
    public void testPreventRefundReq() {

        OrderChange orderChange = new OrderChange();
        orderChange.setAction("preventRefundReq");
        orderChange.setTargetIds("1624");
//        orderChange.setTargetId(1l);
        orderChange.setSellerId(2l);
        orderChange.setSign(SignUtil.sign(orderChange,"bUo8rvGoPDcetkBRXwJCQsQLqnZ2GKnf"));

        String json = JSON_NON_DEFAULT_MAPPER.toJson(orderChange);

        String body = HttpRequest
                .post("http://daqihui.dithub.com/api/order/outer/notify")
                .contentType("application/json")
                .send(json)
                .body();
        System.out.println(body);
    }




    @Test
    public void 退款订单状态复查() {
        Map params = Maps.newHashMap();
        params.put("sellerId", "2");
        params.put("orderItemIds", "10283,1,2,3,4,5,6");
        params.put("sign", SignUtil.sign(params, key));
        String resp = HttpRequest.get("http://daqihui.tunnel.mobi/api/order/outer/refund/statusInfos", params, true).body();
        System.out.println(resp);
    }



    @Test
    public void testGetPaid() {

        String body = HttpRequest
                .get("http://www.groupon.com/api/order/outer/paid?paidAtStart=2014-10-01%2000:01:34&paidAtEnd=2014-12-30%2022:00:00&sign=LKDJFO087889LKJ87689LKJ")
                .contentType("application/json")
                .body();

        System.out.println(body);

    }

    @Test
    public void testRefunding() {

        String body = HttpRequest
                .get("http://www.groupon.com/api/order/outer/refund?refundReqAtStart=2014-10-01%2000:01:34&refundReqAtEnd=2014-12-30%2022:00:00&sign=LKDJFO087889LKJ87689LKJ")
                .contentType("application/json")
                .body();

        System.out.println(body);

    }


    @Test
    public void testCcbpayNotify() {

        String data=
         "{" +
         "  \"posid\": \"320887391\"," +
         "  \"branchid\": \"371000000\"," +
         "  \"orderid\": \"20141127094752100000000000000013\"," +
         "  \"payment\": 0.01," +
         "  \"curcode\": \"01\"," +
         "  \"remark1\": \"GROUP\"," +
         "  \"remark2\": \"value2\"," +
         "  \"accType\": \"12\"," +
         "  \"success\": \"Y\"," +
         "  \"type\": \"1\"," +
         "  \"clientip\": \"115.199.244.175\"," +
         "  \"sign\": \"4557295A8FE386ABC68F090A75BCE466\"" +
         "}";

        String body = HttpRequest
                .post("http://www.groupon.com/api/ccbpay/notify")
                .contentType("text/xml")
                .send(data)
                .body();
        System.out.println(body);


    }


    @Test
    public void testWechatpayNotify() {


        String data =
        "{" +
        "  \"returnCode\": \"SUCCESS\"," +
        "  \"appid\": \"wx1514378cc449f87d\"," +
        "  \"mchId\": \"10024535\"," +
        "  \"subMchId\": \"10024535\"," +
        "  \"nonceStr\": \"b1b89999fdfe4a6bbce426215be94845\"," +
        "  \"sign\": \"2872CA9184CA41D4B443D83CA97DB1B8\"," +
        "  \"resultCode\": \"SUCCESS\"," +
        "  \"openid\": \"oGBKDjmaC5ArdGpm9239gMVlyAQQ\"," +
        "  \"isSubscribe\": \"Y\"," +
        "  \"bankType\": \"CMB_DEBIT\"," +
        "  \"totalFee\": 1," +
        "  \"feeType\": \"CNY\"," +
        "  \"transactionId\": \"1001250599201411250006202017\"," +
        "  \"outTradeNo\": \"20141127094752100000000000000013\"," +
        "  \"attach\": \"GROUP\"," +
        "  \"tradeType\": \"NATIVE\"," +
        "  \"timeEnd\": \"20141125221453\"," +
        "  \"success\": true" +
        "}";

        String body = HttpRequest
                .post("http://www.groupon.com/api/wechatpay/notify")
                .contentType("text/xml")
                .send(data)
                .body();
        System.out.println(body);

    }

        String key = "bUo8rvGoPDcetkBRXwJCQsQLqnZ2GKnf";

    @Test
    public void test2() {

        Map data = Maps.newHashMap();
        data.put("orderId", "314");
        data.put("url", "http:///wwwwww");
        data.put("taxNo", "234treweqle,dfmg");
        data.put("sign", SignUtil.sign(data, key));

        String body = HttpRequest
                .post("http://www.groupon.com/api/order/outer/invoice")
                .contentType("application/json")
                .send(JSON_NON_DEFAULT_MAPPER.toJson(data))
                .body();
        System.out.println(body);
    }


    @Test
    public void 支付订单查询() throws IOException {
        String key = "bUo8rvGoPDcetkBRXwJCQsQLqnZ2GKnf";
        String url = "http://daqihui.tunnel.mobi/api/order/outer/paid";
        Map params = Maps.newHashMap();
        params.put("sellerId", "2");
        params.put("paidAtStart", "2015-06-01 12:14:50");
        params.put("paidAtEnd", "2015-06-20 12:30:29");
        params.put("sign", SignUtil.sign(params, key));
        System.out.println(params.get("sign"));
        String resp = HttpRequest.get(url, params, true).body();
        System.out.println(resp);
    }

    @Test
    public void 退款订单查询() throws IOException {
        String key = "bUo8rvGoPDcetkBRXwJCQsQLqnZ2GKnf";
        String url = "http://daqihui.tunnel.mobi/api/order/outer/refund";
        Map params = Maps.newHashMap();
        params.put("sellerId", "2");
        params.put("refundReqAtStart", "2015-05-01 12:14:50");
        params.put("refundReqAtEnd", "2015-06-20 12:30:29");
        params.put("sign", SignUtil.sign(params, key));
        System.out.println(params.get("sign"));
        String resp = HttpRequest.get(url, params, true).body();
        System.out.println(resp);
    }




    @Test
    public void test5() {

        Map userJson = Maps.newHashMap();
        userJson.put("1", "2");
        userJson.put("2", "3");
        String resp = HttpRequest
                .get("http://uv.haier.com/api/outerUser/getGroupUser", true, "user", JSON_NON_DEFAULT_MAPPER.toJson(userJson), "sign", "1")
//                .contentLength(0)
                .body();
        System.out.println(resp);

    }


    @Test
    public void 物流更新() {

        String json = "{\"orderId\":355,\"orderItemId\":361, \"status\":1, \"logistics\":[{\"date\":1419328801231,\"info\":\"工厂生产中\"},{\"date\":1419910201819,\"info\":\"工厂已发货\"},{\"date\":1420254003510,\"info\":\"已到当地集散中心\"}]}";

        String body = HttpRequest.post("http://www.groupon.com/api/order/outer/logistics").contentType("application/json").send(json).body();

        System.out.println(body);

    }


    @Test
    public void test快递100() {

        String url = "http://api.kuaidi100.com/api?id=f93c3f9e5a89fb22&com=huitongkuaidi&nu=50020609401865";
        String resp = HttpRequest.get(url).body();
        Map respMap = JSON_NON_DEFAULT_MAPPER.fromJson(resp, Map.class);
        System.out.println(respMap);

    }

    @Data
    public static class OrderChange {
        private Long targetId;       //订单ID,或子订单ID
        private String targetIds;       //订单ID,或子订单ID
        private Long sellerId;       //订单相关卖家ID
        private String action;         //订单状态处理指令
        private String sign;           //防改签名
    }

}

