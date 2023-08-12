package Utitlities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties properties;
    String path ="config.properties";

    public ReadConfig()
    {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl()
    {
      String value=properties.getProperty("baseUrl");
    if (value!=null)
        return value;
    else
        throw new RuntimeException("baseUrl not provided in config file");
    }

    public String getBrowser()
    {
        String value=properties.getProperty("browser");
        if (value!=null)
            return value;
        else
            throw new RuntimeException("browser not provided in config file");
    }


    public String getUseremail()
    {
        String value=properties.getProperty("useremail");
        if (value!=null)
            return value;
        else
            throw new RuntimeException("useremail not provided in config file");
    }


    public String getPassword()
    {
        String value=properties.getProperty("password");
        if (value!=null)
            return value;
        else {
            throw new RuntimeException("password not provided in config file");
        }
    }


}
