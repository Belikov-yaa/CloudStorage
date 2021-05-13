package gb.cloud.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для загрузки параметров из файла настроек
 */

public class PropertiesLoader {

    private static final String RESOURCE = "app.properties";
    private static final Properties properties;

    static {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        properties = new Properties();
        try {
            properties.load(new FileInputStream(rootPath + RESOURCE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
