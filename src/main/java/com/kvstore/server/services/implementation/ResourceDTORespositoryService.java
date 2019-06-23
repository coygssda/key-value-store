package com.kvstore.server.services.implementation;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.error.DocumentDoesNotExistException;
import com.kvstore.server.models.ResourceDTO;

@Service
@Qualifier("ResourceDTORespositoryService")
@ComponentScan(basePackageClasses=ResourceDTORepository.class)
@Component
public class ResourceDTORespositoryService implements ResourceDTOService {
	
	/*@Autowired
	private ResourceDTORepository repo;
	
	
*/
	Logger logger = LoggerFactory.getLogger(ResourceDTORespositoryService.class);
	
	@Autowired
	private KvStoreApplicationService kvStoreApplicationService;
	
	@Autowired
    private CouchbaseTemplate template;
	
	@Override
	public ResourceDTO findOne(String id) {
		if(!template.exists(id)) {
			//document with this id does not exist
			throw new DocumentDoesNotExistException("Document does not exist with respect to requested id = "+ id);
		}
        return template.findById(id, ResourceDTO.class);
	}

	@Override
	public void create(ResourceDTO resourceDTO) {
		//resourceDTO.setCreated(null);
		//resourceDTO.setUpdated(null);
		try {
		if(template.exists(resourceDTO.getId())) {
			//means document already exist with the same id
			//we need to update its value
			/*
			 * At this time we need to publish our message to Kafka that
			 * we are about to update the value of this id document
			 */
			resourceDTO.setUpdated(LocalDateTime.now());
			kvStoreApplicationService.publishEvent(resourceDTO);
			logger.info("document already existed but we are updating the value");
			
			template.save(resourceDTO);
			
			
		}else {
			resourceDTO.setUpdated(LocalDateTime.now());
			template.insert(resourceDTO);
		}
		}
		catch(CouchbaseException ex) {
			logger.info("Exception occured while accessing Databse");
			throw new CouchbaseException(ex.getMessage(), ex.getCause());
		}
		
	}

	@Override
	public void update(ResourceDTO resourceDTO) {
		//resourceDTO.setUpdated(OffsetDateTime.now());
		template.insert(resourceDTO);
	}

}
