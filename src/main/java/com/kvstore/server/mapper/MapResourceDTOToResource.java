package com.kvstore.server.mapper;

import com.kvstore.server.models.Resource;
import com.kvstore.server.models.ResourceDTO;

public class MapResourceDTOToResource {
	
	public Resource map(ResourceDTO resourceDTO) {
		
		Resource resource = new Resource();
		resource.setId(resourceDTO.getId());
		resource.setValue(resourceDTO.getValue());
		
		return resource;
	}

}
