package com.kvstore.server.services.implementation;

import com.kvstore.server.models.ResourceDTO;

public interface ResourceDTOService {
	public ResourceDTO findOne(String id);
	
	void create(ResourceDTO resourceDTO);
	void update(ResourceDTO resourceDTO);

}
