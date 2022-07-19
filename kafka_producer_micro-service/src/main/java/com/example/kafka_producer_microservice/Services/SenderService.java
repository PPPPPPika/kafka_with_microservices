package com.example.kafka_producer_microservice.Services;

import com.example.kafka_producer_microservice.Models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SenderService {
    private final Logger log = LoggerFactory.getLogger(SenderService.class);
    private final KafkaTemplate<String, User> kafkaTemplate;

    @Value(value = "${topic.name}")
    private String topicName;

    @Autowired
    public SenderService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*public void send(String message){
        kafkaTemplate.send(topicName, message);
    }*/

    /*public void send(String message){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message = [" + message + "] " + "with offset = [" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message = [" + message + "] due to : " + ex.getMessage());
            }
        });
    }*/

    public void sendUser(User user){
        Message<User> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, topicName).build();
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            @Override
            public void onSuccess(SendResult<String, User> result) {
                log.info("Sent message = [" + user + "] " + "with offset = [" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message = [" + user + "] due to : " + ex.getMessage());
            }
        });
    }





}
