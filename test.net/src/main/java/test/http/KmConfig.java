package test.http;

import lombok.Data;

/**
 * @author wanggen on 14-8-14.
 */
@Data
public class KmConfig {
    //主机ip
    private String host;
    //端口号
    private int port;
    //ftp用户名
    private String username;
    //ftp密码
    private String password;
    //ftp中的目录
    private String dir = "";
}