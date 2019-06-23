package com.kvstore.server.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Bean
	public KvStoreApplicationTests KvStoreApplicationTests() {
		return new KvStoreApplicationTests();
	}
	
	}
