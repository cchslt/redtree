package com.stone.spark.conf;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author chen
 * @create 2019-03-23 23:29
 **/

public class ConfigurationManager {

    private static Properties pro = new Properties();

    static {
        try {
            InputStream in = ConfigurationManager.class
                    .getClassLoader().getResourceAsStream("application.properties");
            pro.load(in);
            in.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperties(String key) {
        return pro.getProperty(key);
    }

    public static Integer getInteger(String key) {
        return Integer.parseInt(pro.getProperty(key));

    }

}
