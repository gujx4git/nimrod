package com.gioov.nimrod.common.mail.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author godcheese
 */
@Component
public class MailProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue mailQueue;

    //    @Scheduled(fixedRate=5000, initialDelay=3000)
    public void produce(String message) {
        jmsMessagingTemplate.convertAndSend(mailQueue, message);
    }

}
