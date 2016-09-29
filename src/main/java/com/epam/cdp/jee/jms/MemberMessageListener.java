package com.epam.cdp.jee.jms;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

/**
 * Created by Vladislav on 30.09.2016.
 */
@MessageDriven(name = "TodoQueueMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "java:/jms/queue/TodoQueue")})
public class MemberMessageListener implements MessageListener {

    @Inject
    private Logger log;

    @Override
    public void onMessage(final Message message) {

        try {
            log.info("Message was received: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            log.severe("Received unexpected message!");
        }
    }
}



