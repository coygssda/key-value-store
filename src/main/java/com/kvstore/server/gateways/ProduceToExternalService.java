package com.kvstore.server.gateways;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kvstore.server.models.ResourcePublishEntity;

@Service
public class ProduceToExternalService {


	private static final Logger logger = LoggerFactory.getLogger(ProduceToExternalService.class);
	private static final String TOPIC = "kvStoreEvent";
	
	@Autowired
	private KafkaTemplate<String, ResourcePublishEntity> kafkaTemplate;
	
	public void publish(ResourcePublishEntity resourcePublishEntity) {
		kafkaTemplate.send(TOPIC, resourcePublishEntity);
		logger.info("published the event");
	}
	


}
