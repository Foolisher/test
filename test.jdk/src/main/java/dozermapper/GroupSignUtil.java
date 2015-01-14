package dozermapper;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * Desc: 和团购协议的签名的工具类
 * Mail: houly@terminus.io
 * author: Hou Luyao
 * Date: 14/11/25.
 */
@Slf4j
public class GroupSignUtil {

    private static final HashFunction MD5 = Hashing.md5();

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private static final String SIGN = "sign";

    /**
     * 生成签名
     * @param content   待签名内容
     * @param signKey   签名的key
     * @return  签名
     */
    public static String generateSign(String content, String signKey){
        content = content + "&key=" + signKey;
        return BaseEncoding.base16().encode(MD5.newHasher().putString(content, UTF8).hash().asBytes()).toUpperCase();
    }

    /**
     * 将一个Object生成签名
     * @param object    待签名的对象
     * @param signKey   签名的key
     * @return  签名
     */
    @SuppressWarnings("unchecked")
    public static String generateSign(Object object, String signKey, String...ignores){
        Set ignoreKeys = Sets.newHashSet(ignores, "serialVersionUID");
        String content = mapJoin(objectToMap(object, ignoreKeys), false, false);
        return generateSign(content, signKey);
    }


    /**
     * 根据请求参数连接成请求参数字符串
     * <pre>
     *     object: {"A":"1", "B": "2"}  signKey=LK345LK32lk
     *     则生成URL: A=1&B=2&key=LK345LK32lk
     * </pre>
     * @param object    请求参数pojo，将会转换为map并按 key=value 拼接字符串
     * @param signKey   签名私密key
     * @return          拼接的请求字符串
     */
    public static String url(Object object, String signKey){
        String content = mapJoin(objectToMap(object, null), false, false);
        String sign = generateSign(content, signKey);
        return content + "&sign=" + sign;
    }


    /**
     * 检查sign是否合法
     * @param map 接收到的参数
     * @param sign 接收到的签名
     * @param signKey 签名秘钥
     * @return 是否合法
     */
    public static boolean checkSign(Map<String,String> map, String sign, String signKey){
        return sign.equals(generateSign(mapJoin(map, false, false), signKey));
    }

    /**
     * 将MAP变为key=value&key=value格式字符串
     * @param map map集合 请传递treeMap
     * @param keyLower 是否将key变为小写
     * @param valueUrlencode value是否进行编码
     * @return  格式化后的字符串
     */
    public static String mapJoin(Map<String, String> map,boolean keyLower,boolean valueUrlencode){
        if(map.containsKey(SIGN)){
            map.remove(SIGN);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();

            if(!Strings.isNullOrEmpty(value)){
                try {
                    stringBuilder.append(keyLower ? key.toLowerCase() : key)
                            .append("=")
                            .append(valueUrlencode? URLEncoder.encode(value, "utf-8") : value)
                            .append("&");
                } catch (UnsupportedEncodingException e) {
                    log.error(Throwables.getStackTraceAsString(e));
                }
            }
        }

        if( stringBuilder.length() > 0 ){
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param source 需要转换的object
     * @param ignoreFieldNames 需要忽略的字段名称集合
     * @return 返回一个 treeMap
     */
    public static Map<String, String> objectToMap(Object source, Set<String> ignoreFieldNames){
        Map<String,String> tempMap = Maps.newTreeMap();
        for(Field f : source.getClass().getDeclaredFields()){
            if(!f.isAccessible()){
                f.setAccessible(true);
            }
            //获得属性名称
            String fieldName = f.getName();
            //判断是否忽略
            if( ignoreFieldNames == null || !ignoreFieldNames.contains(fieldName)) {
                Object o = null;
                try {
                    o = f.get(source);
                } catch (IllegalArgumentException e) {
                    log.error(Throwables.getStackTraceAsString(e));
                } catch (IllegalAccessException e) {
                    log.error(Throwables.getStackTraceAsString(e));
                }
                tempMap.put(fieldName, o==null?"":o.toString());
            }
        }
        return tempMap;
    }
}