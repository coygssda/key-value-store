package com.kvstore.server.project;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.kvstore.server.models.ResourceDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class KvStoreApplicationTests {

	@Autowired
    private CouchbaseTemplate template;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	public void testResource() {
		
		ResourceDTO resourceDTO = new ResourceDTO();
		resourceDTO.setId("id1");
		resourceDTO.setValue("value1");
		resourceDTO.setUpdated(LocalDateTime.now());
		
		template.insert(resourceDTO);
		webTestClient.get().uri("/kvStore/getValueFromId/id1").exchange().expectStatus().isOk().expectBody(ResourceDTO.class);
		
			}
}
