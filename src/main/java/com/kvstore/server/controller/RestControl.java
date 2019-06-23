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
	
	@RequestMapping(value="/getValueFromId/{id}" , method=RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("id") String id) throws Exception{
		//Resource resource=new Resource();
		//resource.setId("id");
		//resource.setValue("237");
		logger.info("inside rest controller class");
		
		Resource resource = kvHandleDelegate.kvHandleGenericForQuery(id);
		
		return new ResponseEntity<String>(resource.getValue(),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateStorage" , method=RequestMethod.PUT)
	public ResponseEntity<Resource> update(@RequestBody Resource resource) throws Exception{
		System.out.println(resource);
		logger.info("inside rest controller class");
		//now i have to delegate this call to my delegate 
		kvHandleDelegate.kvHandleGenericForUpdate(resource);
		
		return new ResponseEntity<Resource>(resource,HttpStatus.OK);
	}

	

}
