package com.kvstore.server.resouces;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.kvstore.server.controller.RestControl;
import com.kvstore.server.mapper.MapResourceToResourceDTO;
import com.kvstore.server.models.Resource;
import com.kvstore.server.models.ResourceDTO;
import com.kvstore.server.services.implementation.KvStoreApplicationService;

@ComponentScan
public class KvHandleDelegate {
	
	@Autowired
	private KvStoreApplicationService kvStoreApplicationService;
	
	@Autowired
	private MapResourceToResourceDTO mapResourceToResourceDTO;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(RestControl.class);

	
	/**
	 * This method calls the application service and maps the Resource input to ResourceDTO
	 * which is the object(Entity) for our database
	 * @param resource input request
	 */
	public void kvHandleGenericForUpdate(Resource resource) {
		
		logger.info("Inside KvHandleDelegate class :method = kvHandleGenericForUpdate");
		
		ResourceDTO resourceDTO = mapResourceToResourceDTO.map(resource);
		kvStoreApplicationService.executeUpdate(resourceDTO);
	}
	
	/**
	 * This method returns the Resource object to RestControl class and calls the 
	 * application service for query.
	 * @param id
	 * @return
	 */
	public Resource kvHandleGenericForQuery(String id) {
		
		logger.info("Inside KvHandleDelegate class :method = kvHandleGenericForQuery");

		return kvStoreApplicationService.executeQuery(id);
		
	}

}
