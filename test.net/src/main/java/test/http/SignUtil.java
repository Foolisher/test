/*
 * Copyright (c) 2015
 */

package test.http;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.hash.Hashing.md5;

/**
 * 团购平台应用于数据校验的简单签名工具
 */
public class SignUtil {

    private final static Logger log = LoggerFactory.getLogger(SignUtil.class);
    private static DozerBeanMapper dozer = new DozerBeanMapper();
    private final static String SIGN = "sign";
    private final static Joiner.MapJoiner mapJoiner = Joiner.on('&').withKeyValueSeparator("=");

    /**
     * <pre>
     * 数据签名.
     *  1. 可以直接对一个字符串对象签名, 如: "A=1&B=2&C=3"
     *  2. 可对Map对象进行签名:将map以 key=value 形式拼接并以 & 连接起来
     *  3. 可对pojo对象进行签名,先将pojo转换为TreeMap,然后将TreeMap对象签名
     *  4. *不可对基本对象类型签名,例如 int Integer double Double 等皆不可
     * </pre>
     *
     * @param data     待签名对象
     * @param salt     加盐HASH
     * @param excludes 将对象中的指定字段排除签名,例如一些final字段
     * @return 签名后的字符串
     */
    public static String sign(Object data, String salt, String... excludes) {
        try {
            checkArgument(data != null, "sign data can't be null");
            if(data instanceof String){
                return md5().newHasher()
                        .putString((String) data, Charsets.UTF_8)
                        .putString(salt, Charsets.UTF_8).hash().toString();
            }
            Map params = toTreeMap(data, excludes);
            String toVerify = mapJoiner.join(params);
            return md5().newHasher()
                    .putString(toVerify, Charsets.UTF_8)
                    .putString(salt, Charsets.UTF_8)
                    .hash().toString();
        } catch (Exception e) {
            log.error("Sign data:[{}] failed caused by:{}", data, e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 验证签名
     *
     * @param data 参数
     * @param sign 签名
     * @return 校验通过
     */
    public static boolean verify(Object data, String sign, String salt, String... excludeKeys) {
        String newSign = sign(data, salt, excludeKeys);
        final boolean isSignMatch = Objects.equal(newSign, sign);
        if (!isSignMatch) {
            log.error("Sign:[{}] from data:[{}] not match given sign:[{}]", newSign, data, sign);
        }
        return isSignMatch;
    }


    /**
     * 若map非TreeMap,将其转换为TreeMap;
     * 若value值域有非String类型,将其转换为String类型
     * 若value值域为 null 或 "",过滤该数据
     *
     * @param data     待签名实例
     * @param excludes 待排除的keys
     * @return 经过滤, 或转换后的map数据
     */
    private static Map toTreeMap(Object data, String... excludes) {
        Map params;
        if (data instanceof Map) {
            params = (Map) data;
        } else {
            params = dozer.map(data, TreeMap.class);
        }
        for (String excKey : excludes) params.remove(excKey);
        params.remove(SIGN);
        if (!(params instanceof TreeMap)) {
            params = new TreeMap<Object, Object>(params);
        }
        Iterator<Map.Entry<Object, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = iterator.next();
            Object value = next.getValue();
            if (value == null || value.toString().length() == 0) {
                iterator.remove();
            }
        }
        return params;
    }

}
