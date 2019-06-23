package com.kvstore.server.mapper;

import com.kvstore.server.models.Resource;
import com.kvstore.server.models.ResourceDTO;

public class MapResourceToResourceDTO {
	
	public ResourceDTO map(Resource resource) {
		
		ResourceDTO resourceDTO = new ResourceDTO();
		
		resourceDTO.setId(resource.getId());
		resourceDTO.setValue(resource.getValue());
		
		return resourceDTO;
		
	}

}
