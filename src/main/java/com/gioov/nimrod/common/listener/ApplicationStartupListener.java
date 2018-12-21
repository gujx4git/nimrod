package com.gioov.nimrod.common.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Service
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        AutowireCapableBeanFactory factory = event.getApplicationContext().getAutowireCapableBeanFactory();
//        AppProperties appProperties = factory.getBean(AppProperties.class);
//        Map<String, Object> config = BaseResponse.config;
//        config.put("showException", appProperties.getDebug());
//        config.put("timeZone", appProperties.getDefaultTimeZone());
//        config.put("dateFormat", appProperties.getDefaultDateFormat());
    }

}
