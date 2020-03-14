package cn.objectspace.webssh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


import java.util.Objects;

/**
 * 业务对象操作 工具类,
 *
 * @Auther: liaojl
 * @Date: 2019/7/30 17:19
 * @Description:
 */

public class ObjectsUtil {
    private static final Logger log = LoggerFactory.getLogger(ObjectsUtil.class);

    /**
     * Spring 环境变量
     */
    private Environment environment;
    /**
     * Spring 上下文
     */
    private ApplicationContext applicationContext;


    public Environment getEnvironment() {
        return environment;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取Spring 配置文件 信息
     * <p>
     * 并返回相应类型
     *
     * @param key    配置名称
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getSpringConfigProperties(String key, Class<T> tClass) {
        return this.getSpringConfigProperties(key, tClass, null);
    }

    /**
     * 获取Spring 配置文件 信息
     * <p>
     * 并返回相应类型
     *
     * @param key    配置名称
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getSpringConfigProperties(String key, Class<T> tClass, T defaultV) {
        T property = environment.getProperty(key, tClass);
        if (property == null) {
            property = defaultV;
        }
        return property;
    }

    /**
     * 获取Spring 配置文件 信息
     *
     * @param key 配置名称
     * @return 配置值
     */
    public String getSpringConfigProperties(String key) {
        return environment.getProperty(key);
    }

    /**
     * 反射代码 获取 SpringFactory 托管类 通过beanName
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public <T> T getBeanByName(String beanName, Class<T> t) {
        return this.getBeanByName(beanName, t, true);
    }

    /**
     * 反射代码 获取 SpringFactory 托管类 通过beanName
     *
     * @param beanName
     * @param require  是否必须
     * @param <T>
     * @return
     */
    public <T> T getBeanByName(String beanName, Class<T> t, boolean require) {
        T bean = null;
        try {
            bean = SpringContextHolder.getApplicationContext().getBean(beanName, t);

        } catch (BeansException e) {
            if (require) {
                throw e;
            }
        }

        return bean;
    }

    /**
     * 反射代码 获取 SpringFactory 托管类  通过 类型
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getBeanByType(Class<T> tClass) {
        return this.getBeanByType(tClass, true);
    }

    /**
     * 反射代码 获取 SpringFactory 托管类  通过 类型
     *
     * @param tClass
     * @param require 是否必须
     * @param <T>
     * @return
     */
    public <T> T getBeanByType(Class<T> tClass, boolean require) {
        T bean = null;
        try {
            bean = SpringContextHolder.getApplicationContext().getBean(tClass);

        } catch (BeansException e) {
            if (require) {
                throw e;
            }
        }

        return bean;
    }

    private static ObjectsUtil instance = null;
    private static int count = 0;

    private ObjectsUtil() {
        if (count > 0) {
            throw new RuntimeException("创建了两个实例");
        }
        count++;
    }

    public static ObjectsUtil getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ObjectsUtil();
            instance.environment = instance.getBeanByType(Environment.class);
            instance.applicationContext = SpringContextHolder.getApplicationContext();
        }
        return instance;
    }

}
