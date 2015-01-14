package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author wanggen on 14-8-21.
 */
public class FasterJson {

    ObjectMapper objectMapper = new ObjectMapper();

    public void jsonTest() {

    }

    @Test
    public void test2() throws IOException {

        String json = "{\"orderId\":\"355\",\"orderItemId\":\"361\",\"logistics\":[{\"date\":1419328801231,\"info\":\"工厂生产中\"},{\"date\":1419910201819,\"info\":\"工厂已发货\"},{\"date\":1420254003510,\"info\":\"已到当地集散中心\"}]}";

        Map map = objectMapper.readValue(json, Map.class);

        System.out.println(map);

    }


}
