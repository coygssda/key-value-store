package com.kvstore.server.models;

import java.time.LocalDateTime;

public class ResourcePublishEntity {
	
	private String id;
	
	private String value;
	
	private LocalDateTime aboutTobeUpdated;

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

	public LocalDateTime getAboutTobeUpdated() {
		return aboutTobeUpdated;
	}

	public void setAboutTobeUpdated(LocalDateTime aboutTobeUpdated) {
		this.aboutTobeUpdated = aboutTobeUpdated;
	}
	
	

}
