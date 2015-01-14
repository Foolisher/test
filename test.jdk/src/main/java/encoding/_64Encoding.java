package encoding;

import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-15.
 */
public class _64Encoding {

    @Test
    public void decode() throws IOException {

        String data = "{\"\\u7269\\u6599\\u53f7\\u7c7b\\u578b\":{\"\\u6b20\\u4ea7\":{\"claimAmount\":-55750,\"scoreAmount\":-3345}}}";

        System.out.println(UnicodeToString(data));

    }


    public static String UnicodeToString(String str) {
        if(str==null)
            return null;
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        StringBuffer sb = new StringBuffer();
        char ch;
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            if (matcher.groupCount() > 1) {
                ch = (char) Integer.parseInt(matcher.group(2), 16);
            } else {
                ch = (char) Integer.parseInt(matcher.group(1), 10);
            }
            matcher.appendReplacement(sb, String.valueOf(ch));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
