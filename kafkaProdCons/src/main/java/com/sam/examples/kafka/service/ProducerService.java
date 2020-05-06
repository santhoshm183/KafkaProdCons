package com.sam.examples.kafka.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.RecordMetadata;
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

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProducerService {
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	@Value("${examples.kafka.producer.topic}")
	private String topic;
	
	public RecordMetadata sendMessage(String messageIn) {
		RecordMetadata recordMetadata = null;
		Message<String> message = buildMessage(messageIn);
		ListenableFuture<SendResult> sendRes = kafkaTemplate.send(message);
		try {
			recordMetadata = sendRes.get(10, TimeUnit.SECONDS).getRecordMetadata();
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.info("Not able to send the message");
		}
		
		sendRes.addCallback(new ListenableFutureCallback<SendResult>() {
			@Override
			public void onFailure(Throwable ex) {
				log.error("kafka sent failed", ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult result) {
				log.info("message sent success {}",result.getRecordMetadata().toString());
			}
		});
		return recordMetadata;
	}

	private Message<String> buildMessage(String message) {
		return MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, topic).build();
	}
}
