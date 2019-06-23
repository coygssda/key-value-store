package com.kvstore.server.services.implementation;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import com.kvstore.server.models.ResourceDTO;

@ViewIndexed(designDoc = "kvstore")
public interface ResourceDTORepository extends CrudRepository<ResourceDTO, String> {

}
