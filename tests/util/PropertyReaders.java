package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaders {

    private static PropertyReaders instance = null;
    private Properties properties = new Properties();
    private InputStream inputStream = null;

    private PropertyReaders() {
        loadProperties();
    }

    public static PropertyReaders getInstance() {
        if (instance == null) {
            instance = new PropertyReaders();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            inputStream = new FileInputStream("resources/properties/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) throws IOException {
        String readPropValue = properties.getProperty(key);
        inputStream.close();
        return readPropValue;
    }

    public void writeProperty(String key, String value) throws IOException {
        FileOutputStream outputStream;
        outputStream = new FileOutputStream("resources/properties/config.properties");
        properties.setProperty(key, value);
        properties.store(outputStream, null);
        outputStream.close();
    }
}
