package com.sam.examples.kafka.controller;

import java.util.List;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.sam.examples.kafka.service.ConsumerService;
import com.sam.examples.kafka.service.ProducerService;

@RestController
@RequestMapping(value = "/kafka")
public class ProdConsController {

	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private ConsumerService consumerService;

	@PostMapping(value = "/publish")
	public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
		RecordMetadata sendMessage = producerService.sendMessage(message);
		StringBuilder res = new StringBuilder("Asys message sent");
		if (sendMessage != null) {
			res.append(" ").append("ACK recived");
		}
		return res.toString();
	}
	
	@PostMapping(value = "/subscribe")
	public ResponseEntity<List<String>> getConsumeEvents() {
		List<String> consumeEvents =  consumerService.getConsumerMsgEvents();
		ResponseEntity<List<String>> responseEntity = new ResponseEntity<List<String>>(consumeEvents, HttpStatus.ACCEPTED);
		return responseEntity;
	}
}
