package it.olly.awssqssns;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageResult;

/**
 * NOTE: this class cannot be @SpringBootTest as this must not be in the context (otherwise, my component in "main"
 * would receive messages)<br>
 * this test must be configured with "accessKey" and "secretKey" env vars
 */
class MultipleMessageSendingTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void testMultiple() {
        logger.info("testMultiple() - CALLED");

        String accessKey = System.getenv("accessKey");
        String secretKey = System.getenv("secretKey");
        String queueUrl = "https://sqs.eu-west-1.amazonaws.com/455269111574/local-msgQ";

        logger.debug("creating client manually on queue: {}", queueUrl);
        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard()
                .withRegion("eu-west-1")
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
        final int TOT = 20;
        logger.debug("senging {} messages on queue: {}", TOT, queueUrl);
        for (int i = 1; i <= TOT; i++) {
            SendMessageResult res = amazonSQS.sendMessage(queueUrl, "Test message body #" + i);
            logger.debug("Message #{} sent: {}", i, res);
        }

        logger.info("testMultiple() - DONE");
    }

}
