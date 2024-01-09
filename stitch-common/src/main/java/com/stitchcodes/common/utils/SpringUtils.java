package com.stitchcodes.common.utils;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: stitch
 * @Date: 2023/5/7 13:51
 * @Description: spring工具包(方便在非Spring环境中获取bean)
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {

    /*Spring上下文环境*/
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtils.beanFactory = configurableListableBeanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * 根据bean的名称获取bean
     *
     * @param name bean名称
     * @param <T>  泛型
     * @return Bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) SpringUtils.beanFactory.getBean(name);
    }

    /**
     * 根据类型获取Bean
     *
     * @param clazz 类
     * @param <T>   泛型
     * @return Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return SpringUtils.beanFactory.getBean(clazz);
    }

    /**
     * 获取当前的环境配置，若无返回null
     *
     * @return 当前环境配置
     */
    public static String[] getActiveProfile() {
        return SpringUtils.applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前代理对象
     *
     * @param invokes 当前对象类型
     * @param <T>     泛型
     * @return 当前代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invokes) {
        return (T) AopContext.currentProxy();
    }


}
