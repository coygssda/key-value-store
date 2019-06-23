package com.kvstore.server.services.implementation;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kvstore.server.gateways.ProduceToExternalService;
import com.kvstore.server.mapper.MapResourceDTOToResource;
import com.kvstore.server.mapper.MapResourceDTOToResourcePublishEntity;
import com.kvstore.server.models.Resource;
import com.kvstore.server.models.ResourceDTO;
import com.kvstore.server.models.ResourcePublishEntity;
import com.kvstore.server.models.StorageFlag;

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
	
	
	/**
	 * This method calls the repository service to actually update the document.
	 * @param resourceDTO entity which is to be persisted in database
	 */
	public void executeUpdate (ResourceDTO resourceDTO) {
		
		logger.info("Inside KvStoreApplicationService class :method =executeUpdate");
		resourceDTORespositoryService.create(resourceDTO);
	}
	
	
	/**
	 * This method calls the repository service to get the Resource object from 
	 * the database.
	 * @param id id of the document persisted in database
	 * @return
	 */
	public Resource executeQuery(String id) {
		
		logger.info("Inside KvStoreApplicationService class :method =executeQuery");

		ResourceDTO resourceDTO = resourceDTORespositoryService.findOne(id);
		Resource resource = mapResourceDTOToResource.map(resourceDTO);
		
		return resource;
	}
	
	
	/**
	 * This method calls the gateway service to publish the id,value and updatedTime 
	 * which was going to be persisted in the database.
	 * @param resourceDTO
	 */
	public void publishEvent(ResourceDTO resourceDTO,StorageFlag flag) {
		
		logger.info("Inside KvStoreApplicationService class :method =publishEvent");

		ResourcePublishEntity resourcePublishEntity = mapResourceDTOToResourcePublishEntity.map(resourceDTO);
		resourcePublishEntity.setToBeCreated(flag.getIsCreated());
		resourcePublishEntity.setToBeUpdated(flag.getIsUpdated());
		
		produceToExternalService.publish(resourcePublishEntity);
	}

}
