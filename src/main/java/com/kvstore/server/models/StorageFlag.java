package com.kvstore.server.models;

public class StorageFlag {
	
	private Boolean isUpdated;
	
	private Boolean isCreated;

	public StorageFlag() {
		super();
		this.isUpdated = Boolean.FALSE;
		this.isCreated = Boolean.FALSE;
		
	}

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

	public Boolean getIsCreated() {
		return isCreated;
	}

	public void setIsCreated(Boolean isCreated) {
		this.isCreated = isCreated;
	}
	
	

}
