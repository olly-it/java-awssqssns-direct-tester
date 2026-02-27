package it.olly.awssqssns.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;

import io.awspring.cloud.messaging.listener.Acknowledgment;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

@Component
public class SqsConnector {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final static int SLEEP = 5000;

    @Autowired
    private AmazonSQS amazonSQS;

    // this is just a replication to have it on logs
    @Value("${cloud.aws.sqs.queue-name}")
    private String queueName;

    // private ThreadPoolTaskExecutor executor = null;

    @PostConstruct
    public void init() {
        logger.debug("********** sqs engine: {}", amazonSQS);

        /*executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(20);
        executor.setCorePoolSize(20);
        executor.setQueueCapacity(0);
        executor.setRejectedExecutionHandler(new SqsBlockingSubmissionPolicy(3000));
        executor.initialize();*/
    }

    /**
     * Reference documentation (this is a new one, but it's similar to the old one):
     * https://docs.awspring.io/spring-cloud-aws/docs/3.2.0/reference/html/index.html#other-annotation-properties
     * 
     * @param message can also be a com.amazonaws.services.sqs.model.Message
     */
    @SqsListener("${cloud.aws.sqs.queue-name}")
    public void receiveMessage(String message, Acknowledgment ack) {
        logger.debug("SQSListener [{}] - SQS got message on queue [{}] - ack: {}", message, queueName, ack);

        process(message);
        ack.acknowledge(); // OK

        // executor.submit(() -> {
        // message processing goes here
        // process
        // });

        logger.debug("SQSListener [{}] - done", message);
    }

    private void process(String message) {
        logger.debug("[{}] - waiting for {} millis", message, SLEEP);
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
        }
        logger.debug("[{}] - message processed", message);
    }

}
