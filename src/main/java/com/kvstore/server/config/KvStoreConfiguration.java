package com.kvstore.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kvstore.server.gateways.ProduceToExternalService;
import com.kvstore.server.mapper.MapResourceDTOToResource;
import com.kvstore.server.mapper.MapResourceDTOToResourcePublishEntity;
import com.kvstore.server.mapper.MapResourceToResourceDTO;
import com.kvstore.server.resouces.KvHandleDelegate;
import com.kvstore.server.services.implementation.KvStoreApplicationService;

@Configuration
public class KvStoreConfiguration {
	
	@Bean
	public KvHandleDelegate kvHandleDelegate() {
		return new KvHandleDelegate();
	}
	
	@Bean
	public KvStoreApplicationService kvStoreApplicationService() {
		return new KvStoreApplicationService();
	}
	
	@Bean
	public MapResourceToResourceDTO mapResourceToResourceDTO() {
		return new MapResourceToResourceDTO();
	
	}
	
	@Bean
	public MapResourceDTOToResource mapResourceDTOToResource() {
		return new MapResourceDTOToResource();
	}

	@Bean
	public MapResourceDTOToResourcePublishEntity mapResourceDTOToResourcePublishEntity() {
		return new MapResourceDTOToResourcePublishEntity();
	}
	
	/*@Bean
	public ProduceToExternalService produceToExternalService() {
		return new ProduceToExternalService();
	}
*/
}
