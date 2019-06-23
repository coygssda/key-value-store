package com.kvstore.server.models;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.Data;

@Data
@Document
public class ResourceDTO {
	


	@Id
	@Field
	@NotNull
	private String id;
	
	@Field
	@NotNull
	private String value;
	
	@Field
	@NotNull
	private LocalDateTime updated;
	
/*	@Field
	@NotNull
	private OffsetDateTime created;
	
	@Field
	private OffsetDateTime updated;
	
	
	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(OffsetDateTime updated) {
		this.updated = updated;
	}*/

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	

}
