package com.gioov.nimrod.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gioov.common.util.JsonUtil;
import com.gioov.nimrod.common.mail.service.MailService;
import com.gioov.nimrod.system.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author godcheese
 */
@Component
public class Common {

    private static final Logger LOGGER = LoggerFactory.getLogger(Common.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 默认区域/语言
     */
//    private static final Locale LOCALE = Locale.CHINA;

    /**
     * 默认时区
     */
//    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT+8");

    /**
     * 默认日期格式
     */
//    private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";


    public void initialize() {
        // 首次启动加载数据字典到 ServletContext 内存
        dictionaryService.addDictionaryToServletContext();
        mailService.initialize();
    }

//    public TimeZone getSystemTimeZone() {
//        String timeZoneId = (String) dictionaryService.get("SYSTEM", "TIME_ZONE_ID");
//        if (timeZoneId != null) {
//            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
//            if (!DEFAULT_TIME_ZONE.hasSameRules(timeZone)) {
//                return timeZone;
//            }
//        }
//        return DEFAULT_TIME_ZONE;
//    }
//

    /**
     * 对象转 json
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    /**
     * json 转对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(json, clazz);
    }

//
//    private ObjectMapper getObjectMapper() {
//        String dateFormatPattern = (String) dictionaryService.get("SYSTEM", "DATE_FORMAT_PATTERN");
//        String language = (String) dictionaryService.get("SYSTEM", "LANGUAGE");
//        String county = (String) dictionaryService.get("SYSTEM", "COUNTY");
//        LOGGER.info("dateFormatPattern1={}", dateFormatPattern);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setTimeZone(getSystemTimeZone());
//        objectMapper.setDateFormat(new SimpleDateFormat(dateFormatPattern != null ? dateFormatPattern : DEFAULT_DATE_FORMAT_PATTERN));
//        objectMapper.setLocale((language != null && county != null) ? new Locale(language, county) : LOCALE);
//        return objectMapper;
//    }

}
