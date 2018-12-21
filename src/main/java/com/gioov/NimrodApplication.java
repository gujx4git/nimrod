package com.gioov;

import com.gioov.nimrod.common.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author godcheese
 */
@SpringBootApplication
public class NimrodApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(NimrodApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(NimrodApplication.class, args);
        LOGGER.info("==================={}===================", "Nimrod is started");
    }

}
