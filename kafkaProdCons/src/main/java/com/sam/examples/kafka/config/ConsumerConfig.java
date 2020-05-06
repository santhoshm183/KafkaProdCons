package com.sam.examples.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageHeaderAccessor;

import com.sam.examples.kafka.service.ConsumerService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ConsumerConfig {
	@Autowired
	private ConsumerService consumerService;
	
	@KafkaListener(topics = "${examples.kafka.consumer.topic}" ,groupId = "${examples.kafka.consumer.groupId}")
	public void onConsumeEvent(@Payload final String message) {
		log.info("consume the message {}..", message);
		consumerService.consumeMessage(message);
	}

}
