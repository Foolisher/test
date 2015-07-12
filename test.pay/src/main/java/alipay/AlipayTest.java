/*
 * Copyright (c) 2014
 */

package alipay;

import com.alipay.sign.MD5;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2014-12-22.
 * @since 1
 * @version 1
 */
public class AlipayTest {



    String pid = "*";
    String key = "*";
    String email = "*";
    String gateway = "*";

//    buyer_email	["15168334488"]
//    buyer_id	["2088202871353074"]
//    discount	["0.00"]
//    gmt_create	["2015-01-10 22:09:45"]
//    gmt_payment	["2015-01-10 22:09:57"]
//    is_total_fee_adjust	["N"]
//    notify_id	["c1d56c2c539b0c0efa9a0fa144080b852e"]
//    notify_time	["2015-01-10 22:23:19"]
//    notify_type	["trade_status_sync"]
//    out_trade_no	["201501102209311000000000000373"]
//    payment_type	["1"]
//    price	["0.01"]
//    quantity	["1"]
//    seller_email	["zhenguan.yang@gmail.com"]
//    seller_id	["2088901984379736"]
//    sign	["53dde785d281e97401fc3f817bebb21d"]
//    sign_type	["MD5"]
//    subject	["我的洗衣机-wgx1"]
//    total_fee	["0.01"]
//    trade_no	["2015011004148507"]
//    trade_status	["TRADE_SUCCESS"]
//    use_coupon	["N"]

    @Test
    public void testNotify1() {

        String params = "sign=0b603c58b1952414056882a8b40ea23f&sign_type=MD5&buyer_email=saitowu@gmail.com&buyer_id=2088002131217869&discount=0.00&gmt_create=2015-01-10 21:45:23&gmt_payment=2015-01-10 21:45:36&is_total_fee_adjust=N&notify_id=6a273efcbc45945a15022b7515f1a4d66s&notify_time=2015-01-10 22:09:57&notify_type=trade_status_sync&out_trade_no=201501102138371000000000001922&payment_type=1&price=88.00&quantity=1&seller_email=zhenguan.yang@gmail.com&seller_id=2088901984379736&subject=无疆妙品宁夏中宁免洗枸杞贡果独立小包装4x1&total_fee=88.00&trade_no=2015011047471186&trade_status=TRADE_SUCCESS&use_coupon=N";

        String resp = HttpRequest.post("http://prepub.dithub.com/api/alipay/notify?" + params, true).body();

        System.out.println(resp);

    }


    @Test
    public void batch_trans_notify() {

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new TreeMap<String, String>();
        sParaTemp.put("service", "batch_trans_notify");
        sParaTemp.put("partner", pid);
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("notify_url", "http://daqihui.ngrok.com/api/alipay/notify");
        sParaTemp.put("email", email);
        sParaTemp.put("account_name", "daqihui");
        sParaTemp.put("pay_date", "20141223");
        sParaTemp.put("batch_no", "2014122301");
        sParaTemp.put("batch_fee", "0.01");
        sParaTemp.put("batch_num", "1");
        sParaTemp.put("detail_data", "1001010^576192941@qq.com^王根^0.01^测试一个");
        //流水号1^收款方帐号1^真实姓名^付款金额1^备注说明1

        getPayGateway(sParaTemp);

    }


    @Test
    public void create_direct_pay_by_user() {

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new TreeMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", pid);
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", "http://daqihui.ngrok.com/api/alipay/notify");
        sParaTemp.put("return_url", "http://daqihui.ngrok.com/trade/");
        sParaTemp.put("seller_email", email);
        sParaTemp.put("out_trade_no", "1234567811");
        sParaTemp.put("subject", "测试直接付");
        sParaTemp.put("total_fee", "0.01");
        sParaTemp.put("body", "测试一下下");
        sParaTemp.put("paymethod", "bankPay");
        sParaTemp.put("defaultbank", "cmb");
        sParaTemp.put("show_url", "http://tuan.haier.com");
        sParaTemp.put("anti_phishing_key", new Date().getTime()+"");
        sParaTemp.put("exter_invoke_ip", "wanggen.ngrok.com");

        getPayGateway(sParaTemp);

    }


    @Test
    public void refund_fastpay_by_platform_pwd() {

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new TreeMap<String, String>();
        sParaTemp.put("service", "refund_fastpay_by_platform_pwd");
        sParaTemp.put("partner", pid);
        sParaTemp.put("_input_charset", "utf-8");
        sParaTemp.put("notify_url", "http://daqihui.ngrok.com");
        sParaTemp.put("return_url", "http://daqihui.ngrok.com");
        sParaTemp.put("seller_email", email);
        sParaTemp.put("refund_date", "2015-01-10 10:01:01");
        sParaTemp.put("batch_no", "2015011050101011");
        sParaTemp.put("batch_num", "1");
//        sParaTemp.put("return_type", "false");
        sParaTemp.put("detail_data", "2014122748761307^0.01^7天包退退款");

        getPayGateway(sParaTemp);

//        batch_no	        ["201501105010101"]
//        notify_id	        ["81958551a0494a587ac94afb1455d02lbo"]
//        notify_time	    ["2015-01-10 10:53:16"]
//        notify_type	    ["batch_refund_notify"]
//        result_details	["2014122748761307^0.01^SUCCESS"]
//        sign	            ["8e23a967ae85c1f691d33e48688881da"]
//        sign_type	        ["MD5"]
//        success_num	    ["1"]

    }


    @Test
    public void testNotify() {

        // body=Haier/海尔滚筒洗衣机7kg全自动XQG70-1012家家爱x1,
        // buyer_email=2292750238@qq.com,
        // buyer_id=2088302799373845,
        // discount=0.0,
        // gmt_create=2015-01-04 14:54:17,
        // gmt_payment=2015-01-04 15:02:25,
        // is_total_fee_adjust=N,
        // notify_id=d305a246f207b650041c8a3f8e1044816o,
        // notify_time=2015-01-04 15:26:22,
        // notify_type=trade_status_sync,
        // out_trade_no=201501041452421000000000001174,
        // payment_type=1,
        // price=1800.01,
        // quantity=1,
        // seller_email=huaibin@haier.com,
        // seller_id=2088511054238429,
        // sign=1812B71A8DB5F20351CAF793A35C2214,
        // sign_type=MD5,
        // subject=Haier/海尔滚筒洗衣机7kg全自动XQG70-1012家家爱x1,
        // total_fee=1800.01,
        // trade_no=2015010470095284,
        // trade_status=TRADE_SUCCESS,
        // use_coupon=N

    }


    private void getPayGateway(Map<String, String> sParaTemp) {
        String data = Joiner.on("&").withKeyValueSeparator("=").join(sParaTemp);
        String sign = MD5.sign(data, key, "utf-8");
        System.out.println(gateway+"?"+data+"&sign="+sign);
    }


    @Test
    public void testPreventRefund() {



    }


    @Test
    public void testMap() {

        System.out.println(Pattern.matches("alipay-.*", "alipay-lkjsdlfkjdslk"));
        System.out.println(Pattern.matches("alipay-.+", "alipay-lkjsdlfkjdslk"));
        System.out.println(Pattern.matches("alipay-.*", "alipay"));
        System.out.println(Pattern.matches("alipay-.*", "-alipay-"));
    }



}
