package com.kvstore.server.models;

import java.time.LocalDateTime;

public class ResourcePublishEntity {
	
	private String id;
	
	private String value;
	
	private LocalDateTime timeToBeUpdated;
	
	private Boolean toBeCreated;
	
	private Boolean toBeUpdated;
	

	public Boolean getToBeCreated() {
		return toBeCreated;
	}

	public void setToBeCreated(Boolean toBeCreated) {
		this.toBeCreated = toBeCreated;
	}

	public Boolean getToBeUpdated() {
		return toBeUpdated;
	}

	public void setToBeUpdated(Boolean toBeUpdated) {
		this.toBeUpdated = toBeUpdated;
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

	public LocalDateTime gettimeToBeUpdated() {
		return timeToBeUpdated;
	}

	public void settimeToBeUpdated(LocalDateTime timeToBeUpdated) {
		this.timeToBeUpdated = timeToBeUpdated;
	}
	
	

}
