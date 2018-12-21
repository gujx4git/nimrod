package com.gioov.nimrod.common.mail.common;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.mail.entity.MailEntity;
import com.gioov.nimrod.common.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author godcheese
 */
@Component
public class MailConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailConsumer.class);

    @Autowired
    private Common common;

    @Autowired
    private MailService mailService;

    @JmsListener(destination = "mailQueue")
    public void consume(String message) {
        MailEntity mailEntity = null;
        try {
            mailEntity = common.jsonToObject(message, MailEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("consume={}", mailEntity);
        mailService.send(mailEntity);
    }

}
