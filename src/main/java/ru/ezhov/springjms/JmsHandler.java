package ru.ezhov.springjms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsHandler {

    @JmsListener(destination = "topic", containerFactory = "topicFactory")
    public void topicHandler1() {
        System.out.println("topicHandler1");
    }

    @JmsListener(destination = "topic", containerFactory = "topicFactory")
    public void topicHandler2() {
        System.out.println("topicHandler2");
    }

    @JmsListener(destination = "queue", containerFactory = "queueFactory")
    public void queueHandler2() {
        System.out.println("queueHandler1");
    }
}
