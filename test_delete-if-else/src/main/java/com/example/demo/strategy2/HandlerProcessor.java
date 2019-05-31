package com.example.demo.strategy2;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/***
 * @author : 马晓光
 * @date   : 2019/5/25
 * @description :
 **/
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String,Class> handlerMap = Maps.newHashMapWithExpectedSize(3);
        ClassScaner.scan("",HandlerType.class).forEach(clazz->{
            String type = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(type,clazz);
        });
        HandlerContext context = new HandlerContext(handlerMap);
        configurableListableBeanFactory.registerSingleton(HandlerContext.class.getName(),context);
    }
}
