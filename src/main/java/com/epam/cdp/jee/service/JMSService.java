package com.epam.cdp.jee.service;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.*;


@RequestScoped
public class JMSService {

    @Resource(mappedName = "queueName")
    private Queue queue;

    @Inject
    JMSContext context;

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