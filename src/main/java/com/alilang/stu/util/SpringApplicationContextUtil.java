package com.alilang.stu.util;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;


public class SpringApplicationContextUtil {

    private static ConfigurableApplicationContext applicationContext;

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        SpringApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return applicationContext.getBeanFactory();
    }
}

