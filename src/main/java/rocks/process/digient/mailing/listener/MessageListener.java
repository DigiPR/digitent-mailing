/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.digient.mailing.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rocks.process.digient.domain.Mailing;
import rocks.process.digient.mailing.channel.ConsumerChannel;
import rocks.process.digient.message.Message;

@Component
@EnableBinding(ConsumerChannel.class)
public class MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @StreamListener(target = ConsumerChannel.MAILING, condition = "headers['type']=='mailing'")
    @Transactional
    public void customerMessage(@Payload Message<Mailing> mailingMessage) {
        Mailing mailing = mailingMessage.getPayload();
        logger.info("Payload received: " + mailing.toString());
    }

    @StreamListener(target = ConsumerChannel.MAILING)
    public void defaultListener() {}

}
