package com.epam.cdp.jee.service;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;


@ApplicationScoped
public class JMSService {

    @Inject
    JMSContext context;


    @Inject
    Logger log;

    @Resource(mappedName = "java:/jms/queue/TodoQueue")
    private Queue queue;

    public void sendMessage(String txt) {
        try {
            context.createProducer().send(queue, txt);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception sending JMS message", e);

        }

    }


}