package ru.ezhov.springjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    @Qualifier("topic")
    private JmsTemplate jmsTemplateTopic;

    @Autowired
    @Qualifier("queue")
    private JmsTemplate jmsTemplateQueue;

    @GetMapping("add")
    public String register() {
        System.out.println("call register");
        jmsTemplateTopic.convertAndSend("topic", "topic1");
        jmsTemplateTopic.convertAndSend("topic", "topic2");
        jmsTemplateQueue.convertAndSend("queue", "queue1");
        jmsTemplateQueue.convertAndSend("queue", "queue2");
        System.out.println("send");
        return "add ok";
    }
}
