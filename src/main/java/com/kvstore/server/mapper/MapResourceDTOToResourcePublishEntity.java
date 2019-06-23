package com.kvstore.server.mapper;

import com.kvstore.server.models.ResourceDTO;
import com.kvstore.server.models.ResourcePublishEntity;

public class MapResourceDTOToResourcePublishEntity {
	
	public ResourcePublishEntity map(ResourceDTO resourceDTO) {
		ResourcePublishEntity resourcePublishEntity = new ResourcePublishEntity();
		
		resourcePublishEntity.setId(resourceDTO.getId());
		resourcePublishEntity.setValue(resourceDTO.getValue());
		resourcePublishEntity.settimeToBeUpdated(resourceDTO.getUpdated());
		
		return resourcePublishEntity;
	}

}
