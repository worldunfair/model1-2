package com.lagou.edu.factory;

import com.lagou.edu.utils.ConnectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 应癫
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    /**
     * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
     * 任务二：对外提供获取实例对象的接口（根据id获取）
     */

    private static ApplicationContext applicationContext;


    static {
        ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
        applicationContext = app;
        System.out.println(app.getBean("transferServiceImpl"));


    }


    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static  Object getBean(String id) {
        return applicationContext.getBean(id);
    }

    public static void main(String[] args) throws SQLException {
        ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(app.getBean("transferServiceImpl"));
        ConnectionUtils connectionUtils = (ConnectionUtils)app.getBean("connectionUtils");
        connectionUtils.getCurrentThreadConn();



    }

}
