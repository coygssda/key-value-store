package com.kvstore.server.services.implementation;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kvstore.server.gateways.ProduceToExternalService;
import com.kvstore.server.mapper.MapResourceDTOToResource;
import com.kvstore.server.mapper.MapResourceDTOToResourcePublishEntity;
import com.kvstore.server.models.Resource;
import com.kvstore.server.models.ResourceDTO;
import com.kvstore.server.models.ResourcePublishEntity;

public class KvStoreApplicationService {
	
	@Autowired
	private ResourceDTORespositoryService resourceDTORespositoryService;
	
	@Autowired
	private MapResourceDTOToResource mapResourceDTOToResource;
	
	@Autowired
	private MapResourceDTOToResourcePublishEntity mapResourceDTOToResourcePublishEntity;
	
	@Autowired
	private ProduceToExternalService produceToExternalService;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(KvStoreApplicationService.class);
	
	public void executeUpdate (ResourceDTO resourceDTO) {
		
		logger.info("inside the KvStoreApplicationService class");
		logger.info(resourceDTO.toString());
		
		resourceDTORespositoryService.create(resourceDTO);
		
	}
	
	public Resource executeQuery(String id) {
		
		ResourceDTO resourceDTO = resourceDTORespositoryService.findOne(id);
		//map resourceDTO to resource
		//reverse Mapper
		Resource resource = mapResourceDTOToResource.map(resourceDTO);
		
		return resource;
	}
	
	public void publishEvent(ResourceDTO resourceDTO) {
		
		ResourcePublishEntity resourcePublishEntity = mapResourceDTOToResourcePublishEntity.map(resourceDTO);
		
		produceToExternalService.publish(resourcePublishEntity);
	}

}
