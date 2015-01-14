package dozermapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Desc: 用户信息
 * Mail: houly@terminus.io
 * author: Hou Luyao
 * Date: 14-9-17.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4123946511165612009L;
    @Getter
    @Setter
    @JsonProperty("subscribe")
    private Integer subscribe;    // 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    @Getter
    @Setter
    @JsonProperty("openid")
    private String openid;        // 用户的标识，对当前公众号唯一
    @Getter
    @Setter
    @JsonProperty("nickname")
    private String nickname;      // 用户的昵称
    @Getter
    @Setter
    @JsonProperty("sex")
    private Integer sex;          // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知

    /**
     * Enum description
     *
     */
    public static enum Sex
     {
        MALE(1), FEMALE(2), UNKONOW(0);

        @Getter
        private Integer title;

        /**
         * Constructs ...
         *
         *
         * @param title
         */
        private Sex(Integer title) {
            this.title = title;
        }

        /**
         * Method description
         *
         *
         * @param value
         *
         * @return
         */
        public static Sex from(Integer value) {
            for (Sex s : Sex.values())
             {
                if (Objects.equal(value, s.title)) {
                    return s;
                }
            }

            return null;
        }
    }

    @Getter
    @Setter
    @JsonProperty("language")
    private String language;    // 用户的语言，简体中文为zh_CN

    /**
     * Enum description
     *
     */
    public static enum Language
     {
        ZHCN("zh_CN"), ZHTW("zh_TW"), EN("en");

        @Getter
        private String title;

        /**
         * Constructs ...
         *
         *
         * @param title
         */
        private Language(String title) {
            this.title = title;
        }

        /**
         * Method description
         *
         *
         * @param value
         *
         * @return
         */
        public static Language from(String value) {
            for (Language lan : Language.values())
             {
                if (Objects.equal(value, lan.title)) {
                    return lan;
                }
            }

            return null;
        }
    }

    @Getter
    @Setter
    @JsonProperty("city")
    private String city;               // 用户所在城市
    @Getter
    @Setter
    @JsonProperty("province")
    private String province;           // 用户所在省份
    @Getter
    @Setter
    @JsonProperty("country")
    private String country;            // 用户所在国家
    @Getter
    @Setter
    @JsonProperty("headimgurl")
    private String headimgurl;         // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    @Getter
    @Setter
    @JsonProperty("subscribe_time")
    private Long subscribeTime;        // 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    @Getter
    @Setter
    @JsonProperty("unionid")
    private String unionid;            // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
    @Getter
    @Setter
    @JsonProperty("privilege")
    private List<String> privilege;    // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
}



