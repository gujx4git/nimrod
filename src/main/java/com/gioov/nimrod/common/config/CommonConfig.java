package com.gioov.nimrod.common.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author godcheese
 */
@Component
public class CommonConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.secret}")
    private String appSecret;

    @Value("${app.url}")
    private String appUrl;

    @Value("${app.security.system-admin-role}")
    private List<String> systemAdminRole;

    @Value("${app.attachment.upload-path}")
    private String uploadPath;

    /**
     * 应用 Logback 日志存储目录
     */
    @Value("${app.log.dir}")
    private String logDir = "../";

    /**
     * 日志文件保存的最大天数
     */
    @Value("${app.log.max-history}")
    private String logMaxHistory = "30";

    /**
     * 日志文件的最大大小
     */
    @Value("${app.log.max-file-size}")
    private String logMaxFileSize = "10MB";

    /**
     * 日志文件总量大小
     */
    @Value("${app.log.total-size-cap}")
    private String logTotalSizeCap = "2GB";


    public String getAppName() {
        return appName;
    }


    public String getAppSecret() {
        return appSecret;
    }



    public String getAppUrl() {
        return appUrl;
    }


    public List<String> getSystemAdminRole() {
        return systemAdminRole;
    }


    public String getUploadPath() {
        return uploadPath;
    }



    public String getLogDir() {
        return logDir;
    }


    public String getLogMaxHistory() {
        return logMaxHistory;
    }


    public String getLogMaxFileSize() {
        return logMaxFileSize;
    }



    public String getLogTotalSizeCap() {
        return logTotalSizeCap;
    }


}
