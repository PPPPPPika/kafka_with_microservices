package com.example.kafka_consumer_microservice.Listeners;

import com.example.kafka_consumer_microservice.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    /*@Value(value = "${topic.name}")
    private String topicName; */

    /*@KafkaListener(topics = "topic.first", groupId = "gr")
    public void listenerFirst(String message){
        System.out.println(message);
    }*/

    @KafkaListener(topics = "topic.first", groupId = "group_one", containerFactory = "kafkaListenerContainerFactory")
    public void listenerFirst(@Payload User user, @Headers MessageHeaders messageHeaders){
        System.out.println("user: " + user);
        System.out.println("messageHeaders: " + messageHeaders);
    }

}
