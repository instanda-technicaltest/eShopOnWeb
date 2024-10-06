package com.eShopOnWeb.configreader;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Soniya Patel
 */
public class ConfigReader {

    //Declare the ConfigReader variable
    private static volatile ConfigReader propInstance;

    //Create Private constructor Because of prevent the Instantiation of class
    private ConfigReader() {

    }

    /**
     * This method will return instance of ConfigReader class
     *
     * @return
     */
    public static synchronized ConfigReader getInstance() {
        if (propInstance == null) {
            propInstance = new ConfigReader();
        }
        return propInstance;
    }

    /**
     * This method will read property from config file
     *
     * @param propertyName
     * @return
     */
    public String getProperty(String propertyName) {

        Properties prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/propertiesfile/config.properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {
            System.out.println("Property not found");
        }
        return null;
    }
}
