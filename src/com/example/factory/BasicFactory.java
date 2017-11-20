package com.example.factory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Pan on 16/12/3.
 */
public class BasicFactory {
    private static BasicFactory factory = new BasicFactory();
    private static Properties prop = null;
    private BasicFactory(){}

    static {
        try {
            prop = new Properties();
            prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.property").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static BasicFactory getFactory(){
        return factory;
    }

    public <T> T getInstance(Class<T> clazz){
        try {
            String name = clazz.getSimpleName();
            String clazzName = prop.getProperty(name);
            return (T) Class.forName(clazzName).newInstance();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
