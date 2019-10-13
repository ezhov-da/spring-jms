package ru.ezhov.springjms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, transactionManager = "transactionManager")
public class TestService {

    @Autowired
    @Qualifier("topic")
    private JmsTemplate jmsTemplateTopic;

    @Autowired
    @Qualifier("queue")
    private JmsTemplate jmsTemplateQueue;

    @Autowired
    private TestJpaRepository testJpaRepository;

    public void doWorkGood() {
        jmsTemplateTopic.convertAndSend("topic", "topic-one");
        System.out.println("service-topic-1");
        testJpaRepository.save(new TestEntity());
        System.out.println("service-jpa");
        jmsTemplateTopic.convertAndSend("topic", "topic-two");
        System.out.println("service-topic-2");
        jmsTemplateQueue.convertAndSend("queue", "queue-one");
        System.out.println("service-queue-1");
    }

    public void doWorkErrorAfterFirstTopic() {
        jmsTemplateTopic.convertAndSend("topic", "topic-one");
        System.out.println("service-topic-1");
        if (1 == 1) {
            throw new IllegalArgumentException("Возбуждено исключение после отправки в первый TOPIC");
        }
        testJpaRepository.save(new TestEntity());
        System.out.println("service-jpa");
        jmsTemplateTopic.convertAndSend("topic", "topic-two");
        System.out.println("service-topic-2");
        jmsTemplateQueue.convertAndSend("queue", "queue-one");
        System.out.println("service-queue-1");
    }

    public void doWorkErrorAfterJpa() {
        jmsTemplateTopic.convertAndSend("topic", "topic-one");
        System.out.println("service-topic-1");
        testJpaRepository.save(new TestEntity());
        System.out.println("service-jpa");
        if (1 == 1) {
            throw new IllegalArgumentException("Возбуждено исключение после JPA");
        }
        jmsTemplateTopic.convertAndSend("topic", "topic-two");
        System.out.println("service-topic-2");
        jmsTemplateQueue.convertAndSend("queue", "queue-one");
        System.out.println("service-queue-1");
    }

    public void doWorkErrorAfterSecondTopic() {
        jmsTemplateTopic.convertAndSend("topic", "topic-one");
        System.out.println("service-topic-1");
        testJpaRepository.save(new TestEntity());
        System.out.println("service-jpa");
        jmsTemplateTopic.convertAndSend("topic", "topic-two");
        System.out.println("service-topic-2");
        if (1 == 1) {
            throw new IllegalArgumentException("Возбуждено исключение после отправки во второй TOPIC");
        }
        jmsTemplateQueue.convertAndSend("queue", "queue-one");
        System.out.println("service-queue-1");
    }

    public void doWorkErrorAfterFirstQueue() {
        jmsTemplateTopic.convertAndSend("topic", "topic-one");
        System.out.println("service-topic-1");
        testJpaRepository.save(new TestEntity());
        System.out.println("service-jpa");
        jmsTemplateTopic.convertAndSend("topic", "topic-two");
        System.out.println("service-topic-2");
        jmsTemplateQueue.convertAndSend("queue", "queue-one");
        System.out.println("service-queue-1");
        if (1 == 1) {
            throw new IllegalArgumentException("Возбуждено исключение после отправки в QUEUE");
        }
    }
}
