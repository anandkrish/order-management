package com.order.management.ordermanagement.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import javax.validation.constraints.NotNull;

/**
 * @author Exzion
 */

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void postMessage(String topicName, String key, String message) {
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, key, message);

        future.addCallback(
                new ListenableFutureCallback<>() {

                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                    }

                    @Override
                    public void onFailure(@NotNull Throwable e) {
                        logger.error("Unable to post order Details = [" + message + "]", e);
                    }
                });
    }
}
