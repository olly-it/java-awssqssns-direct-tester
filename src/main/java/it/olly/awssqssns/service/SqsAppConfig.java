package it.olly.awssqssns.service;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsAppConfig {

    /*@Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSqs);
        factory.setAutoStartup(true);
        factory.setMaxNumberOfMessages(10);// max 10
    
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(20);
        executor.setCorePoolSize(20);
        executor.setQueueCapacity(0);
        executor.initialize();
        factory.setTaskExecutor(executor);
    
        return factory;
    }*/
}