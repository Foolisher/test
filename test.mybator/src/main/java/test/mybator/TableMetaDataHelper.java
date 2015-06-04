package test.mybator;

import com.google.common.collect.Maps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TableMetaDataHelper {


    static String schema = "";
    static String table = "";

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://wg-linux:3306/groupon";
    static String user = "wanggen";
    static String pwd = "wanggen";

//    static {
//        Properties properties = new Properties();
//        InputStream inputStream = null;
//        try {
//            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf.properties");
//            properties.load(in);
//            driver = properties.getProperty("jdbc.driver");
//            url = properties.getProperty("jdbc.url");
//            user = properties.getProperty("jdbc.username");
//            pwd = properties.getProperty("jdbc.password");
//            properties.get("jdbc.driver");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {}
//            }
//        }
//    }

    public static void buildColumnMeta(Map<String, Object> root) {

        table = root.get("tableName").toString();
        schema = root.get("schema").toString();

        String colMetaSql =
                "  SELECT COLUMN_NAME , DATA_TYPE, COLUMN_COMMENT  \n" +
                "       FROM INFORMATION_SCHEMA.COLUMNS             \n" +
                "    WHERE TABLE_NAME = '" + table + "'  \n" +
                "       AND   table_schema = '" + schema + "' ";

        List<Map<String, Object>> properties = new ArrayList<Map<String, Object>>();

        Connection conn = null;
        ResultSet rst = null;
        PreparedStatement stmt = null;

        try {
            // 加载驱动程序
            Class.forName(driver);

            conn = DriverManager.getConnection(url, user, pwd);
            stmt = conn.prepareStatement(colMetaSql);
            rst = stmt.executeQuery();

            while (rst.next()) {
                Map<String, Object> fieldMeta = Maps.newHashMap();
                fieldMeta.put("dbName", rst.getObject("COLUMN_NAME"));
                fieldMeta.put("dbType", rst.getObject("DATA_TYPE").toString().toUpperCase());
                fieldMeta.put("javaName", Strings2.toCamelCase(fieldMeta.get("dbName").toString()));
                fieldMeta.put("javaType", typeMapping.get(fieldMeta.get("dbType")));
                fieldMeta.put("comment", rst.getObject("COLUMN_COMMENT"));
                properties.add(fieldMeta);
                System.out.println(rst.getObject("COLUMN_NAME") + "   " + rst.getObject("DATA_TYPE") + "   " + rst.getObject("COLUMN_COMMENT"));
            }

            root.put("properties", properties);

        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            try {
                if (stmt != null && !stmt.isClosed()) stmt.close();
                if (rst != null && !rst.isClosed()) rst.close();
                if (conn != null && !conn.isClosed()) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, String> typeMapping = new HashMap<String, String>() {
        {
            put("SMALLINT", "Integer");
            put("INT", "Integer");
            put("BIGINT", "Long");
            put("VARCHAR", "String");
            put("DATE", "Date");
            put("DATETIME", "Date");
            put("TEXT", "String");
            put("CHAR", "String");
            put("NUMERIC", "Double");
            put("REAL", "Double");
            put("DOUBLE", "Double");
            put("FLOAT", "Double");
        }
    };

}

