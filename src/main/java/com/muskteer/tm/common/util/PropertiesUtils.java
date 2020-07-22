package com.muskteer.tm.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesUtils {
    private static final Logger logger = Logger.getAnonymousLogger();
    private static Properties properties = new Properties();

    static {
        loadConfig("pro.properties");
    }

    public static synchronized void loadConfig(String configFileName) {
        InputStream inStream = null;
        try {
            Resource res = new ClassPathResource(configFileName);
            inStream = res.getInputStream();
            loadConfig(inStream);
        } catch (Exception e) {
            logger.info("加载配置文件出现异常");
        }
    }

    public static synchronized void loadConfig(InputStream is) throws IOException {
        properties.load(is);
    }

    public static String getProperty(String key) {
        return properties.getProperty(key).trim();
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue).trim();
    }

}
