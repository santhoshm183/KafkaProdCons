package com.sam.examples.kafka.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Setter
@Getter
public class ConsumerService {

	private List<String> consumerMsgEvents = new ArrayList<>();
	
	public void consumeMessage(final String message) {
		log.info("pay load info {}, header info  ",message);
		consumerMsgEvents.clear();
		consumerMsgEvents.add(message);
	}
}
