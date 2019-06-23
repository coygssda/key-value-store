package com.kvstore.server.resouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

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
	
	public void kvHandleGenericForUpdate(Resource resource) {
		
		//from this i will call my application service which will my call my persisistence
		//layer
		//map my resource object to the DTO Object
		ResourceDTO resourceDTO = mapResourceToResourceDTO.map(resource);
		kvStoreApplicationService.executeUpdate(resourceDTO);
		
	}
	
	public Resource kvHandleGenericForQuery(String id) {
		
		return kvStoreApplicationService.executeQuery(id);
		
	}

}
