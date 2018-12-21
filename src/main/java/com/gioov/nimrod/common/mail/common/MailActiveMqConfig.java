package com.gioov.nimrod.common.mail.common;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * @author godcheese
 */
@Configuration
@EnableJms
public class MailActiveMqConfig {

    @Bean
    public Queue mailQueue() {
        return new ActiveMQQueue("mailQueue");
    }

}
