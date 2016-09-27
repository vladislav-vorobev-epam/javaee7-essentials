package com.epam.cdp.jee.service;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;


@ApplicationScoped
public class JMSService {

    @Inject
    JMSContext context;
    @Resource(mappedName = "queueName")
    private Queue queue;

    public void sendMessage(String txt) {

        try {

            context.createProducer().send(queue, txt);

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public String recieveMessage(){
        String message = context.createConsumer(queue)
                .receiveBody(String.class);
        return message;
    }
}