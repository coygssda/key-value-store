package com.kvstore.server.services.implementation;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.error.DocumentDoesNotExistException;
import com.kvstore.server.models.ResourceDTO;
import com.kvstore.server.models.StorageFlag;

@Service
@Qualifier("ResourceDTORespositoryService")
@Component
public class ResourceDTORespositoryService implements ResourceDTOService {
	
	
	Logger logger = LoggerFactory.getLogger(ResourceDTORespositoryService.class);
	
	@Autowired
	private KvStoreApplicationService kvStoreApplicationService;
	
	@Autowired
    private CouchbaseTemplate template;
	
	@Autowired
	private StorageFlag flag;
	
	
	/**
	 * This method return the ResourceDTO after querying the database with the document
	 * key and throws exception if key does not exist.
	 */
	@Override
	public ResourceDTO findOne(String id) {
		if(!template.exists(id)) {
			
			throw new DocumentDoesNotExistException("Document does not exist with respect to requested id = "+ id);
		}
        return template.findById(id, ResourceDTO.class);
	}

	
	/**
	 * This method creates or updates the document in the database depending on the 
	 * condition , if the key already exist then it is updated otherwise a new 
	 * document is created in the database.
	 * 
	 */
	@Override
	public void create(ResourceDTO resourceDTO) {
		
		try {
			
		if(template.exists(resourceDTO.getId())) {
			
			logger.info("Document with id :"+resourceDTO.getId()+" already exist, Updating the document values");
			
			ResourceDTO resourceToBeUpdated = template.findById(resourceDTO.getId(), ResourceDTO.class);
			flag.setIsUpdated(Boolean.TRUE);
			flag.setIsCreated(Boolean.FALSE);
			resourceDTO.setUpdated(LocalDateTime.now());
			
			if(!resourceToBeUpdated.getValue().equals(resourceDTO.getValue())) {
				logger.info("value of id: "+resourceDTO.getId()+" has been changed from "+resourceToBeUpdated.getValue()
				+" to "+resourceDTO.getValue());
				}
			else {
					logger.info("Request recieved to update the already existing value for the existing document");
				}
			
			template.save(resourceDTO);
			
			
		}else {
			
			logger.info("Document with id :"+ resourceDTO.getId()+ " does not exist,thus creating a new document");

			flag.setIsCreated(Boolean.TRUE);
			flag.setIsUpdated(Boolean.FALSE);
			resourceDTO.setUpdated(LocalDateTime.now());

			template.insert(resourceDTO);
		}
		}
		catch(CouchbaseException ex) {
			logger.info("Exception occured while accessing Database");
			kvStoreApplicationService.publishEvent(resourceDTO,flag);

			throw new CouchbaseException(ex.getMessage(), ex.getCause());
		}
		
	}

}
