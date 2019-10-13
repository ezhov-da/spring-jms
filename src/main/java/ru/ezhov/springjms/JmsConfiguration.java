package ru.ezhov.springjms;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

@EnableJms
@Configuration
public class JmsConfiguration {

    @Bean
    public JmsTemplate queue(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(false);
        System.out.println("init queue");
        return jmsTemplate;
    }

    @Bean
    public JmsTemplate topic(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        System.out.println("init topic");
        return jmsTemplate;
    }


    @Bean
    public JmsListenerContainerFactory<?> topicFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setAutoStartup(true);
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        System.out.println("init topic factory");
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setAutoStartup(true);
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        System.out.println("init queue factory");
        return factory;
    }

}
