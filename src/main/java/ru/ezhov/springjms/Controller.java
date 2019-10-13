package ru.ezhov.springjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class Controller {

    @Autowired
    @Qualifier("topic")
    private JmsTemplate jmsTemplateTopic;

    @Autowired
    @Qualifier("queue")
    private JmsTemplate jmsTemplateQueue;

    @Autowired
    private TestService testService;

    @Autowired
    private TestJpaRepository testJpaRepository;

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

    @GetMapping("tran")
    public String tran(@PathParam("what") String what) {
        try {

            System.out.println("call init");

            switch (what) {
                case "g":
                    testService.doWorkGood();
                    break;
                case "f":
                    testService.doWorkErrorAfterFirstTopic();
                    break;
                case "j":
                    testService.doWorkErrorAfterJpa();
                    break;
                case "s":
                    testService.doWorkErrorAfterSecondTopic();
                    break;
                case "q":
                    testService.doWorkErrorAfterFirstQueue();
                    break;
            }

            System.out.println("complete");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "count -" + testJpaRepository.count();
    }
}
