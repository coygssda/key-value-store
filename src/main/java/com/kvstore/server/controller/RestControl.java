package com.kvstore.server.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kvstore.server.models.Resource;
import com.kvstore.server.resouces.KvHandleDelegate;

@RestController
@RequestMapping("/kvStore")
@ComponentScan
public class RestControl {
	
	@Autowired
	private KvHandleDelegate kvHandleDelegate;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(RestControl.class);
	
	/**
	 * Returns the value of the id which is coming as a path parameter in
	 * request and throws Exception if id is not found.
	 * @param id the id of the document which is store in database
	 * @return value of the id
	 * @throws Exception
	 */
	@RequestMapping(value="/getValueFromId/{id}" , method=RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("id") String id) throws Exception{
	
		logger.info("Inside RestControl class : method = get : id = "+id);
		Resource resource = kvHandleDelegate.kvHandleGenericForQuery(id);
		
		return new ResponseEntity<String>(resource.getValue(),HttpStatus.OK);
	}
	
	/**
	 * Returns 200 OK as response along with the requested body and updates
	 * the document in the database
	 * @param resource request coming from client 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateStorage" , method=RequestMethod.PUT)
	public ResponseEntity<Resource> update(@RequestBody Resource resource) throws Exception{

		logger.info("Inside RestControl class : method= update : "+resource.toString());
		kvHandleDelegate.kvHandleGenericForUpdate(resource);
		
		return new ResponseEntity<Resource>(resource,HttpStatus.OK);
	}

	

}
